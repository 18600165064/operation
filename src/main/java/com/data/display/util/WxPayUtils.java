package com.data.display.util;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.Xmls;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.util.Arrays;
import java.util.Map;

public class WxPayUtils {

	private static final Log log = Logs.get();

	public static boolean DEV_MODE = false;

	public static void enableDevMode() {
		DEV_MODE = true;
		log.warn("nutzwx DevMode=true now.");
	}

	/**
	 * 根据提交参数，生成签名
	 *
	 * @param map
	 *            要签名的集合
	 * @param key
	 *            商户秘钥
	 * @return 签名
	 *
	 * @see <a href=
	 *      "https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3">
	 *      微信商户平台签名算法</a>
	 *
	 */
	public static String genPaySign(Map<String, Object> map, String key, String signType) {
		String[] nms = map.keySet().toArray(new String[map.size()]);
		Arrays.sort(nms);
		StringBuilder sb = new StringBuilder();
		signType = signType == null ? "MD5" : signType.toUpperCase();
		for (String nm : nms) {
			Object v = map.get(nm);
			if (null == v)
				continue;
			String s = v.toString();
			if (Strings.isBlank(s))
				continue;
			sb.append(nm).append('=').append(s).append('&');
		}
		sb.append("key=").append(key);
		return Lang.digest(signType, sb).toUpperCase();
	}

	/**
	 * 默认采用 MD5 方式的签名
	 *
	 * @see #genPaySign(Map, String, String)
	 */
	public static String genPaySignMD5(Map<String, Object> map, String key) {
		return genPaySign(map, key, "MD5");
	}
	
	
	public static String nonce_str(){
	    return R.random(10000000, 100000000)+"";
	}
	
	public static String timeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
	/**
	 * 检查一下支付平台返回的 xml，是否签名合法，如果合法，转换成一个 map
	 *
	 * @param xml
	 *            支付平台返回的 xml
	 * @param key
	 *            商户秘钥
	 * @return 合法的 Map
	 *
	 * @throws "e.wx.sign.invalid"
	 *
	 * @see <a href=
	 *      "https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1">
	 *      支付平台文档</a>
	 */
	public static NutMap checkPayReturn(String xml, String key) {
		NutMap map = Xmls.xmlToMap(xml);
		if (!map.containsKey("sign")) {
			throw Lang.makeThrow("e.wx.pay.re.error : %s", xml);
		}
		String sign = map.remove("sign").toString();
		String sign2 = WxPayUtils.genPaySignMD5(map, key);
		if (!sign.equals(sign2))
			throw Lang.makeThrow("e.wx.pay.re.sign.invalid : expect '%s' but '%s'", sign2, sign);
		return map;
	}

}