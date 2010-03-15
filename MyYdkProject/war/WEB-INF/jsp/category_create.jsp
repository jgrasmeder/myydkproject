<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>

<body>

<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>


<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
  创建书籍分类
</div>

<form:form id="dataForm" modelAttribute="dataForm" >
<table class="table2" align="center" width="95%" border="0" bgcolor="#C9E7E6">
  <tr>
  <td style="width:45%;">&nbsp;</td>
  <td>&nbsp;</td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">分类名</td>
    <td style="text-align:left;" colspan="2">
        <form:input path="instance.name" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入分类名');"
    	onchange="check_input_p(this, 'white');"/>
    	<script language="javascript"> document.getElementById('instance.name').value="请输入分类名";</script>
    	<form:errors path="instance.name" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">选择书馆</td>
    <td style="text-align:left;" colspan="2">
    	<form:select path="instance.library.id" cssStyle="width:150px">
    		<c:forEach var="type" items="${typeList}">
    			<option value="${type.id}">${type.name}</option>
    		</c:forEach>
    	</form:select>
    	<form:errors path="instance.library.id" cssClass="errors"/>
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