/**
 * 
 */
package com.ydk.search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser.Operator;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.chenlb.mmseg4j.analysis.MaxWordAnalyzer;
import com.ydk.book.persistence.entity.Book;

/**
 * @author y1zhao
 * 
 */
public class SimpleSearchManager {
	private static File BOOK_INDEX = new File("book_index");
	private static final String[] bookFields = {"content","title","author","brief","press","category"};
	private static final Logger log = Logger.getLogger(SimpleSearchManager.class.getName());  
	final private static int MAX_RESULT_NUMBER = 50;  
	private Analyzer analyzer; 
	private Directory bookDir; 
	

	/**
	 * @param key
	 */
	public void addActivity(String key) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param book
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 */
	public void addBook(Book book, File contentFile) throws CorruptIndexException, LockObtainFailedException, IOException {
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
		Document doc = BookDocument.Document(book, new FileReader(contentFile));
		indexWriter.deleteDocuments(new Term("id", book.getId().toString()));
		indexWriter.addDocument(doc);
		indexWriter.optimize();
		indexWriter.close();
	}
	

	/**
	 * @param key
	 */
	public void addReview(String key) {
		// TODO Auto-generated method stub

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
	 * rebuild the all exist activity's index
	 * called when the system init  
	 */
	private void rebuildActivityIndex() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * rebuild the all exist activity's index
	 * called when the system init  
	 * @throws IOException 
	 * @throws LockObtainFailedException 
	 * @throws CorruptIndexException 
	 */
	private void rebuildBookIndex() throws CorruptIndexException, LockObtainFailedException, IOException {
		log.info("rebuild book index");
		//this will create a new empty book index file, Max index number is Integer.MAX_VALUE
		//bookWriter = new IndexWriter(bookDir, analyzer,true, IndexWriter.MaxFieldLength.UNLIMITED);  
		//TODO go over the Book DB, rebuild the book index		
		//Document doc = BookDocument.Document(key);
		
	}

	/**
	 * rebuild the all exist activity's index
	 * called when the system init  
	 */
	private void rebuildReviewIndex() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * @param key
	 */
	public void removeActivity(String key) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param key
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 */
	public void removeBook(String id) throws CorruptIndexException, LockObtainFailedException, IOException {
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
		indexWriter.deleteDocuments(new Term("id", id));
		indexWriter.close();

	}

	/**
	 * @param key
	 */
	public void removeReview(String key) {
		// TODO Auto-generated method stub

	}

	public List<String> searchActivity(String keywords, String[] fields) {
		// TODO Auto-generated method stub
		return null;
	}
	
/*
	This class is generated by JavaCC. The most important method is parse(String). The syntax for query strings is as follows: A Query is a series of clauses. A clause may be prefixed by: 

		a plus (+) or a minus (-) sign, indicating that the clause is required or prohibited respectively; or 
		a term followed by a colon, indicating the field to be searched. This enables one to construct queries which search multiple fields. 
		A clause may be either: 
		a term, indicating all the documents that contain this term; or 
		a nested query, enclosed in parentheses. Note that this may be used with a +/- prefix to require any of a set of terms. 
		Thus, in BNF, the query grammar is: 
		   Query  ::= ( Clause )*
		   Clause ::= ["+", "-"] [<TERM> ":"] ( <TERM> | "(" Query ")" )
*/
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
            	log.info("id: "+indexSearcher.doc(sd.doc).get("id")+" score: "+sd.score);
            	books.add(indexSearcher.doc(sd.doc).get("id"));                 
            }  
		} catch (ParseException e) {
			log.log(Level.WARNING, "search book ParseException: "+keywords+" fields: "+fields);
		} catch (IOException e) {
			log.log(Level.WARNING, "search book IOException: "+keywords+" fields: "+fields);
		}
		return books;
	}
	
	public List<String> searchBookExact(String keywords) {
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
	    MultiFieldQueryParser mfqp = new MultiFieldQueryParser(Version.LUCENE_CURRENT, bookFields, analyzer);
	    mfqp.setDefaultOperator(Operator.AND);
	    try {
			Query query = mfqp.parse(keywords);
			log.info(query.toString());
			TopDocs docs = indexSearcher.search(query, MAX_RESULT_NUMBER);
			log.info("got "+docs.totalHits+" hits");			  
            for(ScoreDoc sd : docs.scoreDocs) { 
            	log.info("id: "+indexSearcher.doc(sd.doc).get("id")+" score: "+sd.score);
            	books.add(indexSearcher.doc(sd.doc).get("id"));                 
            }  
		} catch (ParseException e) {
			log.log(Level.WARNING, "search book ParseException: "+keywords+" fields: "+bookFields);
		} catch (IOException e) {
			log.log(Level.WARNING, "search book IOException: "+keywords+" fields: "+bookFields);
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

	public List<String> searchReview(String keywords, String[] fields) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @param key
	 */
	public void updateActivity(String key) {
		// TODO Auto-generated method stub

	}


	/**
	 * @param book
	 * @throws CorruptIndexException
	 * @throws LockObtainFailedException
	 * @throws IOException
	 */
	public void updateBook(Book book) throws CorruptIndexException, LockObtainFailedException, IOException {
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
		Document doc = BookDocument.Document(book);
		try {
			indexWriter.updateDocument(new Term("id", book.getId().toString()), doc, analyzer);
		} catch (CorruptIndexException e) {
			log.log(Level.WARNING, "CorruptIndexException: "+book.toString());
		} catch (IOException e) {
			log.log(Level.WARNING, "IOException: "+book.toString());
		}
		indexWriter.optimize();
		indexWriter.close();

	}


	/**
	 * @param key
	 */
	public void updateReview(String key) {
		// TODO Auto-generated method stub

	}

}
