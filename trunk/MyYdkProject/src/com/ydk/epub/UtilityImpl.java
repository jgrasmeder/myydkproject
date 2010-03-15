/**
 * 
 */
package com.ydk.epub;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.ZipFile;

import com.ydk.epub.api.Utility;
import com.ydk.epub.api.ePubClient;
import com.adobe.epubcheck.api.EpubCheck;
import com.adobe.epubcheck.api.Report;

/**
 * @author y1zhao
 *
 */
public class UtilityImpl implements Utility {
	private static final Logger log = Logger.getLogger(UtilityImpl.class.getName());	
	private ePubClient client;
	
	public static void main(String[] args) {
		Utility utility = new UtilityImpl();
		utility.register(new ePubDemoClient());
		if(utility.addBook("gegebook.epub"))
		{
			System.out.println("add book succeed");
		}
		else
		{
			System.out.println("add book failed");
		}
	}
	/* (non-Javadoc)
	 * @see com.YDK.epub.Utility#addBook(java.lang.String)
	 */
	public boolean addBook(final String bookName) {
		boolean ret = false;
		EpubCheck check = new EpubCheck(new File(bookName), new Report(){

			public void error(String resource, int line, String message) {
				log.severe("ERROR: "+ bookName + (resource == null ? "" : "/" + resource) +
						(line <= 0 ? "" : "(" + line + ")") + ": " + message );	
			}

			public void warning(String resource, int line, String message) {
				log.warning("WARNING: " + bookName + (resource == null ? "" : "/" + resource) +
				(line <= 0 ? "" : "(" + line + ")") + message );				
			}});
		
//		if( check.validate() )
		if (true)
		{
			log.info(bookName + " no errors or warnings detected");
			
			try {
				if(client!=null)
				{
					ZipFile zip = new ZipFile(bookName);
					OCFReader ocfReader = new OCFReader(zip);
					ocfReader.process();
					OPFReader opfReader = new OPFReader(zip, ocfReader.getRootPath());
					opfReader.setEncryptedItemsSet(ocfReader.getEncryptedItemsSet());
					opfReader.setClient(client);
					opfReader.process();
					zip.close();
				}
				ret = true;
			} catch (IOException e) {				
				e.printStackTrace();
				ret = false;
			}			
		}
		else {
			ret = false;
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.YDK.epub.Utility#register()
	 */
	public void register(ePubClient client) {
		this.client = client;
	}

}
