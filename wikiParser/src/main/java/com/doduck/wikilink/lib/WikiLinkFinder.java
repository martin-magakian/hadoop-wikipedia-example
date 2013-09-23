package com.doduck.wikilink.lib;

import info.bliki.wiki.model.WikiModel;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class WikiLinkFinder {

	public Set<String> findLinksInWikiFormat(String wikiContent) throws UnsupportedEncodingException {
		
		String wikiHtml = WikiModel.toHtml(wikiContent);
		Iterator<Element> linkIterator = Jsoup.parse(wikiHtml).select("a[href]").iterator();
		
		HashSet<String> links = new HashSet<String>();
		while(linkIterator.hasNext()){
			String link = linkIterator.next().attr("href");
			if(isCorrectLink(link)){
				link = cleanLink(link);
				links.add(link);
			}
		}
		return links;
	}

	private String cleanLink(String link) {
		int end = link.indexOf("#");
		if(end != -1){
			link = link.substring(0, end);
		}
		
		if(link.startsWith("/")){
			link = link.substring(1);
		}
		
		return link;
	}

	private boolean isCorrectLink(String link) {
		String lowerString = link.toLowerCase();
		if(lowerString.startsWith("#")) return false;
		
		if(lowerString.startsWith("/image:")) return false;
		
		if(lowerString.startsWith("/file:")) return false;
		
		/*if(lowerString.startsWith("/wp:")) return false;
		
		if(lowerString.startsWith("/wikipedia:")) return false;*/
		
		return true;
	}
}