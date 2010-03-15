package com.ydk.epub;

public interface IMetadaa {
	public String bookUUID();
	public String title();
	public String[] authors();
	public String[] translators();
	public String publisher();
	public String publishDate();
	public String introduction();
	public String format();
	public String layout();
	public String paperCopyPrice();
	public String isbn();
	public int pages();
	public Image thumbnailBig();
	public Image thumbnailSamll();
	public Image thumbnail3DBig();
	public Image thumbnail3DSmall();
	
}
