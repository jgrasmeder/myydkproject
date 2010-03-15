<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<body>
<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>


<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
  创建读者
</div>
<c:if test="${!empty operationMsg}">
  	<div align="left"><b><font color="RED"><c:url value="${operationMsg}"/></font></b></div>
</c:if>
<form:form id="dataForm" modelAttribute="dataForm" enctype="multipart/form-data">
<div style="float:left; width:49%;">
	<table class="table2" width="95%"  height="auto">
		<tr  align="center">
		  <td>我的图像</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<div id="upload" style='float:left;'></div>
		  	<input type="file" name="portraitImage" 
		  		onchange="showUploadedPic(this.value, 'upload');"/></td>
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
    <td  style="text-align:right">用户名</td>
    <td style="text-align:left;" colspan="2">
        
    	<form:input path="instance.nickName" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入用户名');"
    	onchange="check_input_p(this, 'white');"/>
    	<script language="javascript"> document.getElementById('instance.nickName').value="请输入用户名";</script>
    	<form:errors path="instance.nickName" cssClass="errors"/>
    </td>
  </tr>
  
  
  
  
  <tr align="center">	
    <td  style="text-align:right">服务商</td>
    <td style="text-align:left;" colspan="2">
    	<form:select path="instance.partner.id" cssStyle="width:150px">
    		<c:forEach var="type" items="${typeList}">
    			<option value="${type.id}">${type.name}</option>
    		</c:forEach>
    	</form:select>
    	<form:errors path="instance.partner.id" cssClass="errors"/>
    	
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">邮箱</td>
    <td style="text-align:left;" colspan="2">
    <form:input path="instance.email" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', 'example@ydk.com');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('instance.email').value="example@ydk.com";</script>
    <form:errors path="instance.email" cssClass="errors"/>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">姓名</td>
    <td style="text-align:left;" colspan="2">
    <form:input path="instance.realName" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '姓名');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('instance.realName').value="姓名";</script>
    <form:errors path="instance.email" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">生日</td>
    <td style="text-align:left;" colspan="2">
    <form:input path="instance.birthDate" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '1980-01-01');"
    	onchange="check_input_p(this, 'white');"/>
    	<script language="javascript"> document.getElementById('instance.birthDate').value="1980-01-01";</script>
    <form:errors path="instance.birthDate" cssClass="errors"/>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">手机</td>
    <td style="text-align:left;" colspan="2">
    <form:input path="instance.mobilePhone" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入相关信息');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('instance.mobilePhone').value="请输入相关信息";</script>
    <form:errors path="instance.mobilePhone" cssClass="errors"/>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">固定电话</td>
    <td style="text-align:left;" colspan="2">
     <form:input path="instance.fixPhone" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入相关信息');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('instance.fixPhone').value="请输入相关信息";</script>
    <form:errors path="instance.fixPhone" cssClass="errors"/>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">获得密码问提</td>
    <td style="text-align:left;" colspan="2">
     <form:input path="instance.securityQuestion" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入相关信息');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('instance.securityQuestion').value="请输入相关信息";</script>
    <form:errors path="instance.securityQuestion" cssClass="errors"/>
    </td>
  </tr>
  <tr align="center">	
    <td  style="text-align:right">获得密码答案</td>
    <td style="text-align:left;" colspan="2">
     <form:input path="instance.sequrityAnswer" onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入相关信息');"
    	onchange="check_input_p(this, 'white');"/>
    <script language="javascript"> document.getElementById('instance.sequrityAnswer').value="请输入相关信息";</script>
    <form:errors path="instance.sequrityAnswer" cssClass="errors"/>
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">状态</td>
    <td style="text-align:left;" colspan="2">
    	<form:select path="instance.isActived" cssStyle="width:150px">
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
</div>

<div class="clear"></div>
</form:form>


</div>
<!--End of Main Content -->

<!-- Card Container bottom  -->
<%@ include file="/WEB-INF/jsp/common_card_bottom.jsp"%>
<!-- body bottom -->
<%@ include file="/WEB-INF/jsp/common_include_bottom.jsp"%>