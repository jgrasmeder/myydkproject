package ydk.zip
{
	import flash.utils.ByteArray;


	public class FileEntry
	{
		public function FileEntry()
		{
			_localHeader = new LocalHeader();
		}
		private var _localHeader : LocalHeader;
		private var _data : ByteArray;
		private var _encoding : String = "utf-8";
		//private var _offset:uint = 0;
		private var _fileHeader:FileHeader;
		
		
		public function get fh () : FileHeader {
			return _fileHeader;
		}
		public function set fh ( v:FileHeader ) : void {
			_fileHeader = v;
		}
		public function get localHeader () : LocalHeader {
			return _localHeader;
		}
		public function set localHeader ( header:LocalHeader ) : void {
			_localHeader = header;
		}
		public function get data () : ByteArray {
			return _data;
		}
		public function set data (data : ByteArray) : void {
			_data = data;
		}
		public function get encoding () : String {
			return _encoding;
		}
		public function set encoding (encoding : String) : void {
			_encoding = encoding;
		}
		public function get fileName () : String {
			return _localHeader.fileName;
		}
		public function set fileName (fileName : String ) : void {
			_localHeader.fileName = fileName;
		}
		public function parse(buf:ByteArray) : void {
			if (_localHeader == null){
				_localHeader = new LocalHeader();
			}
			if (_localHeader.parse(buf) < 0){
				trace ("local header parse error");
			}
			if (_localHeader.compressedSize > 0){
				_data = new ByteArray();
				buf.readBytes(_data, 0, _localHeader.compressedSize);
				if (_localHeader.method == 8){
					_data.inflate();	
				}else if (_localHeader.method == 0){
					//do nothing
				}else{
					throw new Error("unsupported comress method:"+_localHeader.method);
				}
				if (_data.length != _localHeader.uncompressedSize){
					trace ("file inflate error");
				}
				if (CRC32.getCRC32(_data) != _localHeader.crc){
					trace ("file crc check error");
				}
			}
			
		}
		
		public function serialize(buf:ByteArray):void {
//			_localHeader.crc = CRC32.getCRC32(_data);
//			_localHeader.uncompressedSize = _data.length;
//			_data.deflate();
//			_localHeader.compressedSize = _data.length;
			trace("file content: size="+_localHeader.uncompressedSize+"  packed="+_localHeader.compressedSize);
			_localHeader.serialize(buf);
			if (_data != null){
				buf.writeBytes(_data);
			}
		}
		
	}
}