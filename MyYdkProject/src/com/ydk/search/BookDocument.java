/**
 * BookDocument.java
 */
package com.ydk.search;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.Term;

import com.ydk.book.persistence.entity.Book;


/**
 * @author y1zhao
 *
 */
public class BookDocument {
	
	 /** create a Document mapping the DB record
	 * @param key, the primary key in DB
	 * @return document
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static Document Document(Book book) throws FileNotFoundException, UnsupportedEncodingException
	 {
		 // make a new, empty document
		 Document doc = new Document();
		 doc.add(new Field("id", book.getId().toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		 Field author = new Field("author", book.getAuthor().getName(), Field.Store.NO, Field.Index.ANALYZED);
		 author.setBoost(10);
		 doc.add(author);
		 Field title = new Field("title", book.getTitle(), Field.Store.NO, Field.Index.ANALYZED);
		 author.setBoost(100);
		 doc.add(title);
		 Field category = new Field("category", book.getCategory().getName(), Field.Store.NO, Field.Index.ANALYZED);
		 category.setBoost(10);
		 doc.add(category);
		 Field press = new Field("subTitle", book.getSubTitle(), Field.Store.NO, Field.Index.ANALYZED);
		 press.setBoost(10);
		 doc.add(press);		 
		 doc.add(new Field("summary", book.getSummary(), Field.Store.NO, Field.Index.ANALYZED));
//		 doc.add(new Field("content", book.getContentReader()));
//		 doc.add(new Field("uri", book.getUri(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		 return doc;		 
	 }
	
	public static Document Document(Book book, Reader contentReader) throws FileNotFoundException, UnsupportedEncodingException
	 {
		 // make a new, empty document
		 Document doc = new Document();
		 doc.add(new Field("id", book.getId().toString(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		 if (book.getAuthor() != null)
		 {
			 Field author = new Field("author", book.getAuthor().getName(), Field.Store.NO, Field.Index.ANALYZED);
			 author.setBoost(10);
			 doc.add(author);
		 }
		 Field title = new Field("title", book.getTitle(), Field.Store.NO, Field.Index.ANALYZED);
		 title.setBoost(100);
		 doc.add(title);
		 if (book.getCategory() != null && book.getCategory().getName() != null)
		 {
			 Field category = new Field("category", book.getCategory().getName(), Field.Store.NO, Field.Index.ANALYZED);
			 category.setBoost(10);
			 doc.add(category);
		 }
		 if (book.getSubTitle() != null)
		 {
			 Field subTitle = new Field("subTitle", book.getSubTitle(), Field.Store.NO, Field.Index.ANALYZED);
			 subTitle.setBoost(10);
			 doc.add(subTitle);		 
		 }
		 if (book.getSummary() != null)
		 {
			doc.add(new Field("summary", book.getSummary(), Field.Store.NO, Field.Index.ANALYZED));
		 }
		 doc.add(new Field("content", contentReader));
//		 doc.add(new Field("uri", book.getUri(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		 return doc;		 
	 }
	
	/*
	 * no instance
	 */
	private BookDocument() {}
}
