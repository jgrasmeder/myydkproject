<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>

<body>

<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>

<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
 活动列表
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
  <td>标题</td>
  <td>类型</td>
  <td>时间</td>
  <td>地点</td>
  <td>简介</td>
  <td>顺序</td>
  <td>推荐</td>
  <td>最新</td>
  <td class="end">操作</td>
  </tr>
  <c:forEach var="item" items="${pageDataList.pageList}">

  <tr align="center">
  <c:choose>
  	<c:when test="${dataForm.instance.id == item.id}">
  	  <td>&nbsp;</td>
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
	  	<form:select path="instance.eventCategory.id" cssStyle="width:100px">
    		<c:forEach var="type" items="${typeList}">
    			<option value="${type.id}" <c:if test="${type.id == dataForm.instance.eventCategory.id}">selected</c:if>>${type.name}</option>
    		</c:forEach>
    	</form:select>
      </td>
      
      <td>
      	<form:input path="instance.date" />
	      <form:errors path="instance.date" cssClass="errors"/>
      </td>
      
      <td>
      	<form:input path="instance.location" cssStyle="width:100px"/>
	      <form:errors path="instance.location" cssClass="errors"/>
      </td>
      <td >
      	<form:input path="instance.introduction" />
	      <form:errors path="instance.introduction" cssClass="errors"/>
      </td>
      
      <td>
      	<form:input path="instance.order" cssStyle="width:40px" />
	      <form:errors path="instance.order" cssClass="errors"/>
      </td>

      <td>
      	<form:select path="instance.recommanded" cssStyle="width:40px">
	  			<option value="false" >否</option>
	  			<option value="true" <c:if test="${dataForm.instance.recommanded}">selected</c:if> >是</option>
    	</form:select>
    	<form:errors path="instance.recommanded" cssClass="errors"/>
      </td>
      <td>
      	<form:select path="instance.isNew" cssStyle="width:40px">
	  			<option value="false" >否</option>
	  			<option value="true" <c:if test="${dataForm.instance.isNew}">selected</c:if> >是</option>
    	</form:select>
    	<form:errors path="instance.isNew" cssClass="errors"/>
      </td>
	  <td>
	  	<form:hidden path="instance.id"/>
	  	<a href="#" onClick="document.pageDataForm.action='<c:url value="/modifyEvent.do"/>';
		  document.pageDataForm.submit();return true;">保存</a></td>
	</c:when>
	<c:otherwise>
	  <td><form:checkbox path="keys" value="${item.id}"/></td>
	  <td><a href="<c:url value="/viewEvent.do?itemId=${item.id}"/>">${item.title}</a></td>
	  <td>${item.eventCategory.name}</td>
	  <td>${item.date}</td>
	   <td>${item.location}</td>
	  <td width="160px;">${item.introduction }</td>
	  <td>${item.order}</td>
	  <td><c:if test ="${!item.recommanded}">否</c:if>
	  	<c:if test ="${item.recommanded}">是</c:if></td>
	  <td><c:if test ="${!item.isNew}">否</c:if>
	  	<c:if test ="${item.isNew}">是</c:if></td>
	  <td><a href="<c:url value="/listEvent.do?itemId=${item.id}&pageNumber=${pageDataList.page}"/>">修改</a></td>
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