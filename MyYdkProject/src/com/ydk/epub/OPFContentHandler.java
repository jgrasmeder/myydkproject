/**
 * 
 */
package com.ydk.epub;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.logging.Logger;

import com.ydk.epub.api.ePubClient;
import com.adobe.epubcheck.opf.OPFItem;
import com.adobe.epubcheck.util.PathUtil;
import com.adobe.epubcheck.xml.XMLElement;
import com.adobe.epubcheck.xml.XMLHandler;
import com.adobe.epubcheck.xml.XMLParser;

/**
 * @author y1zhao
 *
 */
public class OPFContentHandler implements XMLHandler {
	private static final Logger log = Logger.getLogger(OPFContentHandler.class.getName());	

	private XMLParser parser;
	private String path;
	private HashSet encryptedItemsSet;
	private ePubClient client;
	private boolean meta = false;
	
	//This string holds the value of the <package> element's unique-identifier attribute
	//that will be used to make sure that the unique-identifier references an existing
	//<dc:identifier> id attribute
	String uniqueIdent;
	
	public OPFContentHandler(XMLParser opfParser, String path) {
		this.parser = opfParser;
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see com.adobe.epubcheck.xml.XMLHandler#characters(char[], int, int)
	 */
	public void characters(char[] chars, int arg1, int arg2) {
		if(meta)
		{
			String content = String.copyValueOf(chars, arg1, arg2);
			XMLElement e = parser.getCurrentElement();
			String name = e.getName();
			if (name.equals("identifier"))
			{
				String idAttr = e.getAttribute("id");
				if(idAttr != null && !idAttr.equals("") && idAttr.equals(uniqueIdent))
				{					
					client.handlePrimaryId(e, content);
					return;
				}
			}			
			client.handleAttribute(e, content);					
		}

	}

	/* (non-Javadoc)
	 * @see com.adobe.epubcheck.xml.XMLHandler#endElement()
	 */
	public void endElement() {
		meta =false;
	}

	/* (non-Javadoc)
	 * @see com.adobe.epubcheck.xml.XMLHandler#ignorableWhitespace(char[], int, int)
	 */
	public void ignorableWhitespace(char[] chars, int arg1, int arg2) {

	}

	/* (non-Javadoc)
	 * @see com.adobe.epubcheck.xml.XMLHandler#processingInstruction(java.lang.String, java.lang.String)
	 */
	public void processingInstruction(String arg0, String arg1) {

	}

	/* (non-Javadoc)
	 * @see com.adobe.epubcheck.xml.XMLHandler#startElement()
	 */
	public void startElement() {
		boolean registerEntry = true;
		XMLElement e = parser.getCurrentElement();
		String ns = e.getNamespace();
		if (ns == null || ns.equals("") || ns.equals("http://openebook.org/namespaces/oeb-package/1.0/")
				|| ns.equals("http://www.idpf.org/2007/opf")) {
			String name = e.getName();
			if( name.equals("package") ) {
				if(!ns.equals("http://www.idpf.org/2007/opf"))
				{
					log.warning("OPF file is using OEBPS 1.2 syntax allowing backwards compatibility");					
				}
				/* This section checks to see the value of the
				 * unique-identifier attribute and stores it 
				 * in the String uniqueIdent or reports an error
				 * if the unique-identifier attribute is missing
				 * or does not have a value
				 */
				String uniqueIdentAttr = e.getAttribute("unique-identifier");
				if ( uniqueIdentAttr != null && !uniqueIdentAttr.equals(""))
				{
					uniqueIdent = uniqueIdentAttr;
				}
				else
				{
					parser.getReport().error(path, parser.getLineNumber(), "unique-identifier attribute in package element must be present and have a value");
				}
			} else if (name.equals("item")) {
				String id = e.getAttribute("id");
				String href = e.getAttribute("href");				
				if (href != null) {
					try {
						// if the entry is encrypted per encryption.xml file
						if (encryptedItemsSet != null && encryptedItemsSet.contains(URLDecoder.decode(href, "UTF-8"))) {
							// then do not register the entry (it shouldn't be checked)
							registerEntry = false;
							// if the entry is not required, warn and continue
							if(isNotRequiredContent(href))
								log.warning(href + " is an encrypted non-required entry! Epubcheck will not validate " + href);
							// else (the entry is requried), error and exit (cannot continue with encrypted required content!)
							else {
								log.severe(href + " is an encrypted required entry! \nEpubcheck will not validate ePubs with encrypted required content files! Tool will EXIT");
								return ;
							}
						}
					} catch (Exception ex) {
						System.err.println("Error decoding entry: " + name);
						ex.printStackTrace();
						log.severe(ex.getMessage());
					}
						try {
						href = PathUtil.resolveRelativeReference(path, href);
					} catch( IllegalArgumentException ex ) {
						parser.getReport().error(path, parser.getLineNumber(), ex.getMessage());
						href = null;
					}
				}
				String mimeType = e.getAttribute("media-type");
				String fallback = e.getAttribute("fallback");
				String fallbackStyle = e.getAttribute("fallback-style");
				String namespace = e.getAttribute("island-type");
						
				if (id != null) {
					client.handleItem(id, href, mimeType, fallback, fallbackStyle, namespace);							
				}
				
			} 			
		}
		else if(ns.equals("http://purl.org/dc/elements/1.1/"))
		{
			// in the DC metadata, when the <identifier> element is parsed, if it has
			// a non-null and non-empty id attribute value that is the same as the
			// value of the unique-identifier attribute of the package element,
			// set uniqueIdentExists = true (to make sure that the unique-identifier
			// attribute references an existing <identifier> id attribute			
			meta = true;
		}
	}

	/** 
	 * This method is used to check whether the passed href has
	 * and extension that is a required content file inside
	 * the OPF spec. This method returns false if it is requried,
	 * or true if it is not.
	 * 
	 * @param href String to check
	 * @return true if it is not required, false if it is.
	 */
	public boolean isNotRequiredContent(String href) {
		if(href.endsWith(".opf"))
			return false;
		else if(href.endsWith(".html"))
			return false;
		else if (href.endsWith(".ncx"))
			return false;
		else if (href.endsWith(".xpgt"))
			return false;
		else if (href.endsWith(".xhtml"))
			return false;
		else
			return true;
		
	}

	public void setEncryptedItemsSet(HashSet encryptedItemsSet) {
		this.encryptedItemsSet = encryptedItemsSet;
		
	}

	public void setClient(ePubClient client) {
		this.client = client;
	}

}
