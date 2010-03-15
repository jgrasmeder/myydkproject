<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>View Messages</title>
    </head>
    <body>
        <h1>View Messages</h1>
        <ul>
            <c:forEach var="message" items="${messages}">
                <li>${message.lastNameFirstName} (${message.email}): ${message.text}</li>
            </c:forEach>
        </ul>
    </body>
</html>