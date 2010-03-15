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
  <td>Id</td>
  <td>读者</td>
  <td>书籍YdbId</td>
  <td>书籍名</td>
  <td>授权类型</td>
  <td>服务商</td>
  <td>日期</td>
  <td class="end">操作</td>
  </tr>
  <c:forEach var="item" items="${pageDataList.pageList}">

  <tr align="center">
  <c:choose>
  	<c:when test="${dataForm.instance.id == item.id}">
  	  <td>&nbsp;</td>
	  <td>${dataForm.instance.id}
      </td>
      <td>
      	<form:input path="instance.reader.nickName" />
	      <form:errors path="instance.reader.nickName" cssClass="errors"/>
      </td>
      <td>
      	<form:input path="instance.book.ydkBookId" />
	      <form:errors path="instance.book.ydkBookId" cssClass="errors"/>
      </td>
      <td>
      	<form:input path="instance.book.name" />
	      <form:errors path="instance.book.name" cssClass="errors"/>
      </td>
	  <td>
	  	<form:select path="instance.transactionType.id" cssStyle="width:100px">
    		<c:forEach var="type" items="${typeList}">
    			<option value="${type.id}" <c:if test="${type.id == dataForm.instance.transactionType.id}">selected</c:if>>${type.type}</option>
    		</c:forEach>
    	</form:select>
      </td>
      <td>
      	<form:input path="instance.partner.name" />
	      <form:errors path="instance.partner.name" cssClass="errors"/>
      </td>
      <td>
      	<form:input path="instance.date" />
	      <form:errors path="instance.date" cssClass="errors"/>
      </td>
      
      
	  <td>
	  	<form:hidden path="instance.id"/>
	  	<a href="#" onClick="document.pageDataForm.action='<c:url value="/modifyTransaction.do"/>';
		  document.pageDataForm.submit();return true;">保存</a></td>
	</c:when>
	<c:otherwise>
	  <td><form:checkbox path="keys" value="${item.id}"/></td>
	  <td><a href="<c:url value="/viewTransaction.do?itemId=${item.id}"/>">${item.id}</a></td>
	  <td>${item.reader.nickName}</td>
	  <td>${item.book.ydkBookId}</td>
	   <td>${item.book.name}</td>
	  <td width="160px;">${item.transactionType.type }</td>
	  <td>${item.partner.name}</td>
	  <td>${item.date}</td>
	  <td><a href="<c:url value="/listTransaction.do?itemId=${item.id}&pageNumber=${pageDataList.page}"/>">修改</a></td>
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