package ydk.epub.data
{
	import flash.utils.ByteArray;

	public class Container extends XmlEpubDocument
	{
		public function Container(buf:ByteArray)
		{
			if (buf == null){
				_container = 
					<container version="1.0" xmlns="urn:oasis:names:tc:opendocument:xmlns:container">
					  <rootfiles>
						<rootfile full-path={Opf.fileName}
								  media-type="application/oebps-package+xml" />
					  </rootfiles>
					</container>;
			}else{
				parseXml(buf);
			}
		
		}
		
		public static const  _fileName:String = "META-INF/container.xml";
		
		public static function get fileName():String
		{
			return _fileName;
		}
		
//		public static function set fileName(value:String):void
//		{
//			_fileName = value;
//		}
		
		private var _container : XML;
		
		public function addRootFile(rootFile:String = "OEBPS/book.opf", mediaType:String = "application/oebps-package+xml"):void{
			var ns:Namespace = _container.namespace();
			var node:XML = <rootfile full-path={rootFile} media-type={mediaType} />
			_container.ns::rootfiles.appendChild(node);
		}
		public function get rootFile() : XMLList {
			var ns:Namespace = _container.namespace();
			return  _container.ns::rootfiles.ns::rootFile;
		}
		public override function parseXml (buf:ByteArray):void {
			_container = new XML(buf);
		}
		
		public override function serialize ():ByteArray {
			var buf:ByteArray = new ByteArray();
			buf.writeUTFBytes(XmlEpubDocument.xmlDecl);
			buf.writeUTFBytes(_container.toXMLString());
			return buf;
		}
		public override function toXMLString():String{
			return _container.toXMLString();
		
		}
	}
	
}

