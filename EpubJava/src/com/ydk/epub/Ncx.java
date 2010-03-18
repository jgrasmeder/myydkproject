package com.ydk.epub;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

@SuppressWarnings("unchecked")
public class Ncx {
	private static final Logger log = Logger.getLogger(Ncx.class.getName());
	//this is for NCX
	private ZipFile _epubFile;
	private String NCX = "OEBPS/toc.ncx";
	private String ENCRYPTION = "META-INF/encryption.xml";
	private List<TocItem> _ncx = new ArrayList(); 
	private List<String> _encryption = new ArrayList();
	private Encrypter encrypter;
	public Ncx(ZipFile book) throws Exception{
		_epubFile = book;
		getEncryptedChapterList();
		parse();
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
		
		//for test only
//		System.out.println("in.available="+in.available());
//		BufferedReader fr = new BufferedReader(new InputStreamReader(in, "UTF-8"));
//		String ret = "";
//		String line;
//		while ((line =fr.readLine()) != null){
//			ret += line;
//		}
//		System.out.println("ret="+ret);
//		
//		in.reset();
		
		org.jdom.Document doc = builder.build(in);
		
		Element root = doc.getRootElement();
		//System.out.println(root.toString());
//		Namespace ns = root.getNamespace();
//		Namespace ds = root.getNamespace("ds");
		Namespace enc = root.getNamespace("enc");
//		List xl = root.getChildren("EncryptedData");
//		System.out.println("xl.size="+xl.size());
//		List xll = root.getChildren();
//		Iterator x11i = xll.iterator();
//		while (x11i.hasNext()){
//			Element xx11e = (Element) x11i.next();
//			System.out.println("xxlle="+xx11e.getName());
//			System.out.println("xxlle="+xx11e.getNamespace());
//		}
//		System.out.println("xll.size="+xll.size());
		List l = root.getChildren("EncryptedData", enc);
		if (l != null){
			Iterator iter = l.iterator();
			while (iter.hasNext()){
				Element child = (Element)iter.next();
				Element uri = child.getChild("CipherData", enc).getChild("CipherReference", enc);
				String src = uri.getAttributeValue("URI");
				System.out.println(src);
				if (src != null){
					_encryption.add(src);
				}
			}
		}
	}
	public boolean isChapterEncrypted(String url){
		if (url == null){
			return false;
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
