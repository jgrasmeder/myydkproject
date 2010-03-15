package com.ydk.epub;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

@SuppressWarnings("unchecked")
public class Ncx {
	//this is for NCX
	private ZipFile _epubFile;
	private String NCX = "OEBPS/toc.ncx";
	private String ENCRYPTION = "META-INF/encryption.xml";
	private List<TocItem> _ncx = new ArrayList(); 
	private List<String> _encryption = new ArrayList();
	private Encrypter encrypter;
	public Ncx(ZipFile book) throws IOException, JDOMException{
		_epubFile = book;
		getEncryptedChapterList();
	}
	public void parse() throws Exception{
		ZipEntry ncx = _epubFile.getEntry(NCX);
		if (ncx == null) {
			throw new Exception("toc.ncx not exist");
		}
		SAXBuilder builder = new SAXBuilder();
		InputStream in = _epubFile.getInputStream(ncx);
		org.jdom.Document doc = builder.build(in);
		
		Element root = doc.getRootElement();
		Namespace ns = root.getNamespace();
		Element navMap = root.getChild("navMap", ns);
		List l = navMap.getChildren();
		if (l != null){
			Iterator iter = l.iterator();
			while (iter.hasNext()){
				Element child = (Element)iter.next();
				_ncx.add(new TocItem(_epubFile, this, child));
			}
		}

	}
	private void getEncryptedChapterList() throws IOException, JDOMException{
		ZipEntry fe = _epubFile.getEntry(ENCRYPTION);
		if (fe == null) {
			return;
		}
		SAXBuilder builder = new SAXBuilder();
		InputStream in = _epubFile.getInputStream(fe);
		org.jdom.Document doc = builder.build(in);
		
		Element root = doc.getRootElement();
		Namespace ns = root.getNamespace();
//		Namespace ds = root.getNamespace("ds");
		Namespace enc = root.getNamespace("enc");
		
		List l = root.getChildren("EncryptedData", enc);
		if (l != null){
			Iterator iter = l.iterator();
			while (iter.hasNext()){
				Element child = (Element)iter.next();
				Element uri = child.getChild("CipherData", enc).getChild("CipherReference", enc);
				_encryption.add(uri.getAttributeValue("URI", ns));
			}
		}
	}
	public boolean isChapterEncrypted(String url){
		if (url == null){
			return true;
		}
		if (_encryption != null){
			Iterator iter = _encryption.iterator();
			while (iter.hasNext()){
				String _url = (String)iter.next();
				if (_url.compareToIgnoreCase(url) ==0 ){
					return true;
				}
			}
		}
		return false;
	}
	public List<TocItem> getToc(){
		return _ncx;
	}
	public void setKey(String key) {
		if (encrypter == null){
			encrypter = new Encrypter(key);
		}else{
			encrypter.init(key);
		}
		
	}
	public Encrypter getEncrypter() {
		return encrypter;
	}
	
	
}
