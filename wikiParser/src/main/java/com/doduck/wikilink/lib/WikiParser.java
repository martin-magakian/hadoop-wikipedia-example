package com.doduck.wikilink.lib;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WikiParser extends DefaultHandler{

	private boolean isNs;
	private boolean isText;
	private boolean isRootId;
	private boolean isTitle;
	
	private Integer ns;
	private Integer id;
	private String title;
	
	private RevisionDelegate revisionDelegate;
	


	public WikiParser(RevisionDelegate revisionDelegate){
		this.revisionDelegate = revisionDelegate;
	}
	
	public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
 
 
		if (qName.equalsIgnoreCase("ns")) {
			isNs = true;
		}
 
		if (qName.equalsIgnoreCase("id") && id == null) {
			isRootId = true;
		}
		
		if (qName.equalsIgnoreCase("text")) {
			isText = true;
		}
		
		if (qName.equalsIgnoreCase("title")) {
			isTitle = true;
		}
 
	}
 
	public void characters(char ch[], int start, int length) throws SAXException {
 
		if (isNs) {
			ns = Integer.valueOf(new String(ch, start, length));
			isNs = false;
		}
 
		if (isRootId) {
			id = Integer.valueOf(new String(ch, start, length));
			isRootId = false;
		}
		
		if(isTitle){
			title = new String(ch, start, length);
			isTitle = false;
		}
 
		if (isText) {
			String text = new String(ch, start, length);
			try {
				revisionDelegate.revisionFound(title, ns, id, text);
			} catch (IOException e) {
				throw new SAXException(e.getMessage());
			}
			
			isText = false;
		}
	}
	
	
	
}
