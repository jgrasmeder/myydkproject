<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>

<body>

<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>

<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
  访谈列表
</div>
<div align="right" style="display:block;padding-right:5px;">
共${pageDataList.nrOfElements}个条目
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
  <td>访谈类型</td>
  <td>说明</td>

  <td class="end">操作</td>
  </tr>
  <c:forEach var="item" items="${pageDataList.pageList}">

  <tr align="center">
  <c:choose>
  	<c:when test="${dataForm.instance.id == item.id}">
  	  <td>&nbsp;</td>
  	  <td>${item.id}</td>
	  <td><spring:bind path="dataForm.instance.name">
	  		<input 
    		type="text"
    		id="input_name" 
    		name="<c:out value="${status.expression}"/>"   
    		value="<c:url value="${status.value}"/>"/>
    		</spring:bind>
    	   <form:errors path="instance.name" cssClass="errors"/>
      </td>
      
      <td>
	    <form:input path="instance.introduction" onfocus="clear_input_p(this,'#e5fff3');"
	    	onblur="setbg_p(this,'white', '请输入相关信息');"
	    	onchange="check_input_p(this, 'white');"/>
	     <form:errors path="instance.introduction" cssClass="errors"/>
	    </td>
	  
	  <td>
	  	<form:hidden path="instance.id"/>
	  	<a href="#" onClick="document.pageDataForm.action='<c:url value="/modifyInterviewCategory.do"/>';
		  document.pageDataForm.submit();return true;">保存</a></td>
	</c:when>
	<c:otherwise>
	  <td><form:checkbox path="keys" value="${item.id}"/></td>
	  <td>${item.id}</td>
	  <td>${item.name}</td>
	  <td>${item.introduction}</td>

	  <td><a href="<c:url value="/listInterviewCategory.do?itemId=${item.id}&pageNumber=${pageDataList.page}"/>">修改</a></td>
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