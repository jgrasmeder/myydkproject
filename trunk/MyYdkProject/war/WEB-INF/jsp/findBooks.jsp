<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Find Books:</h2>

<form:form modelAttribute="book">
 <table>
    <tr>
      <th>
        Book Name: <form:errors path="*" cssClass="errors"/>
        <br/> 
        <form:input path="bookName" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <td><p class="submit"><input type="submit" value="Find Books"/></p></td>
    </tr>
  </table>
</form:form>


<form method="POST">
<input name="action" type="hidden" value="all"/>"
<table>
	<tr>
      <td><p class="submit"><input type="submit" value="List Books"/></p></td>

    </tr>
  </table>
</form>




<br/>
<a href="<c:url value="/addBook.do"/>">Add Book</a>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
