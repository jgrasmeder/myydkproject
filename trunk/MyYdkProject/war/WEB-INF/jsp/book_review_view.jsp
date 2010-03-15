<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common_include_top.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<body>
<style>
textarea
{
width:100%;
height:100%;
}
</style>
<!-- Card Container top  -->
<%@ include file="/WEB-INF/jsp/common_card_top.jsp" %>


<div id="content">
<!-- Main Content -->
<div align="center" class="page_title">
  活动
</div>

<div style="float:left; width:49%;">
	<table class="table2" width="95%"  height="auto">
		<tr  align="center">
		  <td>标识图片</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<c:choose>
			  <c:when test="${not empty dataForm.instance.titleImage}">
			  	<img style="height:40px;width:40px;" src="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.titleImage}"/>"/>
			  </c:when>
			  <c:otherwise>
			  	无图片
			  </c:otherwise>
			</c:choose>
		  </td>
		</tr>
		<tr align="center">
		  <td>内容图片</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<c:choose>
			  <c:when test="${not empty dataForm.instance.images}">
			  	<c:forEach var="fileName" items="${dataForm.instance.images}">
				  	<img style="height:40px;width:40px;" src="<c:url value="ydkGetImage.do?fileName=${fileName}"/>"/>
			 	</c:forEach>
			  </c:when>
			  <c:otherwise>
		  		无图片
		  		</c:otherwise>
		  </c:choose>
		  </td>
		</tr>
		<tr  align="center">
		  <td>内容(.txt)</td>
		</tr>
		<tr  align="center">
		  <td>
		  	<c:choose>
		  	<c:when test="${not empty dataForm.instance.content}">
			  	<div style="width:100%; height:100%; overflow:visible">
			  	<textarea id="content" rows="12" style="overflow-y:visible;" disabled="disabled"
			  	>${fileContent}</textarea>
			  	
			  	</div>
			</c:when>

			
			  <c:otherwise>
			  	无内容
			  </c:otherwise>
			  </c:choose>	
		  </td>
		</tr>
		
	</table>
	
</div>
<div style="float:right; width:50%;">
<table class="table2" align="center" width="95%" border="0" bgcolor="#C9E7E6">

  
  
  <tr align="center">	
    <td  style="width:35%; text-align:right">访谈题目</td>
    <td style="text-align:left;" colspan="2">
        ${dataForm.instance.title}
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">类型</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.bookReviewCategory.name}
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">时间</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.date}
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">简介</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.introduction}
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">编辑</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.editor}
    </td>
  </tr>
  
  
  <tr align="center">	
    <td  style="text-align:right">顺序</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.order}
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">推荐</td>
    <td style="text-align:left;" colspan="2">
    	<c:if test="${dataForm.instance.recommanded}">是</c:if>
    	<c:if test="${!dataForm.instance.recommanded}">否</c:if>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">最新</td>
    <td style="text-align:left;" colspan="2">
    	<c:if test="${dataForm.instance.isNew}">是</c:if>
    	<c:if test="${!dataForm.instance.isNew}">否</c:if>
    </td>
  </tr>
  
  
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</div>

<div class="clear"></div>


</div>
<!--End of Main Content -->

<!-- Card Container bottom  -->
<%@ include file="/WEB-INF/jsp/common_card_bottom.jsp"%>
<!-- body bottom -->
<%@ include file="/WEB-INF/jsp/common_include_bottom.jsp"%>