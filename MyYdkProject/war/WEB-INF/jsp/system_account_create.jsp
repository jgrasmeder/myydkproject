<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
        <spring:bind path="dataForm.name">
        <c:if test="${not empty status.value}">
    	<input 
    		type="text"
    		id="input_name" 
    		name="<c:out value="${status.expression}"/>"   
    		style="width:150px; text-align:left;" 
    		onfocus="clear_input('input_name','#e5fff3');" 
    		onblur="setbg('input_name','white', '${status.value}');"
    		onChange="check_input('input_name', 'white');"
    		
    		value="<c:url value="${status.value}"/>"/>
    	</c:if>
    	<c:if test="${empty status.value}">
    	<input 
    		type="text"
    		id="input_name" 
    		name="<c:out value="${status.expression}"/>"   
    		style="width:150px; text-align:left;" 
    		onfocus="clear_input('input_name','#e5fff3');" 
    		onblur="setbg('input_name','white', '请输入用户名');"
    		onChange="check_input('input_name', 'white');"
    		value="请输入用户名"/>
    	</c:if>
    	</spring:bind>
    	<form:errors path="name" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">权限</td>
    <td style="text-align:left;" colspan="2">
    	<form:select path="roleId" cssStyle="width:150px">
    		<c:forEach var="ydkRole" items="${ydkRoleList}">
    			<option value="${ydkRole.id}">${ydkRole.roleName}</option>
    		</c:forEach>
    	</form:select>
    </td>
  </tr>
  
  
  <tr>
    <td style="text-align:right">密码</td>
    <td style="text-align:left" colspan="2">
    	<form:password path="password" cssStyle="width:150px"/>
    	&nbsp;
    	<form:errors path="password" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr>
    <td style="text-align:right">确认密码</td>
    <td style="text-align:left" colspan="2">
    	<form:password path="repeatedPw" cssStyle="width:150px"/>
    	&nbsp;
    	<form:errors path="repeatedPw" cssClass="errors"/>
    </td>
  </tr>
  
   <tr>
    <td style="text-align:right">邮箱</td>
    <td style="text-align:left" colspan="2">
     <spring:bind path="dataForm.email">
        <c:if test="${not empty status.value}">
    	<input 
    		type="text"
    		id="input_email" 
    		name="<c:out value="${status.expression}"/>"   
    		style="width:150px;" 
    		onfocus="clear_input('input_email','#e5fff3');" 
    		onblur="setbg('input_email','white', 'example@ydk.com');"
    		onChange="check_input('input_email', 'white');"
    		value="<c:url value="${status.value}"/>"/>
    	</c:if>
    	<c:if test="${empty status.value}">
    	<input 
    		type="text"
    		id="input_email" 
    		name="<c:out value="${status.expression}"/>"   
    		style="width:150px;" 
    		onfocus="clear_input('input_email','#e5fff3');" 
    		onblur="setbg('input_email','white', 'example@ydk.com');"
    		onChange="check_input('input_email', 'white');"
    		value="example@ydk.com"/>
    	</c:if>
    </spring:bind>
    <form:errors path="email" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">状态</td>
    <td style="text-align:left;" colspan="2">
    	<form:select path="isActived" cssStyle="width:150px">
    			<option value="false">未激活</option>
    			<option value="true">激活</option>
    	</form:select>
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