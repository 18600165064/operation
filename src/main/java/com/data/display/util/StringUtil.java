package com.data.display.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.BooleanUtils;

public class StringUtil extends org.apache.commons.lang3.StringUtils {
	private static final char SEPARATOR = '_';
	private static final String CHARSET_NAME = "UTF-8";


	/**
	 * 判断微信是否返回错误
	 *
	 * @param result JSONObject
	 * @return boolean
	 */
	public static boolean isErrcode(JSONObject result) {
		if (result.get("errcode") == null) {
			return true;
		}
		return false;
	}


	public static String dataRandom(){
		//格式化当前时间
		SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String strDate = sfDate.format(new Date());
		//得到17位时间如：20170411094039080
		//为了防止高并发重复,再获取3个随机数
		String random = getRandom620(3);
		return strDate+random;
	}


	/**
	 * 获取6-10 的随机位数数字
	 * @param length	想要生成的长度
	 * @return result
	 */
	public static String getRandom620(Integer length) {
		String result = "";
		Random rand = new Random();
		int n = 20;
		if (null != length && length > 0) {
			n = length;
		}
		int randInt = 0;
		for (int i = 0; i < n; i++) {
			randInt = rand.nextInt(10);
			result += randInt;
		}
		return result;
	}


	public static byte[] getBytes(String str) {
		if (str != null) {
			try {
				return str.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}
		return null;
	}

	public static Boolean toBoolean(Object val) {
		if (val == null) {
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf((BooleanUtils.toBoolean(val.toString())) || ("1".equals(val.toString())));
	}

	public static String toString(byte[] bytes) {
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}

	public static String toString(Object obj, String defaultVal) {
		return obj == null ? defaultVal : obj.toString();
	}

	public static boolean inString(String str, String... strs) {
		if (str != null) {
			for (String s : strs) {
				if (str.equals(trim(s))) {
					return true;
				}
			}
		}
		return false;
	}

	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	public static String replaceMobileHtml(String html) {
		if (html == null) {
			return "";
		}
		return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
	}

	public static String abbr2(String param, int length) {
		if (param == null) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		int n = 0;

		boolean isCode = false;
		boolean isHTML = false;
		for (int i = 0; i < param.length(); i++) {
			char temp = param.charAt(i);
			if (temp == '<') {
				isCode = true;
			} else if (temp == '&') {
				isHTML = true;
			} else if ((temp == '>') && (isCode)) {
				n -= 1;
				isCode = false;
			} else if ((temp == ';') && (isHTML)) {
				isHTML = false;
			}
			try {
				if ((!isCode) && (!isHTML)) {
					n += String.valueOf(temp).getBytes("GBK").length;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (n <= length - 3) {
				result.append(temp);
			} else {
				result.append("...");
				break;
			}
		}
		String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");

		temp_result = temp_result.replaceAll(
				"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
				"");

		temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");

		Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
		Matcher m = p.matcher(temp_result);
		List<String> endHTML = Lists.newArrayList();
		while (m.find()) {
			endHTML.add(m.group(1));
		}
		for (int i = endHTML.size() - 1; i >= 0; i--) {
			result.append("</");
			result.append((String) endHTML.get(i));
			result.append(">");
		}
		return result.toString();
	}

	public static Double toDouble(Object val) {
		if (val == null) {
			return Double.valueOf(0.0D);
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
		}
		return Double.valueOf(0.0D);
	}

	public static Float toFloat(Object val) {
		return Float.valueOf(toDouble(val).floatValue());
	}

	public static Long toLong(Object val) {
		return Long.valueOf(toDouble(val).longValue());
	}

	public static Integer toInteger(Object val) {
		return Integer.valueOf(toLong(val).intValue());
	}

	public static String getRemoteAddr(HttpServletRequest request) {
		String remoteAddr = request.getHeader("X-Real-IP");
		if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("X-Forwarded-For");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("Proxy-Client-IP");
		} else if (isNotBlank(remoteAddr)) {
			remoteAddr = request.getHeader("WL-Proxy-Client-IP");
		}
		return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}

	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '_') {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static String toUnderScoreCase(String s) {
		if (s == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;
			if (i < s.length() - 1) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}
			if ((i > 0) && (Character.isUpperCase(c))) {
				if ((!upperCase) || (!nextUpperCase)) {
					sb.append('_');
				}
				upperCase = true;
			} else {
				upperCase = false;
			}
			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}

	public static void setValueIfNotBlank(String target, String source) {
		if (isNotBlank(source)) {
			target = source;
		}
	}

	public static String jsGetVal(String objectString) {
		StringBuilder result = new StringBuilder();
		StringBuilder val = new StringBuilder();
		String[] vals = split(objectString, ".");
		for (int i = 0; i < vals.length; i++) {
			val.append("." + vals[i]);
			result.append("!" + val.substring(1) + "?'':");
		}
		result.append(val.substring(1));
		return result.toString();
	}

	public static String listToString(List<String> stringList, String sepStr) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(sepStr);
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	public static List<String> stringToList(String str, String sepStr) {
		if (str == null) {
			return null;
		}
		String[] arr = str.split(sepStr);
		return Arrays.asList(arr);
	}

	public static String getCode6() {
		Random rad = new Random();

		String result = rad.nextInt(1000000) + "";
		if (result.length() != 6) {
			return getCode6();
		}
		return result;
	}
	
	
    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }

    
    public static String zyRandom(){
    	StringBuilder str=new StringBuilder();//定义变长字符串
		Random random=new Random();
		//随机生成数字，并添加到字符串
		for(int i=0;i<8;i++){
		    str.append(random.nextInt(10));
		}
		//将字符串转换为数字并输出
		int num=Integer.parseInt(str.toString());
    	return "ZY-"+num;
    }

	public static void main(String[] args) {
		Integer num2 = 4;
		Integer b = 5;
		Integer c = 10;
		Integer d = num2/c;
		Integer e = d*c;
		Integer f = num2-e;
		Integer g = f/b;
		Integer h = d*3;
		Integer i2 = g*1;
		Integer i3 = h+i2;
		if (num2 >= b && num2 < c){
			i3 = num2 + num2/b;
			System.out.println(i3);
		}else{
			System.out.println(i3+num2);
		}
	}
    
}
