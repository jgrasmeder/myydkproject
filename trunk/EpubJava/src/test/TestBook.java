package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import junit.framework.TestCase;

import com.ydk.epub.Book;
import com.ydk.epub.Image;

public class TestBook extends TestCase {
	Book _book;

//	public void testBook() {
//		try {
//			_book = new Book("gegebook.epub");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail("epubbook open error");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//
//	}

//	public void testParseMetadata() throws Exception {
//		_book = new Book("gegebook.epub");
//		;
//		_book.parseMetadata();
//	}

	public void testToString() throws Exception {
		String key = "bb864691ebc40d4a93897669268b619c";
		_book = new Book("xiaoyanzi.epub");
		//_book.parseMetadata();
		System.out.println(_book.toString());
		Image img = _book.getThumbnail3DBig();
		img.save("thumnailbig.jpg");
		String s = _book.getFullContentText(key);
		System.out.println(s);
		_book.IndexDocument(key);
	}
	
//	public void testJDom() throws JDOMException, IOException {
//		FileInputStream fi = new FileInputStream("encryption.xml");
//		SAXBuilder builder = new SAXBuilder();
//		//InputStream in = _epubFile.getInputStream(fe);
//		org.jdom.Document doc = builder.build(fi);
//		Element root = doc.getRootElement();
//		System.out.println(root.toString());
//		Namespace ns = root.getNamespace();
////		Namespace ds = root.getNamespace("ds");
//		Namespace enc = root.getNamespace("enc");
//		List xl = root.getChildren("EncryptedData",enc);
//		System.out.println("xl.size="+xl.size());
//		List xll = root.getChildren();
//		Iterator x11i = xll.iterator();
//		while (x11i.hasNext()){
//			Element xx11e = (Element) x11i.next();
//			System.out.println("xxlle="+xx11e.getName());
//			System.out.println("xxlle="+xx11e.getNamespace());
//		}
//	}

}
