<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<body>

<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>

<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
 书籍列表
</div>
<div>
<div  style="float:left;">
<form:form action="searchBooks.do" method="GET">
<c:if test="${not empty searchedKeywords}">
<input id="keywords" name="keywords" 
		value=
		"${searchedKeywords}"
		onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入关键字');"
    	onchange="check_input_p(this, 'white');"/>
</c:if>
<c:if test="${empty searchedKeywords}">
<input id="keywords" name="keywords" 
		value=
		"请输入关键字"
		onfocus="clear_input_p(this,'#e5fff3');"
    	onblur="setbg_p(this,'white', '请输入关键字');"
    	onchange="check_input_p(this, 'white');"/>
</c:if>
<input type="submit" class="button_blue" value="查找书籍"/>

</form:form>
</div>

<div align="right" style="display:block;padding-right:5px;">
共${pageDataList.nrOfElements}个结果
</div>
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
  <td>分类</td>
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
	  	<form:select path="instance.category.id" cssStyle="width:100px">
	  	    <option value="">请选择一个类别</option>
    		<c:forEach var="type" items="${typeList}">
    			<option value="${type.id}" <c:if test="${type.id == dataForm.instance.category.id}">selected</c:if>>${type.name}</option>
    		</c:forEach>
    	</form:select>
      </td>


	  <td>
	  	<form:hidden path="instance.id"/>
	  	<a href="#" onClick="document.pageDataForm.action='<c:url value="/modifyBook.do"/>';
		  document.pageDataForm.submit();return true;">保存</a></td>
	</c:when>
	<c:otherwise>
	  <td><form:checkbox path="keys" value="${item.id}"/></td>
	  <td><a href="<c:url value="/viewBook.do?itemId=${item.id}"/>">${item.title}</a></td>
	  <td><c:if test="${item.category != null}">${item.category.name}</c:if></td>

	  <td><a href="<c:url value="/listBook.do?itemId=${item.id}&pageNumber=${pageDataList.page}"/>">修改</a></td>
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