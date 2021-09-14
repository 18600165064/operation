package com.data.display.controller.baidu.ueditor.define;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义请求action类型
 * @author hancong03@baidu.com
 *
 */
@SuppressWarnings("serial")
public final class ActionMap {

	public static final Map<String, Integer> mapping;
	// 获取配置请求
	public static final int CONFIG = 0;
	public static final int UPLOAD_IMAGE = 1;
	public static final int UPLOAD_SCRAWL = 2;
	public static final int UPLOAD_VIDEO = 3;
	public static final int UPLOAD_FILE = 4;
	public static final int CATCH_IMAGE = 5;
	public static final int LIST_FILE = 6;
	public static final int LIST_IMAGE = 7;
	
	static {
		mapping = new HashMap<String, Integer>(){{
			put( "config", com.baidu.ueditor.define.ActionMap.CONFIG );
			put( "uploadimage", com.baidu.ueditor.define.ActionMap.UPLOAD_IMAGE );
			put( "uploadscrawl", com.baidu.ueditor.define.ActionMap.UPLOAD_SCRAWL );
			put( "uploadvideo", com.baidu.ueditor.define.ActionMap.UPLOAD_VIDEO );
			put( "uploadfile", com.baidu.ueditor.define.ActionMap.UPLOAD_FILE );
			put( "catchimage", com.baidu.ueditor.define.ActionMap.CATCH_IMAGE );
			put( "listfile", com.baidu.ueditor.define.ActionMap.LIST_FILE );
			put( "listimage", com.baidu.ueditor.define.ActionMap.LIST_IMAGE );
		}};
	}
	
	public static int getType ( String key ) {
		return com.baidu.ueditor.define.ActionMap.mapping.get( key );
	}
	
}
