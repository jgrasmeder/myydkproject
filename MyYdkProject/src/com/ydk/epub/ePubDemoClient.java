/**
 * 
 */
package com.ydk.epub;

import com.ydk.epub.api.ePubClient;
import com.adobe.epubcheck.xml.XMLAttribute;
import com.adobe.epubcheck.xml.XMLElement;

/**
 * @author y1zhao
 *
 */
public class ePubDemoClient implements ePubClient {

	public void handleItem(String id, String href, String mimeType,
			String fallback, String fallbackStyle, String namespace) {
		System.out.println("id:"+id+" href:"+href+" mimeType:"+mimeType+" fallback:"+fallback+" fallbackStyle:"+fallbackStyle
				+" namespace:"+namespace);
		
	}

	public void handlePrimaryId(XMLElement e,String idAttr) {
		System.out.println("Primary Id: "+idAttr);
		System.out.println("element: "+e.getName());
		for(int i=0;i<e.getAttributeCount();i++)
		{
			XMLAttribute attr = e.getAttribute(i);
			System.out.println("value:" + attr.getValue()+" getName: "+attr.getName()
					+" getNamespace: "+attr.getNamespace()+" getPrefix: "+attr.getPrefix());			
		}
		
	}

	public void handleAttribute(XMLElement e, String content) {
		System.out.println("element: "+e.getName());
		for(int i=0;i<e.getAttributeCount();i++)
		{
			XMLAttribute attr = e.getAttribute(i);
			System.out.println("value:" + attr.getValue()+" getName: "+attr.getName()
					+" getNamespace: "+attr.getNamespace()+" getPrefix: "+attr.getPrefix());			
		}
		System.out.println(content);				
	}

}
