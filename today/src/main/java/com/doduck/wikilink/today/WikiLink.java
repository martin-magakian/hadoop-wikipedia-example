package com.doduck.wikilink.today;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class WikiLink implements WritableComparable<WikiLink>{

	private Integer ns;
	private String link;
	
	public WikiLink() {
	}
	
	public WikiLink(Integer ns, String link) {
		this.ns = ns;
		this.link = link;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		ns = in.readInt();
		link = in.readUTF();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(ns);
        out.writeUTF(link);
	}

	@Override
	public int compareTo(WikiLink arg0) {
		return 0;
	}

	public Integer getNs() {
		return ns;
	}

	public String getLink() {
		return link;
	}


	
}
