package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Logger;

import junit.framework.TestCase;

import com.ydk.epub.Book;
import com.ydk.epub.SearchEngine;

public class TestSearchEngine extends TestCase {
	private static final Logger log = Logger.getLogger(TestSearchEngine.class.getName());	
	private SearchEngine engine;
	private static final String[] fields = {"content","title","author","brief","press","category"};
	
	@Override
	protected void setUp() throws Exception {
		engine = new SearchEngine();
		engine.init();

	}
	public void testSearchBookExact() throws Exception {
		log.info("test search");	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			System.out.println("input key words:");
			String line = in.readLine();
			if (line.length() == 0 || line.charAt(0)=='q') {
				break;
			}
			List<String> keys = engine.searchBookExact(line, fields);
			System.out.println("found: "+keys.size());
			for(String key: keys)
			{
				System.out.println("book: "+key);
			}
		}
	}

	
	

}
