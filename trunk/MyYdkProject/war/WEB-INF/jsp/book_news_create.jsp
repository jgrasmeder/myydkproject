<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<body>
<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>


<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
  创建书评
</div>
<c:if test="${!empty operationMsg}">
  	<div align="left"><b><font color="RED"><c:url value="${operationMsg}"/></font></b></div>
</c:if>
<form:form id="dataForm" modelAttribute="dataForm" enctype="multipart/form-data">
<div style="float:left; width:49%;">
	<table class="table2" width="95%"  height="auto">
		<tr  align="center">
		  <td>标识图片</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<div id="upload" style='float:left;'></div>
		  	<input type="file" name="titleImage" 
		  		onchange="showUploadedPic(this.value, 'upload');"/></td>
		</tr>
		<tr  align="center">
		  <td>内容(.txt)</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<input type="file" name="contentFile"/></td>
		</tr>
		<tr align="center">
		  <td>内容图片</td>
		</tr>
		<tr  align="center">
		  <td >
		  <div id="uploadImgContainer">
		  	<div style="height:40px;">
		  	<div id="upload1" style='float:left;'></div>
		  	<input type="file" name="contentImage1" 
		  		onchange="showUploadedPic(this.value, 'upload1');"/>
    		<input type="hidden" id="fileCount" name="fileCount" value="1">
    		</div>
		  </div>
		  </td>
		</tr>
		<tr align="right">
		 <td>
		  
		  <input type="button" value="添加内容图片" 
		  		onclick="javascript:btn_click('fileCount', 'uploadImgContainer', 'contentImage');">

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
    <td  style="text-align:right">标题</td>
    <td style="text-align:left;" colspan="2">
        
    	<form:input path="instance.title" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入标题');"
    	onchange="check_input_p(this, 'white');"/>
    	<script language="javascript"> document.getElementById('instance.title').value="请输入标题";</script>
    	<form:errors path="instance.title" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">类型</td>
    <td style="text-align:left;" colspan="2">
    	<form:select path="instance.bookNewsCategory.id" cssStyle="width:150px">
    		<c:forEach var="type" items="${typeList}">
    			<option value="${type.id}">${type.name}</option>
    		</c:forEach>
    	</form:select>
    	<form:errors path="instance.bookNewsCategory.id" cssClass="errors"/>
    	
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">时间</td>
    <td style="text-align:left;" colspan="2">
    <form:input path="instance.date" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '2010-01-01');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('instance.date').value="2010-01-01";</script>
    <form:errors path="instance.date" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">简介</td>
    <td style="text-align:left;" colspan="2">
    <form:input path="instance.introduction" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入简介信息');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('instance.introduction').value="请输入简介信息";</script>
    <form:errors path="instance.introduction" cssClass="errors"/>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">编辑</td>
    <td style="text-align:left;" colspan="2">
    <form:input path="instance.editor" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入编辑信息');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('instance.editor').value="请输入编辑信息";</script>
    <form:errors path="instance.editor" cssClass="errors"/>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">顺序</td>
    <td style="text-align:left;" colspan="2">
    <form:input path="instance.order" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '1');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('instance.order').value="1";</script>
    <form:errors path="instance.order" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">推荐</td>
    <td style="text-align:left;" colspan="2">
    	<form:select path="instance.recommanded" cssStyle="width:150px">
    			<option value="false">否</option>
    			<option value="true">是</option>
    	</form:select>
    <form:errors path="instance.recommanded" cssClass="errors"/>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">最新</td>
    <td style="text-align:left;" colspan="2">
    	<form:select path="instance.isNew" cssStyle="width:150px">
    			<option value="false">否</option>
    			<option value="true">是</option>
    	</form:select>
    <form:errors path="instance.isNew" cssClass="errors"/>
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