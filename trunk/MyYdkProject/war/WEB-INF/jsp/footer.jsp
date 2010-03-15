
<table class="footer">
	<tr>
		<td><a href="<c:url value="/welcome.do"/>">Home</a></td>
		<td><a href="<c:url value="/addBook.do"/>">Add Book</a></td>
        <td><a href="<c:url value="/findBooks.do"/>">Find Book</a></td>


		<c:if test="${empty userLogIn}">
		<td><a href="<c:url value="/login.do"/>">Log In</a></td>
		</c:if>
		
		<c:if test="${!empty userLogIn}">
		<td>Welcome ${userLogIn.accountName} </td>
		<td>
		<form method="POST" action="<c:url value="/login.do"/>"
			name="userLogOut"><input type="hidden" name="logOut"
			value="true" />
			<p class="submit"><input type="submit" value="Log Out"/></p>
		</form>
		</td>
		</c:if>
		<td align="right"><img src="<c:url value="/images/SS-logo.png"/>" /></td>
	</tr>
</table>

</div>
</body>

</html>
