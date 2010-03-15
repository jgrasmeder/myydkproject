<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>

<body>

<style>
input
{
width:80px;
}
</style>
<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>

<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
 合作伙伴帐户列表
</div>
<div align="right" style="display:block;padding-right:5px;">
共${pageDataList.nrOfElements}个帐户
</div>
<c:if test="${!empty operationMsg}">
  	<div align="left"><b><font color="RED"><c:url value="${operationMsg}"/></font></b></div>
</c:if>
<form:form id="pageDataForm" name="pageDataForm" modelAttribute="dataForm" method="POST">
<table class="table2"  width="100%" border="0" cellspacing="0" >
  <tr class="bgtitle" style="height:20px;">
  <td>
  	<input name="selectAll" type="checkbox" style="width:20px;"
  		onClick="checkAll(document.getElementById('pageDataForm'), 'keys',this)" value="all"/>
  	<span>全选</span></td>
  <td>标题</td>
  <td>类型</td>
  <td>受访者</td>
  <td>记者</td>
  <td>编辑</td>
  <td>地点</td>
  <td>简介</td>
  <td>问答方式</td>
  <td class="end">操作</td>
  </tr>
  <c:forEach var="item" items="${pageDataList.pageList}">

  <tr align="center">
  <c:choose>
  	<c:when test="${dataForm.instance.id == item.id}">
  	  <td width="10px">&nbsp;</td>
	  <td><spring:bind path="dataForm.instance.title">
	  		<input 
    		type="text"
    		id="input_name" 
    		name="<c:out value="${status.expression}"/>"   
    		value="<c:url value="${status.value}"/>"/>
    		</spring:bind>
    	   <form:errors path="instance.title" cssClass="errors"/>
      </td>
	  <td>
	  	<form:select path="instance.interviewCategory.id" cssStyle="width:80px">
    		<c:forEach var="type" items="${typeList}">
    			<option value="${type.id}" <c:if test="${type.id == dataForm.instance.interviewCategory.id}">selected</c:if>>${type.name}</option>
    		</c:forEach>
    	</form:select>
      </td>
      
      <td>
      	<form:input path="instance.interviewer" />
	      <form:errors path="instance.interviewer" cssClass="errors"/>
      </td>

      <td>
      	<form:input path="instance.reporter" />
	      <form:errors path="instance.reporter" cssClass="errors"/>
      </td>
      <td>
      	<form:input path="instance.editor" />
	      <form:errors path="instance.editor" cssClass="errors"/>
      </td>
      <td>
      	<form:input path="instance.location" />
	      <form:errors path="instance.location" cssClass="errors"/>
      </td>
      <td >
      	<form:input path="instance.introduction" />
	    <form:errors path="instance.introduction" cssClass="errors"/>
      </td>
	  
	  <td>
	  	<form:select path="instance.isQa" cssStyle="width:40px">
	  			<option value="false" >否</option>
	  			<option value="true" <c:if test="${dataForm.instance.isQa}">selected</c:if> >是</option>
    			
    	</form:select>
    	<form:errors path="instance.isQa" cssClass="errors"/>
	  
	  </td>
	  <td>
	  	<form:hidden path="instance.id"/>
	  	<a href="#" onClick="document.pageDataForm.action='<c:url value="/modifyInterview.do"/>';
		  document.pageDataForm.submit();return true;">保存</a></td>
	</c:when>
	<c:otherwise>
	  <td><form:checkbox path="keys" value="${item.id}"/></td>
	  <td><a href="<c:url value="/viewInterview.do?itemId=${item.id}"/>">${item.title}</a></td>
	  <td>${item.interviewCategory.name}</td>
	  <td>${item.interviewer}</td>
	  <td>${item.reporter}</td>
	  <td>${item.editor}</td>
	  <td>${item.location}</td>
	  <td width="160px;">${item.introduction }</td>
	  <td><c:if test ="${!item.isQa}">否</c:if>
	  	<c:if test ="${item.isQa}">是</c:if></td>
	  <td><a href="<c:url value="/listInterview.do?itemId=${item.id}&pageNumber=${pageDataList.page}"/>">修改</a></td>
  	</c:otherwise>
  </c:choose>
  </tr>
  </c:forEach>

  </table>
</form:form>

<!-- Page Control Buttons -->
<%@ include file="/WEB-INF/jsp/page_list_control.jsp" %>
</div>
<!--End of Main Content -->


<!-- Card Container bottom  -->
<%@ include file="/WEB-INF/jsp/common_card_bottom.jsp"%>
<!-- body bottom -->
<%@ include file="/WEB-INF/jsp/common_include_bottom.jsp"%>