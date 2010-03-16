package ydk.epub.data
{
	import mx.collections.ICollectionView;
	import mx.collections.IList;
	
	public interface INcx extends IList, ICollectionView
	{
		/***
		 * with given the chapter parser regexp and content of book
		 * build a ncx data structure and book chapter parts;
		 ***/
		function parseBook(chapterRegexp:Array, String content):Boolean;
		
		/***
		 * this is the property that return the current parsed NCX XML data;
		 ***/
		function get ncx():XML;
		
		
	}
}