<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.data.display.controller.baidu.ueditor.ActionEnter"
	
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
// 	String rootPath = application.getRealPath( "/" );
	String rootPath=this.getServletConfig().getServletContext().getResource("/").getPath();
	alert(rootPath);
// 	String rootPath = "E://upload";
// 	rootPath = "E:\\upload";
	System.out.println("rootPath=" + rootPath);
	System.out.println("contextPath=" + request.getContextPath());
	System.out.println("uri=" + request.getRequestURI());
	
	String json = new ActionEnter( request, rootPath ).exec();
	System.out.println( json );
	out.write(  json );
%>