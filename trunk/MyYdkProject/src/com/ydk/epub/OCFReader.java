/**
 * 
 */
package com.ydk.epub;

import java.util.HashSet;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.adobe.epubcheck.ocf.OCFHandler;
import com.adobe.epubcheck.xml.XMLParser;
import com.adobe.epubcheck.xml.XMLValidator;
/**
 * @author y1zhao
 *
 */
public class OCFReader {
	private static final Logger log = Logger.getLogger(OCFReader.class.getName());	
	
	private String rootPath;
	private ZipFile zip;
	private HashSet encryptedItemsSet;

	static XMLValidator containerValidator = new XMLValidator(
			"rng/container.rng");

	static XMLValidator encryptionValidator = new XMLValidator(
			"rng/encryption.rng");

	static XMLValidator signatureValidator = new XMLValidator(
			"rng/signatures.rng");

	public OCFReader(ZipFile zip) {
		this.zip = zip;
	}

	public void process() {
		// Validate container.xml
		String containerEntry = "META-INF/container.xml";
		ZipEntry container = zip.getEntry("META-INF/container.xml");
		if (container == null) {
			log.severe("META-INF/container.xml is missing");			
		} else {
			XMLParser containerParser = new XMLParser(zip, containerEntry,
					null);
			OCFHandler containerHandler = new OCFHandler(containerParser);
			containerParser.addXMLHandler(containerHandler);
			containerParser.addValidator(containerValidator);
			containerParser.process();
			rootPath = containerHandler.getRootPath();
		}

		// Validate encryption.xml
		String encryptionEntry = "META-INF/encryption.xml";
		ZipEntry encryption = zip.getEntry(encryptionEntry);
		if (encryption == null) {
			// System.out.println("No encryption.xml found!");
			// placeholder! No error is generated if encryption.xml is missing
		} else {
			XMLParser encryptionParser = new XMLParser(zip, encryptionEntry,
					null);
			OCFHandler encryptionHandler = new OCFHandler(encryptionParser);
			encryptionHandler.setPopulateEnryptedItems(true); 
			encryptionHandler.setRootBase(processRootPath(rootPath)); 
			encryptionParser.addXMLHandler(encryptionHandler);
			encryptionParser.addValidator(encryptionValidator);
			encryptionParser.process();
			// retrieve encrypted items hash set
			encryptedItemsSet = encryptionHandler.getEncryptedItems();
		}

		// Validate signatures.xml
		String signatureEntry = "META-INF/signatures.xml";
		ZipEntry signatures = zip.getEntry(signatureEntry);
		if (signatures == null) {
			// System.out.println("No signatures.xml found!");
			// placeholder! No error is generated if signature.xml is missing
		} else {
			XMLParser signatureParser = new XMLParser(zip, signatureEntry,
					null);
			OCFHandler signatureHandler = new OCFHandler(signatureParser);
			signatureParser.addXMLHandler(signatureHandler);
			signatureParser.addValidator(signatureValidator);
			signatureParser.process();
		}		
	}

	/**
	 * This method processes the rootPath String and returns the base path to
	 * the directory that contains the OPF content file.
	 * 
	 * @param rootPath
	 *            path+name of OPF content file
	 * @return String containing path to OPF content file's directory inside ZIP
	 */
	public String processRootPath(String rootPath) {
		String rootBase = rootPath;
		if (rootPath.endsWith(".opf")) {
			int slash = rootPath.lastIndexOf("/");
			if (slash < rootPath.lastIndexOf("\\"))
				slash = rootPath.lastIndexOf("\\");
			if (slash >= 0 && (slash + 1) < rootPath.length())
				rootBase = rootPath.substring(0, slash + 1);
			else
				rootBase = rootPath;
			return rootBase;
		} else {
			System.out.println("RootPath is not an OPF file");
			return null;
		}
	}

	/**
	 * @return the rootPath
	 */
	public final String getRootPath() {
		return rootPath;
	}

	/**
	 * @return the encryptedItemsSet
	 */
	public final HashSet getEncryptedItemsSet() {
		return encryptedItemsSet;
	}
}
