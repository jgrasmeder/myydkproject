package com.ydk.epub;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

import com.chenlb.mmseg4j.analysis.MaxWordAnalyzer;

public class SearchEngine {
	private static File BOOK_INDEX = new File("book_index");
	private static final Logger log = Logger.getLogger(SearchEngine.class.getName());  
	final private static int MAX_RESULT_NUMBER = 50;  
	private Analyzer analyzer; 
	private Directory bookDir; 
	
	/**
	 * @param book
	 * @throws Exception 
	 */
	public void addBook(Book book, String aesKey) throws Exception {
		IndexWriter indexWriter;
		try {				
			indexWriter = new IndexWriter(bookDir, analyzer,false, IndexWriter.MaxFieldLength.UNLIMITED);			
		} catch (CorruptIndexException e) {
			indexWriter = new IndexWriter(bookDir, analyzer,true, IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (LockObtainFailedException e) {
			indexWriter = new IndexWriter(bookDir, analyzer,true, IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (IOException e){
			indexWriter = new IndexWriter(bookDir, analyzer,true, IndexWriter.MaxFieldLength.UNLIMITED);
		}
		Document doc = book.IndexDocument(aesKey);
		indexWriter.deleteDocuments(new Term("key", book.getBookUUID()));
		indexWriter.addDocument(doc);
		indexWriter.optimize();
		indexWriter.close();
	}
	/**
	 * clear all book index
	 */
	public void clearBookIndex()
	{
		String[] files = BOOK_INDEX.list();
		for(String file:files)
		{
			new File(file).delete();
		}				
	}
	
	/** 
	 * this must be called before everything 
	 * @return
	 * @throws IOException 
	 */
	public void init() throws IOException
	{
		log.info("init");
		analyzer = new MaxWordAnalyzer();  
		bookDir = FSDirectory.open(BOOK_INDEX);	
		
	
//		rebuildBookIndex();
//		rebuildReviewIndex();
//		rebuildActivityIndex();		
	}
	/**
	 * @param key
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 */
	public void removeBook(String key) throws CorruptIndexException, LockObtainFailedException, IOException {
		IndexWriter indexWriter;
		try {				
			indexWriter = new IndexWriter(bookDir, analyzer,false, IndexWriter.MaxFieldLength.UNLIMITED);			
		} catch (CorruptIndexException e) {
			indexWriter = new IndexWriter(bookDir, analyzer,true, IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (LockObtainFailedException e) {
			indexWriter = new IndexWriter(bookDir, analyzer,true, IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (IOException e){
			indexWriter = new IndexWriter(bookDir, analyzer,true, IndexWriter.MaxFieldLength.UNLIMITED);
		}
		indexWriter.deleteDocuments(new Term("key", key));
		indexWriter.close();

	}
	
	
	/**
	 * @param book
	 * @throws Exception 
	 */
	public void updateBook(Book book, String aesKey) throws Exception {
		IndexWriter indexWriter;
		try {				
			indexWriter = new IndexWriter(bookDir, analyzer,false, IndexWriter.MaxFieldLength.UNLIMITED);			
		} catch (CorruptIndexException e) {
			indexWriter = new IndexWriter(bookDir, analyzer,true, IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (LockObtainFailedException e) {
			indexWriter = new IndexWriter(bookDir, analyzer,true, IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (IOException e){
			indexWriter = new IndexWriter(bookDir, analyzer,true, IndexWriter.MaxFieldLength.UNLIMITED);
		}
		Document doc = book.IndexDocument(aesKey);
		try {
			indexWriter.updateDocument(new Term("key", book.getBookUUID()), doc, analyzer);
		} catch (CorruptIndexException e) {
			log.log(Level.WARNING, "CorruptIndexException: "+book.toString());
		} catch (IOException e) {
			log.log(Level.WARNING, "IOException: "+book.toString());
		}
		indexWriter.optimize();
		indexWriter.close();

	}
	public List<String> searchBookExact(String keywords, String[] fields) {
		List<String> books = new ArrayList<String>();
		IndexSearcher indexSearcher;
		try {
			indexSearcher = new IndexSearcher(bookDir);
		} catch (CorruptIndexException e1) {
			log.log(Level.WARNING, "CorruptIndexException: "+bookDir);
			return books;
		} catch (IOException e1) {
			log.log(Level.WARNING, "IOException: "+bookDir);
			return books;
		} 			
	    MultiFieldQueryParser mfqp = new MultiFieldQueryParser(Version.LUCENE_CURRENT, fields, analyzer);
	    mfqp.setDefaultOperator(Operator.AND);
	    try {
			Query query = mfqp.parse(keywords);
			log.info(query.toString());
			TopDocs docs = indexSearcher.search(query, MAX_RESULT_NUMBER);
			log.info("got "+docs.totalHits+" hits");			  
            for(ScoreDoc sd : docs.scoreDocs) { 
            	log.info("title: "+indexSearcher.doc(sd.doc).get("title")+" score: "+sd.score);
            	books.add(indexSearcher.doc(sd.doc).get("bookid"));                 
            }  
		} catch (ParseException e) {
			log.log(Level.WARNING, "search book ParseException: "+keywords+" fields: "+fields);
		} catch (IOException e) {
			log.log(Level.WARNING, "search book IOException: "+keywords+" fields: "+fields);
		}
		return books;
	}
//	/**
//	 * @param keyword
//	 * @param field
//	 * @return
//	 */
//	public List<String> searchBookFuzzy(String keyword, String field)
//	{
//		log.info("search book fuzzy: "+keyword+" field: "+field);
//		List<String> books = new ArrayList<String>();
//		IndexSearcher indexSearcher;
//		try {
//			indexSearcher = new IndexSearcher(bookDir);
//			
//		} catch (CorruptIndexException e1) {
//			log.log(Level.WARNING, "CorruptIndexException: "+bookDir);
//			return books;
//		} catch (IOException e1) {
//			log.log(Level.WARNING, "IOException: "+bookDir);
//			return books;
//		} 			
//		FuzzyQuery query=new FuzzyQuery(new Term(field,keyword));
//		try {
//			TopDocs docs = indexSearcher.search(query, MAX_RESULT_NUMBER);
//			log.info("got "+docs.totalHits+" hits");
//			for(ScoreDoc sd : docs.scoreDocs) { 
//				log.info("key: "+indexSearcher.doc(sd.doc).get("key")+" score: "+sd.score);
//				books.add(indexSearcher.doc(sd.doc).get("key"));                 
//			}  
//		} catch (IOException e) {
//			return books;
//		}
//		return books;
//	}


}
