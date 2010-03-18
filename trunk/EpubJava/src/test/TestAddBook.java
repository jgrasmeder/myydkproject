package test;

import java.util.logging.Logger;

import com.ydk.epub.Book;
import com.ydk.epub.SearchEngine;

import junit.framework.TestCase;

public class TestAddBook extends TestCase {
	private static final Logger log = Logger.getLogger(TestAddBook.class.getName());	
	private SearchEngine engine;

	protected void setUp() throws Exception {
		log.info("init search engine");
		engine = new SearchEngine();
		engine.init();
		
	}

	public void testAddBook() throws Exception {
		log.info("addbook");
		String key = "c969ac4e08e1cbac411b00489844baee6dffa8db77b2b53b1e88323186cf0981668bb6e99fafafc42afc1c58a2c47110";
		Book _book = new Book("testbook.epub");
		engine.addBook(_book, key);
	}

}
