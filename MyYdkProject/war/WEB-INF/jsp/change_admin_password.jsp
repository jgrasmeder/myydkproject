<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>

<body>

<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>


<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
  创建帐户
</div>

<form:form id="dataForm" modelAttribute="dataForm" >
<table class="table2" align="center" width="95%" border="0" bgcolor="#C9E7E6">
  <tr>
  <td style="width:45%;">&nbsp;</td>
  <td>&nbsp;</td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">用户名</td>
    <td style="text-align:left;" colspan="2">
    	${userLogIn.name}
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">权限</td>
    <td style="text-align:left;" colspan="2">
    	${userLogIn.ydkRole.roleName}
    </td>
  </tr>
  
  
  <tr>
    <td style="text-align:right">旧密码</td>
    <td style="text-align:left" colspan="2">
    	<form:password path="password" cssStyle="width:150px"/>
    	&nbsp;
    	<form:errors path="password" cssClass="errors"/>
    </td>
  </tr>
  <tr>
    <td style="text-align:right">新密码</td>
    <td style="text-align:left" colspan="2">
    	<form:password path="passwordNew" cssStyle="width:150px"/>
    	&nbsp;
    	<form:errors path="passwordNew" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr>
    <td style="text-align:right">确认新密码</td>
    <td style="text-align:left" colspan="2">
    	<form:password path="repeatedPw" cssStyle="width:150px"/>
    	&nbsp;
    	<form:errors path="repeatedPw" cssClass="errors"/>
    </td>
  </tr>
  
   <tr>
    <td style="text-align:right">邮箱</td>
    <td style="text-align:left" colspan="2">
     ${userLogIn.email}
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">状态</td>
    <td style="text-align:left;" colspan="2">
    	<c:choose>
    	<c:when test="${userLogIn.isActived}">
    		激活
    	</c:when>
    	<c:otherwise>
    		未激活
    	</c:otherwise>
    	</c:choose>
    </td>
  </tr>
  
  
  <tr>
	<td style="text-align:right">&nbsp;</td>
	<td style="text-align:left">
	<span><div style="float:left;"><input type="submit" class="button_blue" value="提交"/>
		&nbsp;</div>
	<a href="#" onclick="clear_form(('dataForm'));return false;">
			<div class="link_button_blue" style="float:left;">
			重写</div>
	</a>
	</span>
  </tr>
  
  
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</form:form>


</div>
<!--End of Main Content -->

<!-- Card Container bottom  -->
<%@ include file="/WEB-INF/jsp/common_card_bottom.jsp"%>
<!-- body bottom -->
<%@ include file="/WEB-INF/jsp/common_include_bottom.jsp"%>