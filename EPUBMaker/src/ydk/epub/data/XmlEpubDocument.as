package ydk.epub.data
{
	import flash.utils.ByteArray;

	public class XmlEpubDocument
	{
		public function XmlEpubDocument()
		{
		}
		private static var _xmlDecl:String = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
		
		public static function get xmlDecl():String
		{
			return _xmlDecl;
		}

		public static function set xmlDecl(value:String):void
		{
		}
		public function parseXml(buf:ByteArray):void {
		}
		public function serialize():ByteArray{
			return null;
		}
		public function toXMLString():String{
			return _xmlDecl;
		}

	}
}