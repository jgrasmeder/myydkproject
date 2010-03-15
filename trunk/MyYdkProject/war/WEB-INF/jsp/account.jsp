<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Account Information</h2>

  <table>
  	<tr>
      <th>Role</th>
      <td><b>${accountHelper.account.role}</b></td>
    </tr>
    <tr>
      <th>Name</th>
      <td><b>${accountHelper.account.firstName} ${accountHelper.account.lastName}</b></td>
    </tr>
    <tr>
      <th>Head</th>
      <td><img src="<c:url value="accountImg.do?imgId=${accountHelper.account.image.fileId}"/>"/></td>
    </tr>
    
    <tr>
      <th>Address</th>
      <td>${accountHelper.account.address}</td>
    </tr>
    <tr>
      <th>City</th>
      <td>${accountHelper.account.city}</td>
    </tr>
    <tr>
      <th>Telephone </th>
      <td>${accountHelper.account.telephone}</td>
    </tr>
    <tr>
      <th>Img</th>
      <td></td>
    </tr>
  </table>
  <table class="table-buttons">
    <tr>
      <td colspan="2" align="center">
        <form method="GET" action="<c:url value="/editAccount.do"/>">
          <input type="hidden" name="accountId" value="${accountHelper.account.id}"/>
          <p class="submit"><input type="submit" value="Edit Account"/></p>
        </form>
      </td>
    </tr>
  </table>

  
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
