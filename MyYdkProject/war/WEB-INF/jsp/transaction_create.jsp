<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<body>
<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>


<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
授权书籍
</div>
<c:if test="${!empty operationMsg}">
  	<div align="left"><b><font color="RED"><c:url value="${operationMsg}"/></font></b></div>
</c:if>
<form:form id="dataForm" modelAttribute="dataForm" enctype="multipart/form-data">

<div style="float:right; width:99%;">
<table class="table2" align="center" width="95%" border="0" bgcolor="#C9E7E6">
  <tr>
  <td style="width:45%;">&nbsp;</td>
  <td>&nbsp;</td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">读者用户名</td>
    <td style="text-align:left;" colspan="2">
        
    	<form:input path="readerName" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入读者名');"
    	onchange="check_input_p(this, 'white');"/>
    	<script language="javascript"> document.getElementById('readerName').value="请输入读者名";</script>
    	<form:errors path="readerName" cssClass="errors"/>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">书籍YdkId</td>
    <td style="text-align:left;" colspan="2">
    <form:input path="ydkBookId" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入书籍Id');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('ydkBookId').value="请输入书籍Id";</script>
    <form:errors path="ydkBookId" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">类型</td>
    <td style="text-align:left;" colspan="2">
    	<form:select path="instance.transactionType.id" cssStyle="width:150px">
    		<c:forEach var="type" items="${typeList}">
    			<option value="${type.id}">${type.type}</option>
    		</c:forEach>
    	</form:select>
    	<form:errors path="instance.transactionType.id" cssClass="errors"/>
    	
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
</div>

<div class="clear"></div>
</form:form>


</div>
<!--End of Main Content -->

<!-- Card Container bottom  -->
<%@ include file="/WEB-INF/jsp/common_card_bottom.jsp"%>
<!-- body bottom -->
<%@ include file="/WEB-INF/jsp/common_include_bottom.jsp"%>