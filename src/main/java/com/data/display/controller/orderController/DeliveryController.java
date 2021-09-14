package com.data.display.controller.orderController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
import com.data.display.controller.ExceptionController;
import com.data.display.mapper.commodityMapper.ProductMapper;
import com.data.display.mapper.orderMapper.OrderDetailMapper;
import com.data.display.mapper.orderMapper.OrderMapper;
import com.data.display.mapper.richMapper.InformationMapper;
import com.data.display.mapper.userMapper.UserInfoMapper;
import com.data.display.model.commodity.Product;
import com.data.display.model.order.Order;
import com.data.display.model.order.OrderDetail;
import com.data.display.model.rich.Information;
import com.data.display.model.rich.WxFormId;
import com.data.display.model.rich.WxNotice;
import com.data.display.model.user.UserInfo;
import com.data.display.service.PayService;
import com.data.display.util.RedisConstantUtil;
import com.data.display.util.RedisUtil;
import com.data.display.util.config.WxConfig;
import com.github.pagehelper.Page;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.data.display.mapper.orderMapper.DeliveryMapper;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.order.Delivery;
import com.data.display.util.StringUtil;

@Controller
@RequestMapping("/delivery")
public class DeliveryController extends ExceptionController {

	 private static Logger _log = LoggerFactory.getLogger(DeliveryController.class);
	
	 @Resource
	 private DeliveryMapper deliveryMapper;

	@Resource
	private InformationMapper informationMapper;

	@Resource
	private OrderMapper orderMapper;

	@Resource
	private OrderDetailMapper orderDetailMapper;

	@Resource
	private ProductMapper productMapper;

	@Resource
	private RedisUtil redisUtil;

	@Autowired
	private PayService payService;

	@Resource
	private UserInfoMapper userInfoMapper;


	 
	 
