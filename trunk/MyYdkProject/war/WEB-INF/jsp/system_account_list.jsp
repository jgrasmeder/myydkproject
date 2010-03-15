<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>

<body>

<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>

<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
  帐户列表
</div>
<div align="right" style="display:block;padding-right:5px;">
共${pageDataList.nrOfElements}个帐户
</div>
<form:form id="pageDataForm" name="pageDataForm" modelAttribute="dataForm" method="POST">
<table class="table2"  width="100%" border="0" cellspacing="0" >
  <tr class="bgtitle" style="height:20px;">
  <td>
  	<input name="selectAll" type="checkbox" 
  		onClick="checkAll(document.getElementById('pageDataForm'), 'keys',this)" value="all"/>
  	<span>全选</span></td>
  <td>用户名</td>
  <td>权限</td>
  <td>邮箱</td>
  <td>状态</td>
  <td class="end">操作</td>
  </tr>
  <c:forEach var="item" items="${pageDataList.pageList}">

  <tr align="center">
  <c:choose>
  	<c:when test="${dataForm.accountId == item.id}">
  	  <td>&nbsp;</td>
	  <td><spring:bind path="dataForm.name">
	  		<input 
    		type="text"
    		id="input_name" 
    		name="<c:out value="${status.expression}"/>"   
    		style="width:150px; text-align:left;" 
    		value="<c:url value="${status.value}"/>"/>
    		</spring:bind>
    	   <form:errors path="name" cssClass="errors"/>
      </td>
	  <td>
	  	<form:select path="roleId" cssStyle="width:150px">
    		<c:forEach var="ydkRole" items="${ydkRoleList}">
    			<option value="${ydkRole.id}" <c:if test="${ydkRole.id == dataForm.accountId}">selected</c:if>>${ydkRole.roleName}</option>
    		</c:forEach>
    	</form:select>
      </td>
	  <td>
	  	<spring:bind path="dataForm.email">
	  		<input 
    		type="text"
    		id="input_email" 
    		name="<c:out value="${status.expression}"/>"   
    		style="width:150px;" 
    		value="<c:url value="${status.value}"/>"/>
    	</spring:bind>
    	<form:errors path="email" cssClass="errors"/>
	  </td>
	  <td>
	  	<form:select path="isActived" cssStyle="width:150px">
    			<option value="false">未激活</option>
    			<option value="true">激活</option>

    	</form:select>
	  
	  </td>
	  <td>
	  	<form:hidden path="accountId"/>
	  	<a href="#" onClick="document.pageDataForm.action='<c:url value="/modifyYdkAccount.do"/>';
		  document.pageDataForm.submit();return true;">保存</a></td>
	</c:when>
	<c:otherwise>
	  <td><form:checkbox path="keys" value="${item.id}"/></td>
	  <td>${item.name}</td>
	  <td>${item.ydkRole.roleName}</td>
	  <td>${item.email}</td>
	  <td><c:if test ="${item.isActived}">激活</c:if>
	  	<c:if test ="${!item.isActived}">未激活</c:if></td>
	  <td><a href="<c:url value="/listYdkAccount.do?accountId=${item.id}&pageNumber=${pageDataList.page}"/>">修改</a></td>
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