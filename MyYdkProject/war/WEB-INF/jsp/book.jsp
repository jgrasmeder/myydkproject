<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Book Information</h2>

  <table>
    <tr>
      <th>bookTitle</th>
      <td><b>${bookHelper.book.bookTitle}</b></td>
    </tr>
    <tr>
      <th>author</th>
      <td>${bookHelper.book.author}</td>
    </tr>
    <tr>
      <th>sub title</th>
      <td>${bookHelper.book.subTitle}</td>
    </tr>
    <tr>
      <th>image small</th>
      <td><img src="<c:url value="bookImg.do?imgId=${bookHelper.book.imageSmall.fileId}"/>"/></td>
    </tr>
    <tr>
      <th>image large</th>
      <td><img src="<c:url value="bookImg.do?imgId=${bookHelper.book.imageLarge.fileId}"/>"/></td>
    </tr>
    <tr>
      <th>image 3D small</th>
      <td><img src="<c:url value="bookImg.do?imgId=${bookHelper.book.image3DSmall.fileId}"/>"/></td>
    </tr>
    <tr>
      <th>image 3D large</th>
      <td><img src="<c:url value="bookImg.do?imgId=${bookHelper.book.image3DLarge.fileId}"/>"/></td>
    </tr>
    <tr>
      <th>publisher </th>
      <td>${bookHelper.book.publisher}</td>
    </tr>
    <tr>
      <th>author1 </th>
      <td>${bookHelper.book.author1}</td>
    </tr>
     <tr>
      <th>author2 </th>
      <td>${bookHelper.book.author2}</td>
    </tr>
     <tr>
      <th>author3 </th>
      <td>${bookHelper.book.author3}</td>
    </tr>
     <tr>
      <th>author others </th>
      <td>${bookHelper.book.authorOthers}</td>
    </tr>
    <tr>
      <th>translator </th>
      <td>${bookHelper.book.translator}</td>
    </tr>
    <tr>
      <th>translator1 </th>
      <td>${bookHelper.book.translator1}</td>
    </tr>
    <tr>
      <th>translator2 </th>
      <td>${bookHelper.book.translator2}</td>
    </tr>
    <tr>
      <th>translator3 </th>
      <td>${bookHelper.book.translator3}</td>
    </tr>
    <tr>
      <th>translator others </th>
      <td>${bookHelper.book.translatorOthers}</td>
    </tr>
    <tr>
      <th>page </th>
      <td>${bookHelper.book.page}</td>
    </tr>
    <tr>
      <th>size </th>
      <td>${bookHelper.book.size}</td>
    </tr>
    <tr>
      <th>edition </th>
      <td>${bookHelper.book.edition}</td>
    </tr>
    <tr>
      <th>summary </th>
      <td>${bookHelper.book.summary}</td>
    </tr>
    <tr>
      <th>toc </th>
      <td>${bookHelper.book.toc}</td>
    </tr>
    <tr>
      <th>book content</th>
      <td><img src="<c:url value="bookImg.do?imgId=${bookHelper.book.bookContent.fileId}"/>"/></td>
    </tr>
    <tr>
      <th>publish date </th>
      <td>${bookHelper.book.publishDate}</td>
    </tr>
    <tr>
      <th>isbn </th>
      <td>${bookHelper.book.isbn}</td>
    </tr>
    <tr>
      <th>paper Price</th>
      <td>${bookHelper.book.paperPrice}</td>
    </tr>
    <tr>
      <th>market price</th>
      <td>${bookHelper.book.marketPrice}</td>
    </tr>
    <tr>
      <th>selected E Price</th>
      <td>${bookHelper.book.selectedEPrice}</td>
    </tr>
    <tr>
      <th>ydk Paper Price </th>
      <td>${bookHelper.book.ydkPaperPrice}</td>
    </tr>  
    
  </table>
  <table class="table-buttons">
    <tr>
      <td colspan="2" align="center">
        <form method="GET" action="<c:url value="/editBook.do"/>">
          <input type="hidden" name="bookId" value="${bookHelper.book.id}"/>
          <p class="submit"><input type="submit" value="Edit Book"/></p>
        </form>
      </td>      
    </tr>
  </table>

  
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
