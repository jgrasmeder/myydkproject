package ydk.epub.data
{
	import flash.utils.ByteArray;

	public class Ncx extends XmlEpubDocument
	{
		public function Ncx(buf:ByteArray)
		{
			if (buf == null){
				init();
			}else{
				parseXml(buf);
			}
		}
		
		public function init() : void {
			_ncx = 
				<ncx version="2005-1" 
					xmlns="http://www.daisy.org/z3986/2005/ncx/" >
					  <head>
						<meta name="dtb:uid" content="urn:uuid:4f7a11a1-626f-4696-9f37-35c62a859dbf"/>
						<meta name="dtb:depth" content="3"/>
						<meta name="dtb:totalPageCount" content="0"/>
						<meta name="dtb:maxPageNumber" content="0"/>
					  </head>
					 <docTitle>
						   <text>doc Title missing</text>
					 </docTitle>
					 <docAuthor>
						 <text>doc author missing</text>
					 </docAuthor>
					<navMap >
					</navMap>
				</ncx>;
		}
		private var _fileName:String = "toc.ncx";
		
		public function get fileName():String
		{
			return _fileName;
		}
		
		public function set fileName(value:String):void
		{
			_fileName = value;
		}
		private var _ncxName:String = "toc.ncx";
		private var _xmlDecl:String = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		private var _xmlDocType:String = "<!DOCTYPE ncx PUBLIC \n" +
			"							\"-//NISO//DTD ncx 2005-1//EN\" \n " +
										"\"http://www.daisy.org/z3986/2005/ncx-2005-1.dtd\" >\n";
		private var _ncx:XML;// =
			
		
		public function get xmlDocType():String
		{
			return _xmlDocType;
		}

		public function set xmlDocType(value:String):void
		{
			_xmlDocType = value;
		}

		public function get xmlDecl():String
		{
			return _xmlDecl;
		}

		public function set xmlDecl(value:String):void
		{
			_xmlDecl = value;
		}

		public function get ncxName():String
		{
			return _ncxName;
		}

		public function set ncxName(value:String):void
		{
			_ncxName = value;
		}

		public function get dtbUid():String{
			//var dtb:Namespace = _metadata.namespace("dtb");
			return _ncx.head.meta.(@name == "dtb:uid").@content;
		} 
		public function set dtbUid (uid:String) : void {
			_ncx.head.meta.(@name == "dtb:uid").@content = uid;
		}
		
		public function get docTitle():String {
			return _ncx.docTitle.text;
		}
		public function set docTitle(title:String) : void {
			_ncx.docTitle.text = title;
		}
		public function get docAuthor():String {
			return _ncx.docAuthor.text;
		}
		public function set docAuthor(auhtor:String) : void {
			_ncx.docAuthor.text = auhtor;
		}
		
		public function get navMap ():XML{
			var ns:Namespace = _ncx.namespace();
			return _ncx.ns::navMap[0];
		}
		public function addNavPoint(parent:XML,  label:String, src:String, id:String ="undefined", navClass:String = "chapter",  playOrder:String = "undefined"):XML {
			var ns:Namespace = _ncx.namespace();
			
			if (parent == null){
				parent = _ncx.ns::navMap[0];
			}
			var node:XML = 
				<navPoint >
					<navLabel>
						<text>{label}</text>
					</navLabel>
					<content />
				</navPoint>;
			node.@["class"]=navClass;
			node.@id = id;
			node.@playOrder = playOrder;
			node.content.@src=src;
			parent.appendChild(node);
			return node;
		}
		public override function parseXml(buf:ByteArray):void {
			_ncx = XML(buf);
		}
		public override function serialize():ByteArray{
			var data:ByteArray = new ByteArray();
			data.writeUTFBytes(_xmlDecl);
			data.writeUTFBytes(_xmlDocType);
			data.writeUTFBytes(_ncx.toXMLString());
			return data;
			
		}
		public override function toXMLString():String{
			return _ncx.toXMLString();
		}
	}
}