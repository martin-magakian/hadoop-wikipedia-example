package com.doduck.wikilink.lib;

import java.io.IOException;

import org.xml.sax.SAXException;

public interface RevisionDelegate {

	public void revisionFound(String title, Integer ns, Integer id, String wikitxt) throws IOException,SAXException,SAXException;

}