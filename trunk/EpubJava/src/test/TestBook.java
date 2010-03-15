package test;

import java.io.IOException;

import junit.framework.TestCase;

import com.ydk.epub.Book;
import com.ydk.epub.Image;

public class TestBook extends TestCase {
	Book _book;

	public void testBook() {
		try {
			_book = new Book("gegebook.epub");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("epubbook open error");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	public void testParseMetadata() throws Exception {
		_book = new Book("gegebook.epub");
		;
		_book.parseMetadata();
	}

	public void testToString() throws Exception {
		_book = new Book("testbook.epub");
		//_book.parseMetadata();
		System.out.println(_book.toString());
		Image img = _book.getThumbnail3DBig();
		img.save("thumnailbig.jpg");
	}

}
