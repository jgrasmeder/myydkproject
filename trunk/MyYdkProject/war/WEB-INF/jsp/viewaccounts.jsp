<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>View Messages</title>
    </head>
    <body>
        <h1>View Messages</h1>
        <ul>
            <c:forEach var="message" items="${messages}">
               
                <c:if test="${not empty message.account}">
                <li>${message.account.id} + ${message.account.accountName} + ${message.account.email}
                <c:if test ="${not empty message.account.image.fileId}">
                <img src="<c:url value="accountImg.do?imgId=${message.account.image.fileId}"/>"/>
                </li>
                </c:if>
                </c:if>
                
            </c:forEach>
        </ul>
    </body>
</html>