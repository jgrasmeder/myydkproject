package com.ydk.epub;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Image {
	public String url;
	public byte[] data;
	
	public void save(String fn) throws IOException{
		OutputStream out = new FileOutputStream(fn);
		out.write(data);
		out.close();
	}
}
