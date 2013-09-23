package com.doduck.wikilink.today;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.xml.sax.SAXException;
import com.doduck.wikilink.lib.RevisionDelegate;
import com.doduck.wikilink.lib.WikiLinkFinder;
import com.doduck.wikilink.lib.WikipediaPageParser;

public class Map extends Mapper<LongWritable, Text, Text, WikiLink>{

	private WikiLinkFinder wikiLinkFinder = new WikiLinkFinder();
	private Context context;
	
	@Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
    {
		this.context = context;
		try{
			String xml = value.toString();
			new WikipediaPageParser(xml, new RevisionDelegate() {

				@Override
				public void revisionFound(String title, Integer ns, Integer id, String wikitxt) throws IOException {
					try {
						writeLinks(title, ns, id, wikitxt);
					} catch (Exception e) {
						e.printStackTrace();
						throw new IOException(e.getMessage());
					}
				}
				
			});
		}catch(SAXException e){
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
    }

	
	private void writeLinks(String title, Integer ns, Integer id, String wikitxt) throws IOException, InterruptedException, UnsupportedEncodingException {
		String titleAsLink = titleAsLink(title);
    	Set<String> links = wikiLinkFinder.findLinksInWikiFormat(wikitxt);
    	for(String link : links){
    		context.write(new Text(link), new WikiLink(ns, titleAsLink));
    	}
	}
	
	private String titleAsLink(String title) throws UnsupportedEncodingException {
		String titleAsLink = title.replace(" ", "_");
		titleAsLink = URLEncoder.encode(titleAsLink, "UTF-8");
		return titleAsLink;
	}
	
}