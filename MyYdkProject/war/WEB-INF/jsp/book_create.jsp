<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<body>
<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>


<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
 上传书籍
</div>
<c:if test="${!empty operationMsg}">
  	<div align="left"><b><font color="RED"><c:url value="${operationMsg}"/></font></b></div>
</c:if>
<form:form id="dataForm" modelAttribute="dataForm" enctype="multipart/form-data">
<div style="float:left; width:49%;">
	<table class="table2" width="95%"  height="auto">
		
		<tr align="center">
		  <td>书籍(.epub)</td>
		</tr>
		<tr  align="center">
		  <td >
		  <div id="uploadContainer">
		  	<div style="height:40px;">
		  	<div id="upload1" style='float:left;'></div>
		  	<input type="file" name="contentFile1"/>
    		<input type="hidden" id="fileCount" name="fileCount" value="1">
    		</div>
		  </div>
		  </td>
		</tr>
		<tr align="right">
		 <td>
		  
		  <input type="button" value="添加书籍" 
		  		onclick="javascript:btn_click('fileCount', 'uploadContainer', 'contentFile');">

		  </td>
		</tr>
	</table>
	
</div>
<div style="float:right; width:50%;">
<table class="table2" align="center" width="95%" border="0" bgcolor="#C9E7E6">
  <tr>
  <td style="width:45%;">&nbsp;</td>
  <td>&nbsp;</td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">Key</td>
    <td style="text-align:left;" colspan="2">
        
    	<form:input path="instance.title" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入书籍Key');"
    	onchange="check_input_p(this, 'white');"/>
    	<script language="javascript"> document.getElementById('instance.title').value="请输入书籍Key";</script>
    	<form:errors path="instance.title" cssClass="errors"/>
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