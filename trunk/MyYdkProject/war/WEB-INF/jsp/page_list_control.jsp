<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

 <table width="100%" height="auto">
  <tr align="right">
  <td style="vertical-align:middle;" align="middle">
  <script type="text/javascript">
	<!--
	function prompter() {
	var reply = prompt("确定要删除么?(Y/N)","");
	if (reply == "y" || reply == "Y")
	{
		document.pageDataForm.submit();
		return true;
	}

	}
	//-->
</script>
  <input type="submit" onclick="prompter();" class="button_blue" value="删除所选"/>
  &nbsp;</td>
  <td style="vertical-align:middle;" align="right" colspan="4">
    <div style="margin-top:10px; float:right;">
    	<form method="GET" name="pageNumberForm">
    	
		<c:if test="${!pageDataList.firstPage}">
		    <a href="?pageNumber=0"><font color="blue"><B>
		    &lt;&lt; 第一页
		    </B></font></a>
		  </c:if>
		  
		<c:if test="${!pageDataList.firstPage}">
		    <a href="?page=previous&pageNumber=${pageDataList.page - 1}"><font color="blue"><B>
		    &lt; 上一页</B></font></a>
		</c:if>
		<span>
		<font color="black"><B>&nbsp;
		  	共
		  	</B></font>
		 
		 ${pageDataList.pageCount}
		<font color="black"><B>页
	  		&nbsp;</B></font>
	  	</span>
		<font color="black"><B>&nbsp;
		  	第
		  	</B></font>

		
          <select name="pageNumber"/>
            <c:forEach var="i" begin="0" end="${pageDataList.lastLinkedPage}">
          	<option <c:if test="${i == pageDataList.page}">selected</c:if> value="${i}">
          	<c:out value="${i + 1}"/>
          	</option>
          	</c:forEach>
          </select>
		
	  	
	  	<font color="black"><B>页
	  		&nbsp;</B></font>
	  	</span>

		  <c:if test="${!pageDataList.lastPage}">
		    <a href="?page=next&pageNumber=${pageDataList.page + 1}"><font color="blue"><B>
		   	 下一页 &gt;</B></font></a>
		  </c:if>
		  <c:if test="${!pageDataList.lastPage}">
		    <a href="?page=last"><font color="blue"><B>
		    末页 &gt;&gt;</B></font></a>
		  </c:if>

	  </form>
	  </div>
    </td>
	<td align="right">
		<a href="#" onclick="document.pageNumberForm.submit();return true;">
			<div class="link_button_blue" style="padding-top:5px;">
			Go</div>
		</a>
	</td>
  </tr>
</table>
