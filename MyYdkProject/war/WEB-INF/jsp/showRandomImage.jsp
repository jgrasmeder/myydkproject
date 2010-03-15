<%@ page pageEncoding="UTF-8" contentType="image/jpeg" 
	import="java.awt.image.BufferedImage,javax.imageio.ImageIO" %>
<jsp:useBean id="rc" class="com.ydk.account.utility.YdkRandomImage"/>

<%
//设置页面不缓存
//response.reset();
response.setContentType("image/jpeg");
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
//在内存中创建图象宽60 高18 4个字符 0条干扰线
%>