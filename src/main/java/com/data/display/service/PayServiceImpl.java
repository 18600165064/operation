package com.data.display.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.WidthBean;
import com.data.display.util.*;
import com.data.display.util.config.WxConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.lang.Xmls;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service("payService")
public class PayServiceImpl implements PayService {

    private static final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);
    @Resource
    RedisUtil redisUtil;

    /**
     * 微信服务消息
     * @return
     */
    public synchronized DataTableResult getAccessToken() {
        DataTableResult result = new DataTableResult();
        if (redisUtil.hasKey(RedisConstantUtil.ACCESS_TOKEN)) {
            result.setData(redisUtil.get(RedisConstantUtil.ACCESS_TOKEN));
            return result;
        } else {
            String url = String.format("%s/token?grant_type=client_credential&appid=%s&secret=%s",
                    WxConfig.WX_API_CGI_BIN,
                    WxConfig.APPID,
                    WxConfig.APPSECRET);
            Response resp = Http.get(url);
            if (!resp.isOK()) {
                throw new RuntimeException("获取AccessTokens失败");
            }
            JSONObject jo = JSONObject.parseObject(resp.getContent());
            String token = jo.getString("access_token");
            Integer expires = jo.getInteger("expires_in") - 60;
            redisUtil.set(RedisConstantUtil.ACCESS_TOKEN, token, expires);
            result.setData(token);
            return result;
        }
    }

    @Override
    public String sendHongbao(WidthBean bean) {
        NutMap map = new NutMap();
        map.setv("partner_trade_no", bean.getOrder_no());  // 商户订单号
        map.setv("mchid", WxConfig.MCH_ID); // 商户号
        map.setv("mch_appid", WxConfig.APPID); //  申请商户号的appid或商户号绑定的appid
        map.setv("openid", bean.getOpen_id());  // 用户openid
        map.setv("check_name", "NO_CHECK");  // 校验用户姓名选项  NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名
        map.setv("amount", bean.getTotal_fee()); // 金额 分
        map.setv("spbill_create_ip", "60.205.217.185");
        map.setv("desc", bean.getRemark());  // 企业付款描述信息
        log.debug("传递参数======" + JSON.toJSONString(map));
        Wxs.fillPayMap(map, WxConfig.PATERNERKEY);
        String xml = Xmls.mapToXml(map);
        log.debug(xml);
        return PostXml(xml, WxConfig.TRANSFER_URL);
    }

    @Override
    public DataTableResult balanceWithdraw(int userId, String openid, String amount, String payOrderNo, String remark) {
        DataTableResult result2 = new DataTableResult();
        BigDecimal totalCent = new BigDecimal(amount).multiply(new BigDecimal(100));
        WidthBean widthBean = new WidthBean();
        widthBean.setOrder_no(payOrderNo);
        widthBean.setOpen_id(openid);
        if (StringUtils.isBlank(remark)) {
            widthBean.setRemark("拼团赚赚提现");
        } else {
            widthBean.setRemark(remark);
        }
        widthBean.setTotal_fee(totalCent.intValue());
        String result = this.sendHongbao(widthBean);
        if (StringUtils.isBlank(result)) {
            result2.setDraw(0);
            result2.setError("提现异常,稍后再试");
            return result2;
        }
        NutMap nutMap = Xmls.xmlToMap(result);
        log.info("withdraw_response==" + JSON.toJSONString(nutMap));
        if (null != nutMap.getString("err_code") && nutMap.getString("err_code").equals(RedisConstantUtil.SENDNUM_LIMIT)) {
            result2.setDraw(0);
            result2.setError("提现超次数");
            //超次数 记录redis
            if (redisUtil.hHasKey(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userId))) {
                int num = Integer.parseInt(redisUtil.hget(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userId)).toString()) + 1;
                redisUtil.hset(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userId), String.valueOf(num));
            } else {
                LocalDateTime today_end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);//当天23点59分59秒
                long endLong = DateUtil.remainingSeconds(today_end) + 1;
                redisUtil.hset(RedisConstantUtil.SENDNUM_LIMIT, String.valueOf(userId), "1", endLong);
            }
        } else if (!nutMap.get("return_code").equals("SUCCESS") || !nutMap.get("result_code").equals("SUCCESS")) {
            String return_msg = nutMap.getString("return_msg");
            String err_code_des = nutMap.getString("err_code_des");
            String msg = StringUtil.isBlank(return_msg) ? err_code_des : return_msg;
            if (StringUtil.isBlank(msg)) {
                result2.setDraw(0);
                result2.setError("提现失败");
            }else{
                result2.setDraw(0);
                result2.setError(err_code_des);
            }
            return result2;
        } else {
            result2.setDraw(1);
            result2.setError("成功");
        }
        return result2;
    }

    @Override
    public DataTableResult balanceQuery(String payOrderNo) {
        DataTableResult result2 = new DataTableResult();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("partner_trade_no", payOrderNo);  // 商户订单号
        map.put("mch_id", WxConfig.MCH_ID); // 商户号
        map.put("appid", WxConfig.APPID); //  申请商户号的appid或商户号绑定的appid
        Wxs.fillPayMap(map, WxConfig.PATERNERKEY);
        String xml = Xmls.mapToXml(map);
        log.debug(xml);
        String result = PostXml(xml, WxConfig.GET_TRANSFERINFO_URL);
        if (StringUtils.isBlank(result)) {
            result2.setDraw(0);
            result2.setError("提现异常,稍后再试");
            return result2;
        }

        NutMap nutMap = Xmls.xmlToMap(result);
        log.info(JSON.toJSONString(nutMap));
        String return_code = nutMap.getString("return_code");
        String result_code = nutMap.getString("result_code");
        String err_code = nutMap.getString("err_code");
        if (return_code.equals("SUCCESS") && result_code.equals("SUCCESS")) {
            result2.setDraw(0);
            result2.setError("已经提现了");
            return result2;
        } else if (err_code != null && err_code.equals("NOT_FOUND") && return_code.equals("SUCCESS") && result_code.equals("FAIL")) {
            result2.setDraw(1);
            return result2;
        } else {
            String return_msg = nutMap.getString("return_msg");
            String err_code_des = nutMap.getString("err_code_des");
            String msg = StringUtil.isBlank(return_msg) ? err_code_des : return_msg;
            if (StringUtil.isBlank(msg)) {
                result2.setDraw(0);
                result2.setError("查询失败");
            }
            return result2;
        }
    }

    @Override
    public DataTableResult refund(Integer totalAmount, Integer refundAmount, String payOrderNo, String refundNo) {
        DataTableResult result2 = new DataTableResult();

        NutMap map = new NutMap();
        map.setv("out_trade_no", payOrderNo);  // 商户订单号
        map.setv("mch_id", WxConfig.MCH_ID); // 商户号
        map.setv("out_refund_no", refundNo);//商户退款单号
        map.setv("total_fee", totalAmount);//订单金额
        map.setv("refund_fee", refundAmount);//退款金额
        map.setv("appid", WxConfig.APPID); //  申请商户号的appid或商户号绑定的appid
        Wxs.fillPayMap(map, WxConfig.PATERNERKEY);
        String xml = Xmls.mapToXml(map);
        log.debug(xml);
        String result = PostXml(xml, WxConfig.REFUND_RUL);
        if (StringUtils.isBlank(result)) {
            result2.setDraw(0);
            result2.setError("退款异常，请稍后重试！");
            return result2;
        }
        System.out.println("hello");
        NutMap nutMap = Xmls.xmlToMap(result);
        System.out.println("---------withdraw_response==" + JSON.toJSONString(nutMap));
        if (!nutMap.getString("return_code").equals("SUCCESS") || !nutMap.getString("result_code").equals("SUCCESS")) {
            String return_msg = nutMap.getString("return_msg");
            String err_code_des = nutMap.getString("err_code_des");
            String msg = StringUtil.isBlank(err_code_des) ? return_msg : err_code_des;
            if (StringUtil.isBlank(msg)) {
                msg = "提现失败";
                result2.setDraw(0);
                result2.setError(msg);
            }
            return result2;
        }
        result2.setDraw(1);
        result2.setError("退款成功");
        return result2;
    }

    @Override
    public DataTableResult refundWx(Integer totalAmount, Integer refundAmount, String payOrderNo, String refundNo) {
        DataTableResult result2 = new DataTableResult();
        NutMap map = new NutMap();
        map.setv("out_trade_no", payOrderNo);  // 商户订单号
        map.setv("mch_id", WxConfig.MCH_ID); // 商户号
        map.setv("out_refund_no", refundNo);//商户退款单号
        map.setv("total_fee", totalAmount);//订单金额
        map.setv("refund_fee", refundAmount);//退款金额
        map.setv("appid", WxConfig.APPID); //  申请商户号的appid或商户号绑定的appid
        Wxs.fillPayMap(map, WxConfig.PATERNERKEY);
        String xml = Xmls.mapToXml(map);
        log.debug(xml);
        String result = PostXml(xml, WxConfig.REFUND_RUL);
        if (StringUtils.isBlank(result)) {
            result2.setDraw(0);
            result2.setError("退款异常，请稍后重试！");
            return result2;
        }
        NutMap nutMap = Xmls.xmlToMap(result);
        System.out.println("---------withdraw_response==" + JSON.toJSONString(nutMap));
        if (!nutMap.getString("return_code").equals("SUCCESS") || !nutMap.getString("result_code").equals("SUCCESS")) {
            String return_msg = nutMap.getString("return_msg");
            String err_code_des = nutMap.getString("err_code_des");
            String msg = StringUtil.isBlank(err_code_des) ? return_msg : err_code_des;
            if (StringUtil.isBlank(msg)) {
                msg = "提现失败";
                result2.setDraw(0);
                result2.setError(msg);
            }
            return result2;
        }
        result2.setDraw(1);
        result2.setError("退款成功");
        return result2;
    }

    /**
     * 退款状态：
     * <p>
     * SUCCESS—退款成功
     * <p>
     * REFUNDCLOSE—退款关闭。
     * <p>
     * PROCESSING—退款处理中
     * <p>
     * CHANGE—退款异常
     *
     * @param refundNo 退款单号
     * @return
     */
    @Override
    public NutMap refundQuery(String refundNo) {
        DataTableResult result2 = new DataTableResult();
        NutMap map = new NutMap();
        map.setv("out_refund_no", refundNo);
        map.setv("mch_id", WxConfig.MCH_ID); // 商户号
        map.setv("appid", WxConfig.APPID); //  申请商户号的appid或商户号绑定的appid
        Wxs.fillPayMap(map, WxConfig.PATERNERKEY);
        String xml = Xmls.mapToXml(map);
        log.debug(xml);
        NutMap map2 = new NutMap();
        String result = PostXml(xml, WxConfig.GET_TRANSFERINFO_URL1);
        if (StringUtils.isBlank(result)) {
            result2.setDraw(0);
            result2.setError("退款异常，请稍后重试！");
            map2.setv("status", "退款异常，请稍后重试！");
            return map2;
        }
        NutMap nutMap = Xmls.xmlToMap(result);
        log.info("responseMap==" + JSON.toJSONString(nutMap));
        String refund_status_0 = nutMap.getString("refund_status_0");
        NutMap map1 = new NutMap();
        if (refund_status_0.equals("SUCCESS")) {
            map1.setv("status", "0");//成功
        } else if (refund_status_0.equals("FAIL")) {
            map1.setv("status", "1");//失败
        } else if (refund_status_0.equals("REFUNDCLOSE")) {
            map1.setv("status", "2");//退款关闭
        } else {
            map1.setv("status", "3");//退款异常
        }
        return map1;
    }

    /**
     * 微信订单状态查询
     *
     * @param order_no
     * @return
     */
    @Override
    public boolean checkOrderState(String order_no) {
        boolean flag = false;
        NutMap params = new NutMap();
        params.put("appid", WxConfig.APPID);
        params.put("mch_id", WxConfig.MCH_ID);
        params.put("out_trade_no", order_no.trim());
        String nonceStr = WxPayUtils.nonce_str();
        params.put("nonce_str", nonceStr);
        // 填充签名
        String sign = WxPayUtils.genPaySignMD5(params, WxConfig.PATERNERKEY);
        params.put("sign", sign);
        String reqxml = Xmls.mapToXml(params);
        //	log.debug(reqxml);
        org.nutz.http.Response response = Http.post3("https://api.mch.weixin.qq.com/pay/orderquery", reqxml, null,
                20 * 1000);
        if (response.isOK()) {
            String respxml = response.getContent();
            log.debug(respxml);
            NutMap map = Xmls.xmlToMap(respxml);
            if ("SUCCESS".equals(map.get("return_code")) && "SUCCESS".equals(map.get("result_code"))
                    && "SUCCESS".equals(map.get("trade_state"))) {
                flag = true;
                log.debug("falg===" + flag);
            }
        }
        return flag;
    }

    /**
     * 微信订单状态查询
     *
     * @param order_no
     * @return
     */
    @Override
    public boolean checkWxOrderState(String order_no) {
        boolean flag = false;
        NutMap params = new NutMap();
        params.put("appid", WxConfig.APPID);
        params.put("mch_id", WxConfig.MCH_ID);
        params.put("out_trade_no", order_no.trim());
        String nonceStr = WxPayUtils.nonce_str();
        params.put("nonce_str", nonceStr);
        // 填充签名
        String sign = WxPayUtils.genPaySignMD5(params, WxConfig.PATERNERKEY);
        params.put("sign", sign);
        String reqxml = Xmls.mapToXml(params);
//		log.debug(reqxml);
        org.nutz.http.Response response = Http.post3("https://api.mch.weixin.qq.com/pay/orderquery", reqxml, null,
                20 * 1000);
        if (response.isOK()) {
            String respxml = response.getContent();
            log.debug(respxml);
            NutMap map = Xmls.xmlToMap(respxml);
            if ("SUCCESS".equals(map.get("return_code")) && "SUCCESS".equals(map.get("result_code"))
                    && "SUCCESS".equals(map.get("trade_state"))) {
                flag = true;
                log.debug("falg===" + flag);
            }
        }
        return flag;
    }

    /**
     * 订单是否支付
     *
     * @param order_no
     * @return
     */
    @Override
    public boolean ifOrderPayed(String order_no) {
        boolean b = checkOrderState(order_no) || checkWxOrderState(order_no);
        return b;
    }


    private String PostXml(String xml, String url) {
        FileInputStream instream = null;
        CloseableHttpResponse response = null;
        BufferedReader bufferedReader = null;
        StringBuilder text = new StringBuilder();
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            String file_path = "/data/springboot/1501011831.p12";
            log.debug("微信证书路径：" + file_path);
            instream = new FileInputStream(new File(file_path));
            keyStore.load(instream, WxConfig.MCH_ID.toCharArray());
            instream.close();
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, WxConfig.MCH_ID.toCharArray()).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            HttpPost httpPost = new HttpPost(url);
            HttpEntity httpEntity = new StringEntity(xml, "utf-8");
            log.debug("==============>", httpEntity.getContent());
            httpPost.setEntity(httpEntity);
            httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    text.append(line);
                }
            }
            EntityUtils.consume(entity);
            response.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return text.toString();
    }

}