	@RequestMapping("/notify")
	@ResponseBody
	public Object notify(HttpServletRequest request){
		DataTableResult result = new DataTableResult();
		 result.setError("提交成功");
		_log.debug(" 快递100 信息回调=====");
        String param = request.getParameter("param");
//		String param= "{" +
//				"\"status\": \"polling\"," +
//				"\"billstatus\": \"got\"," +
//				"\"message\": \"\"," +
//				"\"autoCheck\": \"1\"," +
//				"\"comOld\": \"yuantong\"," +
//				"\"comNew\": \"ems\"," +
//				"\"lastResult\": {" +
//				"\"message\": \"ok\"," +
//				"\"state\": \"3\"," +
//				"\"status\": \"200\"," +
//				"\"condition\": \"F00\"," +
//				"\"ischeck\": \"0\"," +
//				"\"com\": \"yuantong\"," +
//				"\"nu\": \"1234567890\"," +
//				"\"data\": [{" +
//				"\"context\": \"上海分拨中心出库\"," +
//				"\"time\": \"2012-08-28 16:33:19\"," +
//				"\"ftime\": \"2012-08-28 16:33:19\"," +
//				"\"status\": \"在途\"," +
//				"\"areaCode\": \"310000000000\"," +
//				"\"areaName\": \"上海市\"" +
//				"}]" +
//				"}" +
//				"}";

        if (StringUtil.isNotBlank(param)){
            NutMap map = Json.fromJson(NutMap.class, param);
            _log.debug("map=="+Json.toJson(map));
            if (null != map){
                NutMap lastResult = map.getAs("lastResult", NutMap.class);
                if (null != lastResult){
                    String state = lastResult.getString("state"); //  物流状态
                    String nu = lastResult.getString("nu"); //  运单号
                    _log.debug("stat=="+state);
                    _log.debug("nu==="+nu);
                    /**
                     * 如果没有该 运单号 则直接入库 ； 如果有 则更新 物流信息 和 状态 ；
                     */
                    List<NutMap> data = lastResult.getList("data", NutMap.class);

					/**
					 * 签收人   签收时间
					 */
					_log.debug("签收时间==="+data.get(0).get("time"));
					String time = String.valueOf(data.get(0).get("time"));

                    Delivery delivery = new Delivery();
                    delivery.setNu(nu);
                    Delivery delivery2 = deliveryMapper.selectByNu(delivery);
	                   if(delivery2 != null){
	                	   delivery2.setDetails(Json.toJson(data));
	                       delivery2.setState(state);
	                       if (state.equals("3")){
	                       	try {
							   Integer user_id = null;
							   Order order = new Order();
	                       	   order.setTrans_id(nu);
							   Page<Order> orderList = orderMapper.selectByPrimaryKey(order);
							   _log.debug("根据运单号查询的订单数据======="+orderList.size());
							   for (int i = 0; i < orderList.size(); i++) {
									user_id = orderList.get(i).getUser_id();
							   }
							   if (orderList != null && orderList.size() > 0){

							   	   OrderDetail orderDetail = new OrderDetail();
								   orderDetail.setSub_order_no(orderList.get(0).getSub_order_no());
								   List<OrderDetail> detailList = orderDetailMapper.selectByOthers(orderDetail);
								   Product product = new Product();
								   product.setSkuid(detailList.get(0).getSkuid());
								   List<Product> proList = productMapper.getProductData(product);
                                   BigDecimal two = new BigDecimal("2");
								   BigDecimal commission = proList.get(0).getCommission_price_eight();
                                   BigDecimal money = commission.divide(two);

								   //发送消息
								   Information information = new Information();
								   information.setCreate_time(new Date());
								   information.setJump_page(7);
								   information.setStatus(0);
								   information.setUser_id(user_id);
								   information.setTitle("您的拼团商品已签收");
								   information.setSub_title("您的拼团商品已签收请打开拼团赚赚点击确认收货哦。您也赶快开团吧，一个团至少赚18-2600元！");
								   information.setMessage("您的拼团商品已签收请打开拼团赚赚点击确认收货哦。您也赶快开团吧，一个团至少赚18-2600元！");
								   informationMapper.insertYmInformation(information);
                                   UserInfo userInfo = userInfoMapper.selectById(String.valueOf(user_id));
								   try{
								       //服务消息（本人）
                                       WxNotice wxNotice = new WxNotice();
                                       wxNotice.setOpenid(userInfo.getOpen_id_small());
                                       wxNotice.setTemplate_id(WxConfig.SEND_ORDER_FINAL_TEMPLATE_ID);
                                       wxNotice.addKeyWords(orderList.get(0).getTrans_com(),"#173177");
                                       wxNotice.addKeyWords(orderList.get(0).getTrans_id(),"#173177");
                                       wxNotice.addKeyWords(time,"#173177");
                                       wxNotice.addKeyWords(orderList.get(0).getAddr_name(),"#173177");
                                       wxNotice.addKeyWords("请打开拼团赚赚点击确认收货哦，您也赶快开团吧，一个团至少赚18-2600元！","#173177");
                                       this.sendMessageWxSmallV2(wxNotice,String.valueOf(user_id));
                                   }catch (Exception e) {
                                       _log.error("物流签收本人消息发送失败", e);
                                   }


								    //服务消息（团长）
									if (orderList.get(0).getOriginator_id() != null && orderList.get(0).getOriginator_id() != 0
										&& StringUtil.isNotBlank(orderList.get(0).getGroup_num()) && orderList.get(0).getGroup_num() != "0"
									   ){
										UserInfo userInfo2 = userInfoMapper.selectById(String.valueOf(orderList.get(0).getOriginator_id()));

										Information information2 = new Information();
										information2.setCreate_time(new Date());
										information2.setJump_page(0);
										information2.setStatus(0);
										information2.setUser_id(userInfo2.getUser_id());
										information2.setTitle("您的团友"+userInfo.getNick_name()+"商品已签收");
										information2.setSub_title("您的团友"+userInfo.getNick_name()+"还没有发起过拼团哦。赶快联络他开团赚佣金，" +
												"他开团，您每单还可赚 "+money.stripTrailingZeros().toPlainString()+" 元，更有机会获得平台分红奖励！");
										information2.setMessage("您的拼团商品已签收请打开拼团赚赚点击确认收货哦。您也赶快开团吧，一个团至少赚18-2600元！");
										informationMapper.insertYmInformation(information2);
                                    try{
										WxNotice wxNotice2 = new WxNotice();
										wxNotice2.setOpenid(userInfo2.getOpen_id_small());
										wxNotice2.setTemplate_id(WxConfig.SEND_ORDER_FINAL_TEMPLATE_ID1);
										wxNotice2.addKeyWords("团友"+userInfo.getNick_name(),"#173177");
										wxNotice2.addKeyWords("您的团友"+userInfo.getNick_name()+"还没有发起过拼团哦。赶快联络他开团赚佣金。","#173177");
										wxNotice2.addKeyWords("他开团，您每单还可赚 "+money.stripTrailingZeros().toPlainString()+" 元，更有机会获得平台分红奖励！","#173177");
										this.sendMessageWxSmallV2(wxNotice2,String.valueOf(orderList.get(0).getOriginator_id()));
                                    }catch (Exception e) {
                                        _log.error("物流签收团长消息发送失败", e);
                                    }
									}
							      }
								}catch (Exception e) {
									_log.error("消息发送失败", e);
								}
						   	}

					   deliveryMapper.updateDeliveryByNu(delivery2);
					   return responsOk();
	                   }
	                   delivery.setCreate_time(new Date());
	                   delivery.setDetails(Json.toJson(data));
	                   delivery.setState(state);
	                   deliveryMapper.addDelivery(delivery);
                       return responsOk();
                }
            }
        }
		return JSON.toJSONString(result);
	}


