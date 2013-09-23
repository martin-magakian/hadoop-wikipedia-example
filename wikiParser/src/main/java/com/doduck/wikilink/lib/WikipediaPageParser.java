package com.doduck.wikilink.lib;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.tools.ant.filters.StringInputStream;
import org.xml.sax.SAXException;

public class WikipediaPageParser{


	public WikipediaPageParser(String xml, RevisionDelegate delegate) throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		WikiParser p = new WikiParser(delegate);
		saxParser.parse(new StringInputStream(xml, "UTF-8"), p);
	}

}
