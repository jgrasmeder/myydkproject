<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>阅读客管理系统</title>
<script type="text/javascript">
<%--for bbs --%>
function setbg_p(e, color, reminder)
{

	e.style.background=color;
	if (e.value == "")
	{
		e.value = reminder;
		e.onfocus = function(){clear_input_p(e, '#e5fff3')};
	}
return true;
}


function setbg(id, color, reminder)
{

	document.getElementById(id).style.background=color;
	if (document.getElementById(id).value == "")
	{
		document.getElementById(id).value = reminder;
		document.getElementById(id).onfocus = function(){clear_input(id, '#e5fff3')};
	}
return true;
}

function clear_form(form_id)
{ 
	form = document.getElementById(form_id);
    for (i=0; i<form.length; i++)
    {
        if (form.elements[i].type == "text" || form.elements[i].type == "textarea"
            || form.elements[i].type =="password")
        {
        	form.elements[i].value = "";
        }
    }
}


function clear_input_p(e, color) 
{ 
    e.value = ""; 
    e.style.background=color;
    e.onfocus = null; 
} 

function clear_input(id, color) 
{ 
    document.getElementById(id).value = ""; 
    document.getElementById(id).style.background=color;
    document.getElementById(id).onfocus = null; 
} 

function check_input_p(e, color) 
{ 
    if(e.value == "")
    {
    	e.onfocus = function(){clear_input_p(e, '#e5fff3')};
    } 
    else
    {
        e.onfocus = null; 
    }
    
} 

function check_input(id, color) 
{ 
    if(document.getElementById(id).value == "")
    {
    	document.getElementById(id).onfocus = function(){clear_input(id, '#e5fff3')};
    } 
    else
    {
        document.getElementById(id).onfocus = null; 
    }
    
} 

<%-- Check all the CheckBox of a form --%>
function checkAll(form_p, checkbox_name, control_p){

	var checkValue = control_p.checked;
	for (j=0; j< form_p.length; j++)
	{
		if (form_p.elements[j].name == (checkbox_name))
		{
			form_p.elements[j].checked = checkValue;
		}
	}
}
</script>
<style type="text/css">

<!--
.main-pad {
	background-image: url(images/login_bg.png);
	background-repeat: no-repeat;
	width:520px;
	height:320px;
	vertical-align:middle;

}
.login-layout {
	width:510px;
	height:300px;
	border:0px;
}
body {
	/*background-color: #FFFFFF; #C9E7E6;*/
	padding-left:10px;
	padding-right:10px;

}
.style1 {font-size: 36px}
-->
</style>
</head>

<body>
<table width="619" height="471" border="0" align="center">
  <tr>
    <td width="59">&nbsp;</td>
    <td width="125"><img src="images/ydk_logo.gif" alt="logo" width="125" height="50" /></td>
    <td width="282"><span class="style1">管理系统登录</span></td>
    <td width="8">&nbsp;</td>
  </tr>
  <tr>
    <td height="23" colspan="4" background="images/login_top.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="23">&nbsp;</td>
    <td colspan="2"><c:if test="${!empty operationMsg}">
  	<div align="center"><b><font color="RED"><c:url value="${operationMsg}"/></font></b></div>
	</c:if></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" rowspan="6" class="main-pad">
    

	<form:form id="form-login" name="form-login" modelAttribute="user" action="/MyYdkProject/login.do">
      <table width="489" height="272" border="0" class="login-layout">
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="2">&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="2"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td width="6">&nbsp;</td>
          <td width="170"><div align="right">用户名</div></td>
          <td colspan="2"><div align="left">
            <form:input path="accountName" maxlength="40" cssStyle="width:150px;"
	            onfocus="clear_input_p(this,'#e5fff3');"
		    	onblur="setbg_p(this,'white', '请输入用户名');"
		    	onchange="check_input_p(this, 'white');"/>
			<form:errors path="accountName" cssClass="errors"/>
          </div></td>
          <td width="134">&nbsp;</td>
          <td width="14">&nbsp;</td>
        </tr>
        <tr>
          <td height="32">&nbsp;</td>
          <td width="170"><div align="right">密码</div></td>
          <td colspan="2"><div align="left">
            <form:password path="password" maxlength="40" cssStyle="width:150px;"/>
			<form:errors path="password" cssClass="errors"/>
          </div></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td 170"><div align="right">验证码</div></td>
          <td colspan="2"><div align="left">
            <form:input path="verificationValue" maxlength="40" cssStyle="width:150px;"/>
			<form:errors path="verificationValue" cssClass="errors"/>
          </div></td>
          <td><img id="randomImage" src="<c:url value="/showRandomImage.do"/>" width="116" height="25"/></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="2">&nbsp;</td>
          <td><a href="<c:url value="/login.do"/>" >看不清？  </a></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td width="99"><input name="submit" type="image" src="images/login-button.png" id="submit" value="登陆" /></td>
          <td width="61">
          	<input name="submit" onclick="document.getElementById('form-login').action='<c:url value="/forgetPassword.do"/>'" 
          		type="image" src="images/forget-pass-button.png"  
          		value="忘记密码" /></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td colspan="2"></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
     
      
      </form:form>
      </td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
