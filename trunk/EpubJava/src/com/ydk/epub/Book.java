package com.ydk.epub;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

public class Book {
	
	private Image thumbnail3DBig;
	private Image thumbnail3DSmall;
	public static final String OEBPS = "OEBPS/";
	private String OPF = OEBPS + "ydkbook.opf";
	
	private ZipFile _epubFile;
	private String bookUUID;
	private String title;
	private Vector<String> authors = new Vector<String>();
	private Vector<String> translators = new Vector<String>();
	private String publisher;
	private String publishDate;
	private String introduction;
	private String format;
	private String layout;
	private String paperCopyPrice;
	private String isbn;
	private String pages;
	private Image thumbnailBig;
	private Image thumbnailSmall;
	private Ncx _ncx;
	public String getBookUUID() {
		return bookUUID;
	}

	public String getTitle() {
		return title;
	}

	public Vector<String> getAuthors() {
		return authors;
	}

	public Vector<String> getTranslators() {
		return translators;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public String getIntroduction() {
		return introduction;
	}

	public String getFormat() {
		return format;
	}

	public String getLayout() {
		return layout;
	}

	public String getPaperCopyPrice() {
		return paperCopyPrice;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getPages() {
		return pages;
	}

	public Image getThumbnailBig() {
		return thumbnailBig;
	}

	public Image getThumbnailSmall() {
		return thumbnailSmall;
	}

	public Image getThumbnail3DBig() {
		return thumbnail3DBig;
	}

	public Image getThumbnail3DSmall() {
		return thumbnail3DSmall;
	}

	public String getOEBPS() {
		return OEBPS;
	}

	public String getOPF() {
		return OPF;
	}

	

	public Book(String fn) throws Exception {
		_epubFile = new ZipFile(fn);
		parseMetadata();
		parseNcx();
	}

	@SuppressWarnings("unchecked")
	public void parseMetadata() throws Exception {
		ZipEntry opf = _epubFile.getEntry(OPF);
		if (opf == null) {
			throw new Exception("ydkbook.opf not exist");
		}

		SAXBuilder builder = new SAXBuilder();
		InputStream in = _epubFile.getInputStream(opf);

		org.jdom.Document doc = builder.build(in);

		Element _package = doc.getRootElement();
		Namespace nsOpf = Namespace
				.getNamespace("http://www.idpf.org/2007/opf");
		Element _metadata = _package.getChild("metadata", nsOpf);

		List<Element> _meta = _metadata.getChildren("meta", nsOpf);
		Iterator<Element> iterator = _meta.iterator();
		while (iterator.hasNext()) {
			Element e = (Element) iterator.next();
			String metaName = e.getAttributeValue("name");
			if (metaName.equalsIgnoreCase("ydkbookname")) {
				title = e.getAttributeValue("content");
			} else if (metaName.equalsIgnoreCase("ydkbookisbn")) {
				isbn = e.getAttributeValue("content");
			} else if (metaName.equalsIgnoreCase("ydkauthor")) {
				String text = e.getAttributeValue("content");
				// byte[] bytes = text.getBytes("utf8");
				// text = new String(bytes);

				authors.add(text);
			} else if (metaName.equalsIgnoreCase("ydktranslator")) {
				translators.add(e.getAttributeValue("content"));
			} else if (metaName.equalsIgnoreCase("ydkbooklayout")) {
				layout = e.getAttributeValue("content");
			} else if (metaName.equalsIgnoreCase("ydkbookformat")) {
				format = e.getAttributeValue("content");
			} else if (metaName.equalsIgnoreCase("ydkbookpages")) {
				pages = e.getAttributeValue("content");
			} else if (metaName.equalsIgnoreCase("ydkpapercopyprice")) {
				paperCopyPrice = e.getAttributeValue("content");
			} else if (metaName.equalsIgnoreCase("ydkbookid")) {
				bookUUID = e.getAttributeValue("content");
			}else if (metaName.equalsIgnoreCase("ydkbookintroduction")) {
				introduction = e.getAttributeValue("content");
			} else if (metaName.equalsIgnoreCase("ydkpublisher")) {
				publisher = e.getAttributeValue("content");
			}  else if (metaName.equalsIgnoreCase("ydkpublishdate")) {
				publishDate = e.getAttributeValue("content");
			} else if (metaName.equalsIgnoreCase("ydkthumbnail3dbig")) {
				thumbnail3DBig = getImage(e);
			} else if (metaName.equalsIgnoreCase("ydkthumbnail3dsmall")) {
				thumbnail3DSmall = getImage(e);
			} else if (metaName.equalsIgnoreCase("ydkthumbnailsmall")) {
				thumbnailBig = getImage(e);
			} else if (metaName.equalsIgnoreCase("ydkthumbnailbig")) {
				thumbnailSmall = getImage(e);
			} else {
				// we don't know the metadata;
			}
		}
		//bookUUID
		
	}
	private void parseNcx() throws Exception{
		_ncx = new Ncx(_epubFile);
	}

	private Image getImage(Element e) throws IOException {
		Image img = new Image();
		img.url = e.getAttributeValue("content");
		ZipEntry imageEntry = _epubFile.getEntry(OEBPS + img.url);
		InputStream in = _epubFile.getInputStream(imageEntry);
		int blen = in.available();
		img.data = new byte[blen];
		for (int i = 0; i < blen; i++){
			img.data[i] = (byte)in.read();
		}
		in.close();
		return img;
	}

	@Override
	public String toString() {
		String ret = "BookMetadata:";
		ret += "\n  title=" + title;
		ret += "\n  bookUUID=" + bookUUID;
		ret += "\n  publisher=" + publisher;
		ret += "\n  publishDate=" + publishDate;
		ret += "\n  introduction=" + introduction;
		ret += vector2string("author", authors);
		ret += vector2string("traslator", translators);
		ret += "\n  format=" + format;
		ret += "\n  layout=" + layout;
		ret += "\n  paperCopyPrice=" + paperCopyPrice;
		ret += "\n  isbn=" + isbn;
		ret += image2string("thumbnail big", thumbnailBig);
		ret += image2string("thumbnail small", thumbnailSmall);
		ret += image2string("thumbnail 3D big", thumbnail3DBig);
		ret += image2string("thumbnail 3D small", thumbnail3DSmall);
		return ret;
	}

	private String vector2string(String title, Vector<String> v) {
		String ret = "";
		Iterator<String> i = v.iterator();
		while (i.hasNext()) {
			String s = i.next();
			if (s != null) {
				if (!s.isEmpty()) {
					ret += "\n  " + title + "=" + s;
				}
			}
		}
		return ret;
	}
	private String vector2string( Vector<String> v) {
		String ret = "";
		Iterator<String> i = v.iterator();
		while (i.hasNext()) {
			String s = i.next();
			if (s != null) {
				if (!s.isEmpty()) {
					ret += " " + s+" ";
				}
			}
		}
		return ret;
	}

	private String image2string(String title, Image img) {
		String ret = "";
		ret += "\n  image[" + title + "]= url:[" + img.url + "]  size:"
				+ img.data.length;
		return ret;
	}
	
	//-------------------------- for lucene full text search document --------------
	public String getBookContentText(){
		String ret = "";
		return ret;
	}
	
	/***
	 * 
	 * @return list of TocItem. with tile, url, get content;
	 * @throws JDOMException 
	 * @throws IOException 
	 */
	public List<TocItem> getToc() throws IOException, JDOMException{
		
		return _ncx.getToc();
	}
	
	/***
	 * 
	 * @param key the AES key for this book must be given, if there is some chapters has 
	 *        been encrypted.
	 * @return
	 * @throws Exception 
	 */
	public String getFullContentText(String key) throws Exception{
		_ncx.setKey(key);
		List <TocItem>l = getToc();
		String ret = "";
		if (l != null){
			Iterator<TocItem> iter = l.iterator();
			while (iter.hasNext()){
				TocItem child = (TocItem)iter.next();
				ret += child.getTextContent();
			}
		}
		return ret;
	}
	
	/***
	 * 
	 * @param key the AES key for this book must be given, if there is some chapters has 
	 *        been encrypted.
	 * @return
	 * @throws Exception 
	 */
	public Document IndexDocument(String key) throws Exception{
		Document doc = new Document();
		//book uuid
		doc.add(new Field("bookid", bookUUID, Field.Store.YES, Field.Index.NOT_ANALYZED));
		//book title/name
		 Field f = new Field("title", title, Field.Store.YES, Field.Index.ANALYZED);
		 f.setBoost(100);
		 doc.add(f);
		//book author
		 f = new Field("author", vector2string(authors), Field.Store.YES, Field.Index.ANALYZED);
		 f.setBoost(80);
		 doc.add(f);
		//book translator
		 f = new Field("translator", vector2string(authors), Field.Store.YES, Field.Index.ANALYZED);
		 f.setBoost(70);
		 doc.add(f);
		//book publisher
		 f = new Field("publisher", publisher, Field.Store.YES, Field.Index.ANALYZED);
		 f.setBoost(60);
		 doc.add(f);
		//book introduction 
		 f = new Field("introduction", introduction, Field.Store.YES, Field.Index.ANALYZED);
		 f.setBoost(90);
		 doc.add(f);
		//book isbn
		 f = new Field("isbn", isbn, Field.Store.YES, Field.Index.NOT_ANALYZED);
		 f.setBoost(99);
		 doc.add(f);
		//book content;
		 Field content = new Field("content", getFullContentText(key), Field.Store.NO, Field.Index.ANALYZED);
		 content.setBoost(20);
		 doc.add(content);
		return doc;
	}

}
