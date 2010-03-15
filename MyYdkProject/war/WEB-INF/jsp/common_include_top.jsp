<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<title>悦读客管理页面</title>

<style type="text/css">
<!--
.style1 {
	font-size: xx-large;
	color: #FF0000;
	height: 48px;
	vertical-align: middle;
}
body {
	background-color:#C9E7E6;
}
-->
</style>
<link rel="stylesheet" rev="stylesheet" href="contentStyle.css" media="screen"/>
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

function showUploadedPic(value, divId)
{
	document.getElementById(divId).innerHTML="<img width='40px' height='40px'  src='" + value +"'/>";
}

function btn_click(imgCountId, uploadDivId, fileNamePrefix){
	  var fileNo=Number(document.getElementById(imgCountId).value)+1;
	  newDiv=document.createElement("div")
	  newDiv.id="divFile"+fileNo;
	  newDiv.style.height="40px";
	  newDiv.style.align="right";
	  document.getElementById(uploadDivId).appendChild(newDiv)
	  document.getElementById(imgCountId).value=fileNo;
	  newDiv.innerHTML="<div id='" + fileNamePrefix + fileNo 
	  	+ "' style='float:left; height:40px;'></div><input type='file' name='"
	  	+ fileNamePrefix+fileNo+ "' onchange='showUploadedPic(this.value,this.name)';/>";
	 }
</script>
</head>
