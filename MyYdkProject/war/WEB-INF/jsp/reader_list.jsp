<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>

<body>

<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>

<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
 读者列表
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
  	<input name="selectAll" type="checkbox" 
  		onClick="checkAll(document.getElementById('pageDataForm'), 'keys',this)" value="all"/>
  	<span>全选</span></td>
  <td>用户名</td>
  <td>服务商</td>
  <td>邮箱</td>
  <td>姓名</td>
  <td>手机</td>
  <td>激活</td>
  <td class="end">操作</td>
  </tr>
  <c:forEach var="item" items="${pageDataList.pageList}">

  <tr align="center">
  <c:choose>
  	<c:when test="${dataForm.instance.id == item.id}">
  	  <td>&nbsp;</td>
	  <td><spring:bind path="dataForm.instance.nickName">
	  		<input 
    		type="text"
    		id="input_name" 
    		name="<c:out value="${status.expression}"/>"   
    		value="<c:url value="${status.value}"/>"/>
    		</spring:bind>
    	   <form:errors path="instance.nickName" cssClass="errors"/>
      </td>
	  <td>
	  	<form:select path="instance.partner.id" cssStyle="width:100px">
    		<c:forEach var="type" items="${typeList}">
    			<option value="${type.id}" <c:if test="${type.id == dataForm.instance.partner.id}">selected</c:if>>${type.name}</option>
    		</c:forEach>
    	</form:select>
      </td>
      
      <td>
      	<form:input path="instance.email" />
	      <form:errors path="instance.email" cssClass="errors"/>
      </td>
      
      <td>
      	<form:input path="instance.realName" />
	      <form:errors path="instance.realName" cssClass="errors"/>
      </td>
      
      <td>
      	<form:input path="instance.mobilePhone"/>
	      <form:errors path="instance.mobilePhone" cssClass="errors"/>
      </td>
     
      <td>
      	<form:select path="instance.isActived" cssStyle="width:40px">
	  			<option value="false" >否</option>
	  			<option value="true" <c:if test="${dataForm.instance.isActived}">selected</c:if> >是</option>
    	</form:select>
      </td>
	  <td>
	  	<form:hidden path="instance.id"/>
	  	<a href="#" onClick="document.pageDataForm.action='<c:url value="/modifyReader.do"/>';
		  document.pageDataForm.submit();return true;">保存</a></td>
	</c:when>
	<c:otherwise>
	  <td><form:checkbox path="keys" value="${item.id}"/></td>
	  <td><a href="<c:url value="/viewReader.do?itemId=${item.id}"/>">${item.nickName}</a></td>
	  <td>${item.partner.name}</td>
	  <td>${item.email}</td>
	   <td>${item.realName}</td>
	  <td>${item.mobilePhone}</td>
	  <td><c:if test ="${!item.isActived}">否</c:if>
	  	<c:if test ="${item.isActived}">是</c:if></td>
	  
	  <td><a href="<c:url value="/listReader.do?itemId=${item.id}&pageNumber=${pageDataList.page}"/>">修改</a></td>
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