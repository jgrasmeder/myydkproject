<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>

<body>

<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>


<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
  创建交易类型
</div>

<form:form id="dataForm" modelAttribute="dataForm" >
<table class="table2" align="center" width="95%" border="0" bgcolor="#C9E7E6">
  <tr>
  <td style="width:45%;">&nbsp;</td>
  <td>&nbsp;</td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">类型名</td>
    <td style="text-align:left;" colspan="2">
    	<!--  
        <spring:bind path="dataForm.instance.type">
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
    		onblur="setbg('input_name','white', '请输入相关信息');"
    		onChange="check_input('input_name', 'white');"
    		value="请输入相关信息"/>
    	</c:if>
    	</spring:bind>
    	-->
    	<form:input path="instance.type" />
    	<form:errors path="instance.type" cssClass="errors"/>
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
	</td>
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