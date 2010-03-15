<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="header.jsp" %>


<c:if test="${!empty message}">
  <b><font color="RED"><c:url value="${message}"/></font></b>
</c:if>

<c:if test="${!empty myForwardAction}">
  <b><font color="RED"><c:url value="${myForwardAction}"/></font></b>
</c:if>

<form:form modelAttribute="user" action="/MyYdkProject/login.do">
<c:if test="${!empty myForwardAction}">
<input type="hidden" name="forwardAction" value="<c:out value="${myForwardAction}"/>"/>
</c:if>

<table align="center" border="0">
<tr>
<td colspan="2"><spring:message code="login.reminder"/>.
<br />&nbsp;</td>
</tr>
<tr>
<td><spring:message code="login.accountName"/>:</td>
<td>
<form:input path="accountName" size="30" maxlength="80"/>
<form:errors path="accountName" cssClass="errors"/>
</td>
</tr>
<tr>
<td><spring:message code="login.password"/>:</td>
<td>
<form:input path="password" size="30" maxlength="80"/>
<form:errors path="password" cssClass="errors"/>
</td>
</tr>
<tr>
<td>Verification Code:</td>
<td>
<form:hidden path="verificationCode"/>
<form:input path="verificationValue" size="30" maxlength="80"/>
<form:errors path="verificationValue" cssClass="errors"/>
<img src="images/<c:out value="${user.verificationCode}"/>"/>
</td>
</tr>
<tr>
<td>
<img id="randomImage" src="<c:url value="/showRandomImage.do"/>"/>
</td>
<td>
<c:if test="${not empty randomChar}">${randomChar}</c:if>
<a href="#" onClick="javascript:document.execCommand('Refresh')">Change Picture </a>
</td>
<td>
<a href="#" onclick="javascript:document.getElementById('randomImage').src='<c:url value="/showRandomImage.do"/>';return false;">
	Change Picture 
</a>
</td>
</tr>

<tr>
<td>Not Clear?</td>
<td>
<c:if test="${empty myForwardAction}">
<a href="<c:url value="/login.do"/>">Change Picture</a>
</c:if>
<c:if test="${!empty myForwardAction}">
<a href="<c:url value="/login.do?forwardAction=${myForwardAction}"/>">Change Picture</a>
</c:if>
</td>
</tr>
<tr>
<td>&nbsp;</td>
<td><input type="image" border="0" src="images/button_submit.gif" name="update" />
<a href="<c:url value="/addAccount.do"/>">
<img border="0" src="images/button_register_now.gif" />
</a></td>

</tr>
<tr>
<td>&nbsp;</td>
<td>

</td>

</tr>
</table>

</form:form>


<%@ include file="footer.jsp" %>

