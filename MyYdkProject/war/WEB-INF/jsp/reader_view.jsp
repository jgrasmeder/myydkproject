<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body>
<style>
textarea
{
width:100%;
height:100%;
}
</style>
<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>


<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
  读者
</div>

<div style="float:left; width:49%;">
	<table class="table2" width="95%"  height="auto">
		<tr  align="center">
		  <td>我的图像</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<c:choose>
			  <c:when test="${not empty dataForm.instance.portrait}">
			  	<a title="点击察看大图" href="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.portrait}"/>">
			  	
			  	<img style="height:40px;width:40px;" src="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.portrait}"/>"/>
			  	</a>
			  </c:when>
			  <c:otherwise>
			  	无图片
			  </c:otherwise>
			</c:choose>
		  </td>
		</tr>
		
	</table>
	
</div>
<div style="float:right; width:50%;">
<table class="table2" align="center" width="95%" border="0" bgcolor="#C9E7E6">

  
  
  <tr align="center">	
    <td  style="width:35%; text-align:right">用户名</td>
    <td style="text-align:left;" colspan="2">
        ${dataForm.instance.nickName}
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">服务商</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.partner.name}
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">邮箱</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.email}
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">姓名</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.realName}
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">手机</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.mobilePhone}
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">激活</td>
    <td style="text-align:left;" colspan="2">
    	<c:if test="${dataForm.instance.isActived}">是</c:if>
    	<c:if test="${!dataForm.instance.isActived}">否</c:if>
    </td>
  </tr>
 
  
  
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</div>

<div class="clear"></div>


</div>
<!--End of Main Content -->

<!-- Card Container bottom  -->
<%@ include file="/WEB-INF/jsp/common_card_bottom.jsp"%>
<!-- body bottom -->
<%@ include file="/WEB-INF/jsp/common_include_bottom.jsp"%>