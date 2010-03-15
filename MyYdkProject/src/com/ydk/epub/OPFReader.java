/**
 * 
 */
package com.ydk.epub;

import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.ydk.epub.api.ePubClient;
import com.adobe.epubcheck.xml.XMLParser;
import com.adobe.epubcheck.xml.XMLValidator;

/**
 * @author y1zhao
 *
 */
public class OPFReader {
	private ZipFile zip;
	private String path;
	private HashSet encryptedItemsSet;
	private ePubClient client;
	
	static XMLValidator opfValidator = new XMLValidator("rng/opf.rng");

	static XMLValidator opfSchematronValidator = new XMLValidator("sch/opf.sch");
	
	public OPFReader(ZipFile zip, String rootPath) {
		this.zip = zip;
		this.path = rootPath;
	}

	public void setEncryptedItemsSet(HashSet encryptedItemsSet) {
		this.encryptedItemsSet = encryptedItemsSet;
	}

	public void process() {
		ZipEntry opfEntry = zip.getEntry(path);
		if (opfEntry == null)
			return ;

		XMLParser opfParser = new XMLParser(zip, path, null);
		OPFContentHandler opfHandler = new OPFContentHandler(opfParser, path);
		opfHandler.setEncryptedItemsSet(encryptedItemsSet);
		opfHandler.setClient(client);
		opfParser.addXMLHandler(opfHandler);

		// add relaxNG validator
		opfParser.addValidator(opfValidator);

		// add schematron validator
		opfParser.addValidator(opfSchematronValidator);

		try {
			// validate according to relaxNG + schematron
			opfParser.process();
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	public void setClient(ePubClient client) {
		this.client = client;
		
	}

}
