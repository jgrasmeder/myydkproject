<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<script language="JavaScript" type="text/javascript">
function showprepic(file){
document.all.showpic.src = "file://" + file;
}
</script>

<h2><c:if test="${book.new}">New </c:if>Book:</h2>

<form:form modelAttribute="bookHelper" enctype="multipart/form-data">
  <table>
  	<tr>
      <th>
        Book title: <form:errors path="book.title" cssClass="errors"/>
        <br/>
        <form:input path="book.title" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        author: <form:errors path="book.author" cssClass="errors"/>
        <br/>
        <form:input path="book.author" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Book sub title: <form:errors path="book.subTitle" cssClass="errors"/>
        <br/>
        <form:input path="book.subTitle" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Small Image: <form:errors path="fileUploadBeanSmall.file" cssClass="errors"/>
        <br/>
        <input type="file"  name="uploadedFileSmall" onChange="javascript:document.all.showprepic(this.value);"/>
      </th>
    </tr>
       <tr>
      <th>
        Large Image: <form:errors path="fileUploadBeanLarge.file" cssClass="errors"/>
        <br/>
        <input type="file"  name="uploadedFileLarge" onChange="javascript:document.all.showprepic(this.value);"/>
      </th>
    </tr>
     <tr>
      <th>
        3D Small Image: <form:errors path="fileUploadBean3DSmall.file" cssClass="errors"/>
        <br/>
        <input type="file"  name="uploadedFile3DSmall" onChange="javascript:document.all.showprepic(this.value);"/>
      </th>
    </tr>
     <tr>
      <th>
        3D Large Image: <form:errors path="fileUploadBean3DLarge.file" cssClass="errors"/>
        <br/>
        <input type="file"  name="uploadedFile3DLarge" onChange="javascript:document.all.showprepic(this.value);"/>
      </th>
    </tr>
       
    <tr>
      <th>
        publisher: <form:errors path="book.publisher" cssClass="errors"/>
        <br/>
        <form:input path="book.publisher" size="30" maxlength="80"/>
      </th>
    </tr> 
    <tr>
      <th>
        author 1: <form:errors path="book.author1" cssClass="errors"/>
        <br/>
        <form:input path="book.author1" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        author 2: <form:errors path="book.author2" cssClass="errors"/>
        <br/>
        <form:input path="book.author2" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        author 3: <form:errors path="book.author3" cssClass="errors"/>
        <br/>
        <form:input path="book.author3" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        author Others: <form:errors path="book.authorOthers" cssClass="errors"/>
        <br/>
        <form:input path="book.authorOthers" size="30" maxlength="80"/>
      </th>
    </tr> 
    <tr>
      <th>
        translator: <form:errors path="book.translator" cssClass="errors"/>
        <br/>
        <form:input path="book.translator" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        translator 1: <form:errors path="book.translator1" cssClass="errors"/>
        <br/>
        <form:input path="book.translator1" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        translator 2: <form:errors path="book.translator2" cssClass="errors"/>
        <br/>
        <form:input path="book.translator2" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        translator 3: <form:errors path="book.translator3" cssClass="errors"/>
        <br/>
        <form:input path="book.translator3" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        translator others: <form:errors path="book.translatorOthers" cssClass="errors"/>
        <br/>
        <form:input path="book.translatorOthers" size="30" maxlength="80"/>
      </th>
    </tr>    
    <tr>
      <th>
        pages: <form:errors path="book.pages" cssClass="errors"/>
        <br/>
        <form:input path="book.pages" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        size: <form:errors path="book.size" cssClass="errors"/>
        <br/>
        <form:input path="book.size" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        edition: <form:errors path="book.edition" cssClass="errors"/>
        <br/>
        <form:input path="book.edition" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        summary: <form:errors path="book.summary" cssClass="errors"/>
        <br/>
        <form:input path="book.summary" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        TOC: <form:errors path="book.toc" cssClass="errors"/>
        <br/>
        <form:input path="book.toc" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Content: <form:errors path="fileUploadBeanContent.file" cssClass="errors"/>
        <br/>
        <input type="file"  name="uploadedFileContent" onChange="javascript:document.all.showprepic(this.value);"/>
      </th>
    </tr>
     <tr>
      <th>
        Publish date: <form:errors path="book.publishDate" cssClass="errors"/>
        <br/>
        <form:input path="book.publishDate" size="30" maxlength="80"/>
      </th>
    </tr>     
   
    <tr>
      <th>
        isbn: <form:errors path="book.isbn" cssClass="errors"/>
        <br/>
        <form:input path="book.isbn" size="30" maxlength="80"/>
      </th>
    </tr>
    
    <tr>
      <th>
        Market Price: <form:errors path="book.paperPrice" cssClass="errors"/>
        <br/>
        <form:input path="book.paperPrice" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        Selected E Price: <form:errors path="book.selectedEPrice" cssClass="errors"/>
        <br/>
        <form:input path="book.selectedEPrice" size="30" maxlength="80"/>
      </th>
    </tr>
    <tr>
      <th>
        YDK Paper Price: <form:errors path="book.ydkPaperPrice" cssClass="errors"/>
        <br/>
        <form:input path="book.ydkPaperPrice" size="30" maxlength="80"/>
      </th>
    </tr>             
    <tr>
      <td>
        <c:choose>
          <c:when test="${bookHelper.book.new}">
            <p class="submit"><input type="submit" value="Add Book"/></p>
          </c:when>
          <c:otherwise>
            <p class="submit"><input type="submit" value="Update Book"/></p>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
  </table>
</form:form>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
