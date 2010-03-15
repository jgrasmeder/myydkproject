<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Books:</h2>

<table>
  <tr>
  <thead>
  	<th>BookName</th>
    <th>Author</th>
    <th>Translator</th>
    <th>isbn</th>
    <th>pages</th>
  </thead>
  </tr>
  <c:forEach var="book" items="${selections}">
    <tr>
      <td>
          <a href="book.do?bookId=${book.id}">${book.bookName}</a>
      </td>
      <td>${book.author}</td>
      <td>${book.translator}</td>
      <td>${book.isbn}</td>
      <td>${book.pages}</td>
      <td><img src="<c:url value="bookImg.do?imgId=${book.imageSmall.fileId}"/>"/></td>
      <td><a href="deleteBook.do?bookId=${book.id}&fromView=books">Delete ${book.bookName}</a></td>
     </tr>
  </c:forEach>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
