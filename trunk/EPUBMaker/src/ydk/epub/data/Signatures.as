package ydk.epub.data
{
	public class Signatures
	{
		public function Signatures()
		{
		}
		private var _fileName:String = "META-INF/signatures.xml";
		
		public function get fileName():String
		{
			return _fileName;
		}
		
		public function set fileName(value:String):void
		{
			_fileName = value;
		}
	}
}