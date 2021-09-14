package com.data.display.util;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;

/**
 * 乱78z
 * @author zouooh
 *
 */
public final class BurroKit {

    public static String captcha_attr = "burro_captcha";

    public static Timestamp current() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String getRemoteIp() {
        HttpServletRequest request = Mvcs.getReq();
        return getRemoteIp(request);
    }
    
    public static String getRemoteIp(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null)
            ip = "";
        return ip;
    }

    public static int[] split(String ids) {
        String[] userIds = Strings.splitIgnoreBlank(ids);
        int[] t = new int[userIds.length];
        for (int i = 0; i < t.length; i++) {
            t[i] = Integer.parseInt(userIds[i]);
        }
        return t;
    }

//    public static int userId() {
//        int uid = 0;
//        Object u;
//        try {
//            u = SecurityUtils.getSubject().getPrincipal();
//        }
//        catch (Throwable e) {
//            return 0;
//        }
//        if (u != null) {
//            if (u instanceof User) {
//                uid = ((User) u).getUser_id();
//            } else if (u instanceof Number) {
//                uid = ((Number) u).intValue();
//            }
//        }
//        return uid;
//    }
//
//    public static void doLogin(AuthenticationToken token, int memberId) {
//        Subject subject = SecurityUtils.getSubject();
//        if (token != null)
//            subject.login(token);
//        subject.getSession().setAttribute(NutShiro.SessionKey, memberId);
//    }

    public static String filedsRegOf(String... fileds) {
        String temp = "^";
        for (String string : fileds) {
            temp += string;
            temp += "|";
        }
        temp = temp.substring(0, temp.length() - 1);
        temp += "$";
        return temp;
    }

//    public static boolean isSa() {
//        Subject subject = SecurityUtils.getSubject();
//        return subject.hasRole("admin");
//    }

    public static void kickout(long user_id) {
        Mvcs.getServletContext().setAttribute("kick" + user_id, "1");
    }

//    public static void logout() {
//        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            subject.logout();
//        }
//    }

    /**
     * 大于
     * @param a
     * @param b
     * @return
     */
    public static boolean ifAGtB(BigDecimal a,BigDecimal b){
        return a.compareTo(b) > 0;
    }

    /**
     * 大于等于
     * @param a
     * @param b
     * @return
     */
    public static boolean ifAGtOrEqB(BigDecimal a,BigDecimal b){
        return a.compareTo(b) >= 0;
    }


    /**
     * 等于
     * @param a
     * @param b
     * @return
     */
    public static boolean ifAEqB(BigDecimal a,BigDecimal b){
        return a.compareTo(b) == 0;
    }

    /**
     * 小于
     * @param a
     * @param b
     * @return
     */
    public static boolean ifALtB(BigDecimal a,BigDecimal b){
        return a.compareTo(b) < 0;
    }

    /**
     * 小于等于
     * @param a
     * @param b
     * @return
     */
    public static boolean ifALtOrEqB(BigDecimal a,BigDecimal b){
        return a.compareTo(b) <= 0;
    }




}
