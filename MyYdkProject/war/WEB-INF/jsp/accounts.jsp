<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Accounts:</h2>

<table>
  <tr>
  <thead>
  	<th>AccountName</th>
    <th>Name</th>
    <th>Address</th>
    <th>City</th>
    <th>Telephone</th>
  </thead>
  </tr>
  <c:forEach var="account" items="${selections}">
    <tr>
      <td>
          <a href="account.do?accountId=${account.id}">${account.accountName}</a>
      </td>
      <td>${account.firstName} + ${account.lastName}</td>
      <td>${account.address}</td>
      <td>${account.city}</td>
      <td>${account.telephone}</td>
      <td><img src="<c:url value="accountImg.do?imgId=${account.image.fileId}"/>"/></td>
      <td><a href="deleteAccount.do?accountId=${account.id}&fromView=accounts">Delete ${account.accountName}</a></td>
    </tr>
  </c:forEach>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
