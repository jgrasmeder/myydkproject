package com.ydk.epub;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
/*
 * <navPoint class="chapter" id="content_1_1" playOrder="6">
      <navLabel>
        <text>Chapter title</text>
      </navLabel>
      <content src="content_1_1.xhtml"/>
    </navPoint>
 */
public class TocItem {
	
	private ZipFile _epubbook;
	private String title;
	private String url;
	private String chapterClass;
//	private String content;
	private boolean encrypted = false;
	private List<TocItem> children = new ArrayList<TocItem>();
	private Ncx _ncx;
	
	@SuppressWarnings("unchecked")
	public TocItem(ZipFile epubbook, Ncx ncx, Element e /*NavPoint*/){
		_epubbook = epubbook;
		_ncx = ncx;
		Namespace ns = e.getNamespace();
		title = e.getChild("navLabel",ns).getChild("text", ns).getText();
		url = e.getChild("content",ns).getAttributeValue("src", ns);
		encrypted = ncx.isChapterEncrypted(url);
		chapterClass = e.getAttributeValue("class",ns);
		
		
		List<Element> l = e.getChildren("navPoint");
		if (l != null){
			Iterator<Element> iter = l.iterator();
			while (iter.hasNext()){
				Element child = (Element)iter.next();
				children.add(new TocItem(_epubbook, ncx, child));
			}
		}
	}
	
	//for WAP or WEB to read a chapter;
	public String getConent() throws Exception{
		ZipEntry fe = _epubbook.getEntry(Book.OEBPS+"url");
		if (fe == null){
			return null;
		}
		InputStream in = _epubbook.getInputStream(fe);
		
		if (encrypted){
			Encrypter encrypter = _ncx.getEncrypter();
			if (encrypter == null){
				throw new Exception("Encrypted content without key set.");
			}
			in = encrypter.decrypt(in);
		}
		BufferedReader fr = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		String ret = "";
		String line;
		while ((line =fr.readLine()) != null){
			ret += line;
		}
		return ret;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public String getChapterClass() {
		return chapterClass;
	}

	//strip the html tags, only text for lucene search.
	public String getTextContent() throws Exception{
		ZipEntry fe = _epubbook.getEntry(Book.OEBPS+"url");
		if (fe == null){
			return null;
		}
		InputStream in = _epubbook.getInputStream(fe);
		if (encrypted){
			Encrypter encrypter = _ncx.getEncrypter();
			if (encrypter == null){
				throw new Exception("Encrypted content without key set.");
			}
			in = encrypter.decrypt(in);
		}
		SAXBuilder builder = new SAXBuilder();
		org.jdom.Document doc = builder.build(in);

		Element root = doc.getRootElement();
		String ret = extractText(root);
		if (ret == null){
			ret = "";
		}
		if (children != null){
			Iterator<TocItem> iter = children.iterator();
			while (iter.hasNext()){
				TocItem child = (TocItem)iter.next();
				ret += child.getTextContent();
			}
		}
		return ret;
		
	}
	@SuppressWarnings("unchecked")
	private String extractText(Element e){
		String ret = "";
		if (e.getText() != null){
			ret += e.getText();
		}
		List<Element> l = e.getChildren();
		if (l != null){
			Iterator<Element> iter = l.iterator();
			while (iter.hasNext()){
				Element child = (Element)iter.next();
				ret += extractText(child);
			}
		}
		return ret;
	}
//	private InputStream decryptContent(InputStream in, String key) throws IOException{
//		if (!encrypted){
//			return in;
//		}
//		int blen = in.available();
//		byte[] data = new byte[blen];
//		for (int i = 0; i < blen; i++){
//			data[i] = (byte)in.read();
//		}
//		in.close();
//		
//		return in;
//	}

	
}
