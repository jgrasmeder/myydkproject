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
  书籍 <b>${dataForm.instance.title}</b>
</div>

<div style="float:left; width:49%;">
	<div>
	<table class="table2" width="95%"  height="auto">
		<tr  align="center">
		  <td>标识图片(小)</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<c:choose>
			  <c:when test="${not empty dataForm.instance.imageSmall}">
			  	<a title="点击察看大图" href="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.imageSmall}"/>">
			  	<img style="height:40px;width:40px;" src="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.imageSmall}"/>"/>
			  	</a>
			  </c:when>
			  
			  <c:otherwise>
			  	无图片
			  </c:otherwise>
			</c:choose>
		  </td>
		</tr>
		<tr  align="center">
		  <td>标识图片(大)</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<c:choose>
			  <c:when test="${not empty dataForm.instance.imageLarge}">
			  	<a title="点击察看大图" href="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.imageLarge}"/>">
			  	<img style="height:40px;width:40px;" src="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.imageLarge}"/>"/>
			  	</a>
			  </c:when>
			  <c:otherwise>
			  	无图片
			  </c:otherwise>
			</c:choose>
		  </td>
		</tr>
		<tr  align="center">
		  <td>3D图片(小)</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<c:choose>
			  <c:when test="${not empty dataForm.instance.image3DSmall}">
			  	<a title="点击察看大图" href="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.image3DSmall}"/>">
			  	<img style="height:40px;width:40px;" src="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.image3DSmall}"/>"/>
			  	</a>
			  </c:when>
			  <c:otherwise>
			  	无图片
			  </c:otherwise>
			</c:choose>
		  </td>
		</tr>
		
		<tr  align="center">
		  <td>3D图片(大)</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<c:choose>
			  <c:when test="${not empty dataForm.instance.image3DLarge}">
			  	<a title="点击察看大图" href="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.image3DLarge}"/>">
			  	<img style="height:40px;width:40px;" src="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.image3DLarge}"/>"/>
			  	</a>
			  </c:when>
			  <c:otherwise>
			  	无图片
			  </c:otherwise>
			</c:choose>
		  </td>
		</tr>
		
		<tr  align="center">
		  <td>书籍内容</td>
		</tr>
		<tr  align="center">
		  <td >
		  	<c:choose>
			  <c:when test="${not empty dataForm.instance.bookContent}">
			  	<a title="点击下载" href="<c:url value="ydkGetImage.do?fileName=${dataForm.instance.bookContent}"/>">
			  	${dataForm.instance.title}
			  	</a>
			  </c:when>
			  <c:otherwise>
			  	无内容
			  </c:otherwise>
			</c:choose>
		  </td>
		</tr>
		
		
		
	</table>
	</div>
	
	
	<table class="table2" width="95%"  height="auto">
	<tr  align="center">
	<td>
	<div align="left">
	所属栏目：<br/>
	<c:forEach var="tag" items="${dataForm.instance.bookTags}">
	${tag.name}&nbsp;
	</c:forEach>
	<br/>
	<br/>
	</div>  
	</td>
	</tr>
	<tr>
	<td>
	<div>
	添加到更多栏目：<br/>
	<form:form action="tagBook.do" modelAttribute="dataForm" method="POST">
		<c:forEach var="tag" items="${tagList.bookTags}">
		<form:checkbox path="keys" value="${tag.id}"/>${tag.name}
		</c:forEach>
		<form:hidden path="instance.id"/>
		<input type="submit"  class="button_blue" value="提交栏目修改"/>
	</form:form>
	</div>
	</td>
	</tr>
	</table>
	
</div>
<div style="float:right; width:50%;">
<table class="table2" align="center" width="95%" border="0" bgcolor="#C9E7E6">

  
  
  <tr align="center">	
    <td  style="width:35%; text-align:right">题目</td>
    <td style="text-align:left;" colspan="2">
        ${dataForm.instance.title}
    </td>
  </tr>
  
   <tr align="center">	
    <td  style="text-align:right">作者</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.author.name}<br/>
    	${dataForm.instance.author1.name}<br/>
    	${dataForm.instance.author2.name}<br/>
    	${dataForm.instance.author3.name}<br/>
    </td>
  </tr>
  <tr align="center">	
    <td  style="text-align:right">译者</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.translator.name}<br/>
    	${dataForm.instance.translator1.name}<br/>
    	${dataForm.instance.translator2.name}<br/>
    	${dataForm.instance.translator3.name}<br/>
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">类型</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.category.name}
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">时间</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.publishDate}
    </td>
  </tr>
  
 
  <tr align="center">	
    <td  style="text-align:right">简介</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.summary}
    </td>
  </tr>
  <tr align="center">	
    <td  style="text-align:right">主题</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.toc}
    </td>
  </tr>
  <tr align="center">	
    <td  style="text-align:right">版本</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.edition}
    </td>
  </tr>
  <tr align="center">	
    <td  style="text-align:right">页数</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.pages}
    </td>
  </tr>
  <tr align="center">	
    <td  style="text-align:right">大小</td>
    <td style="text-align:left;" colspan="2">
    	${dataForm.instance.size}
    </td>
  </tr>
  <tr align="center">	
    <td  style="text-align:right">标签</td>
    <td style="text-align:left;" colspan="2">
    	
    </td>
  </tr>
  
  <tr align="center">	
    <td  style="text-align:right">价格</td>
    <td style="text-align:left;" colspan="2">
    	
    	市场价格：${dataForm.instance.marketPrice}<br/>
    	电子书价格：${dataForm.instance.selectedEPrice}<br/>
    	纸质书价格${dataForm.instance.paperPrice}<br/>
    	Ydk纸质书价格：${dataForm.instance.ydkPaperPrice}
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