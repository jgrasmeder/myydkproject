<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<script language="JavaScript" type="text/javascript">
function showprepic(file){
document.all.showpic.src = "file://" + file;
}
</script>
<h2><c:if test="${account.new}">New </c:if>Account:</h2>

<form:form modelAttribute="accountHelper" enctype="multipart/form-data">
  <table>
  	<tr>
      <th>
        Account Name: <form:errors path="account.accountName" cssClass="errors"/>
        <br/>
        <form:input path="account.accountName" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Password: <form:errors path="account.password" cssClass="errors"/>
        <br/>
        <c:if test="${accountHelper.account.new}">
        <form:password path="account.password" size="30" maxlength="80"/>
        </c:if>
        <c:if test="${!accountHelper.account.new}">
        <form:hidden path="account.password"/>
        </c:if>
      </th>
    </tr>
    <tr>
      <th>
        Email: <form:errors path="account.email" cssClass="errors"/>
        <br/>
        <form:input path="account.email" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Role: <form:errors path="account.role" cssClass="errors"/>
        <br/>
        <form:select disabled="true" path="account.role" >
        	<option>Guest</option>
        	<option>VIP</option>
        	<option>Admin</option>
        </form:select>
      </th>
    </tr>
    <tr>
      <th>
        First Name: <form:errors path="account.firstName" cssClass="errors"/>
        <br/>
        <form:input path="account.firstName" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Last Name: <form:errors path="account.lastName" cssClass="errors"/>
        <br/>
        <form:input path="account.lastName" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Sex: <form:errors path="account.lastName" cssClass="errors"/>
        <br/>
        <form:input path="account.lastName" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Address: <form:errors path="account.address" cssClass="errors"/>
        <br/>
        <form:input path="account.address" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        City: <form:errors path="account.city" cssClass="errors"/>
        <br/>
        <form:input path="account.city" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Telephone: <form:errors path="account.telephone" cssClass="errors"/>
        <br/>
        <form:input path="account.telephone" size="20" maxlength="20"/>
      </th>
    </tr>
    <tr>
      <th>
        My Image: <form:errors path="fileUploadBean.file" cssClass="errors"/>
        <br/>
        <input type="file"  name="uploadedFile" onChange="javascript:document.all.showprepic(this.value);"/>
      </th>
    </tr>
    <tr>
      <td>
        <c:choose>
          <c:when test="${accountHelper.account.new}">
            <p class="submit"><input type="submit" value="Add Account"/></p>
          </c:when>
          <c:otherwise>
            <p class="submit"><input type="submit" value="Update Account"/></p>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
  </table>
</form:form>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>