	private NutMap responsOk(){
		NutMap aNew = NutMap.NEW();
		aNew.setv("result",true);
		aNew.setv("returnCode","200");
		aNew.setv("message","提交成功");
		return aNew;
	}

	/**
	 * 发送微信服务消息
	 * @param wxNotice
	 * @param userId
	 */
	public void sendMessageWxSmallV2(WxNotice wxNotice,String userId) {

		String key = userId+":formIds";
		if (!redisUtil.hasKey(key)) {
			throw new RuntimeException("无 formId");
		}

		final Object o = redisUtil.leftPop(key);
		if (o == null) {
			throw new RuntimeException("无 formId");
		}
		WxFormId wxFormId = JSON.parseObject(o.toString(), WxFormId.class);
		while (wxFormId.getExpierTime() < System.currentTimeMillis()/1000){
			final Object o1 = redisUtil.leftPop(key);
			if (o1 == null) {
				throw new RuntimeException("无 formId");
			}
			wxFormId = JSON.parseObject(o1.toString(), WxFormId.class);
		}

		String token = "";
		if (redisUtil.hasKey(RedisConstantUtil.ACCESS_TOKEN)) {
			token = redisUtil.get(RedisConstantUtil.ACCESS_TOKEN).toString();
		} else {
			token = payService.getAccessToken().getData().toString();
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("touser", wxNotice.getOpenid());
		jsonObject.put("template_id", wxNotice.getTemplate_id());
		jsonObject.put("form_id", wxFormId.getFormId());
		jsonObject.put("data", wxNotice.getData());
		_log.debug("sendMessageWxSmall:" + jsonObject.toString());
		NutMap reHeader = new NutMap();
		reHeader.put("Content-Type", "application/json");
		Header header = Header.create(reHeader);
		Response resp = Http.post3(WxConfig.WX_TEMPLATE + token, jsonObject.toJavaObject((Object.class)), header, 10000);
		_log.debug(resp.getContent());
	}

}
