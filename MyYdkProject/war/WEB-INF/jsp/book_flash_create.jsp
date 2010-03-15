<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<body>
<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>


<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
  创建书籍
</div>
<c:if test="${!empty operationMsg}">
  	<div align="left"><b><font color="RED"><c:url value="${operationMsg}"/></font></b></div>
</c:if>
<form id="dataForm" enctype="multipart/form-data" method="POST" action="flashAddBook.do">
<div style="float:left; width:49%;">
	<table class="table2" width="95%"  height="auto">
		<tr  align="center">
		  <td>标识图片(小)</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<div id="imageSmall" style='float:left;'></div>
		  	<input type="file" name="imageSmall" 
		  		onchange="showUploadedPic(this.value, 'imageSmall');"/></td>
		</tr>
		<tr  align="center">
		  <td>标识图片(大)</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<div id="imageLarge" style='float:left;'></div>
		  	<input type="file" name="imageLarge" 
		  		onchange="showUploadedPic(this.value, 'imageLarge');"/></td>
		</tr>
		<tr  align="center">
		  <td>3D图片(小)</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<div id="image3DSmall" style='float:left;'></div>
		  	<input type="file" name="image3DSmall" 
		  		onchange="showUploadedPic(this.value, 'image3DSmall');"/></td>
		</tr>
		<tr  align="center">
		  <td>3D图片(大)</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<div id="image3DLarge" style='float:left;'></div>
		  	<input type="file" name="image3DLarge" 
		  		onchange="showUploadedPic(this.value, 'image3DLarge');"/></td>
		</tr>
		<tr  align="center">
		  <td>内容(.Epub)</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<input type="file" name="bookContent"/></td>
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
        
    	<input type="text" id="title" name="title" 
    	onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入标题');"
    	onchange="check_input_p(this, 'white');"/>
    	<script language="javascript"> document.getElementById('title').value="请输入标题";</script>

    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">YdkBookId</td>
    <td style="text-align:left;" colspan="2">
        
    	<input type="text" id="ydkBookId" name="ydkBookId" 
    	onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入标题');"
    	onchange="check_input_p(this, 'white');"/>
    	<script language="javascript"> document.getElementById('ydkBookId').value="请输入标题";</script>
 
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">类型</td>
    <td style="text-align:left;" colspan="2">
    	<select name="category" style="width:150px">
    		<c:forEach var="type" items="${typeList}">
    			<option value="${type.name}">${type.name}</option>
    		</c:forEach>
    	<select>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">时间</td>
    <td style="text-align:left;" colspan="2">
    <input type="text" id="publishDate" name="publishDate" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '2010-01-01');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('publishDate').value="2010-01-01";</script>
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
</form>


</div>
<!--End of Main Content -->

<!-- Card Container bottom  -->
<%@ include file="/WEB-INF/jsp/common_card_bottom.jsp"%>
<!-- body bottom -->
<%@ include file="/WEB-INF/jsp/common_include_bottom.jsp"%>