package com.doduck.wikilink.today;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, WikiLink, Text, Text>{
	
	@Override
	protected void reduce(Text key, Iterable<WikiLink> values, Context context) throws IOException, InterruptedException {
		try{
			String pageLinked = key.toString();
			int totalLink = 0;
			
			StringBuilder sb = new StringBuilder();
			Iterator<WikiLink> iterator = values.iterator();
			while(iterator.hasNext()){
				WikiLink wikiLink = iterator.next();
				Integer ns = wikiLink.getNs();
				sb.append(ns);
				sb.append("|");
				sb.append(wikiLink.getLink());
				sb.append("\t");
				totalLink++;
			}
			String allLinks = sb.toString().substring(0, sb.length()-1); //remove last tab
			
			String result = totalLink +"\t"+allLinks;
			context.write(new Text(pageLinked), new Text(result));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}