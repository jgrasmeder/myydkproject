/**
 * 
 */
package com.ydk.epub.api;

import com.adobe.epubcheck.xml.XMLElement;

/**
 * @author y1zhao
 *
 */
public interface ePubClient {

	void handleItem(String id, String href, String mimeType, String fallback,
			String fallbackStyle, String namespace);

	void handlePrimaryId(XMLElement e, String idAttr);

	void handleAttribute(XMLElement e, String content);

}
