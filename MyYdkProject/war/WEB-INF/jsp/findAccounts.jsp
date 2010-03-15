<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Find Accounts:</h2>

<form:form modelAttribute="account">
 <table>
    <tr>
      <th>
        Account Name: <form:errors path="*" cssClass="errors"/>
        <br/> 
        <form:input path="accountName" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <td><p class="submit"><input type="submit" value="Find Accounts"/></p></td>
    </tr>
  </table>
</form:form>


<form method="POST">
<input name="action" type="hidden" value="all"/>"
<table>
	<tr>
      <td><p class="submit"><input type="submit" value="List Accounts"/></p></td>

    </tr>
  </table>
</form>




<br/>
<a href="<c:url value="/addAccount.do"/>">Add Account</a>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
