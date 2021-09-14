package com.data.display.util.wx.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.View;

import com.data.display.util.Wxs;
import com.data.display.util.wx.bean.WxOutMsg;

public class WxView implements View {
	
	public static final View me = new WxView();
	
	public void render(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Throwable {
		if (obj == null) {
			return;
		}
		Wxs.asXml(resp.getWriter(), (WxOutMsg) obj);
	}
}