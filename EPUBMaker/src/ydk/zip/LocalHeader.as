package ydk.zip
{
	import flash.utils.ByteArray;
	import flash.utils.Endian;

	public class LocalHeader
	{
		static public const  EFS_UTF8:uint = 0x0400;
		static public const  LOCAL_HEADER_SIGNATURE:uint = 0x04034b50;
		protected var _fileHeader:ByteArray; //internal strucure to store the header information
		protected var _signature:uint = LOCAL_HEADER_SIGNATURE;
		protected var _version:uint = 0x0a;
		protected var _flag:int = 0;
		protected var _method:int = 8;
		protected var _date:Date = new Date();
		protected var _crc:uint = 0;
		protected var _compressedSize:uint = 0;
		protected var _uncompressedSize:uint = 0;
		protected var _fileNameLength:uint = 0;
		protected var _extraFieldLength:uint = 0;
		protected var _fileName:String;
		protected var _extraField:ByteArray;
		
		
		public function LocalHeader()
		{
			//_fileHeader.endian = Endian.LITTLE_ENDIAN;	
		}
		public function get utf8 () : Boolean {
			return ( _flag & EFS_UTF8 ) != 0;
		}
		public function isSignatureValid (sig:uint):Boolean {
			return sig == LOCAL_HEADER_SIGNATURE;
		}
		public function set signature (signature:uint):void {
			_signature = signature;
		} 
		public function get signature () : uint {
			return _signature;
		}
		
		public function get version():uint {
			return _version;
		}
		public function set version(version:uint):void {
			_version = version;
		}
		
		public function get flag():uint {
			return _flag;
		}
		public function set flag(flag:uint):void {
			_flag = flag;
		}
		
		public function get method ():int{
			return _method;
		}
		public function set method (method:int) :void {
			_method = method;
		}
		public function get date():Date {
			return _date;
		}
		public function set date (date:Date) :void{
			_date = date == null? new Date() : date;
		}
		public function get crc():uint {
			return _crc;
		}
		public function set crc (crc:uint) : void {
			_crc = crc;
		}
		public function get compressedSize () : uint {
			return _compressedSize;
		}
		public function set compressedSize(compressedSize:uint):void {
			_compressedSize = compressedSize;
		}
		public function get uncompressedSize () : uint {
			return _uncompressedSize;
		}
		public function set uncompressedSize (uncompressedSize:uint) :void{
			_uncompressedSize = uncompressedSize;
		}
		
		public function get fileNameLength() : uint {
			return _fileNameLength;
		}
		public function set fileNameLength(fileNameLength:uint) : void {
			_fileNameLength = fileNameLength;
		}
		public function get extraFieldLength () : uint {
			return _extraFieldLength;
		}
		public function set extraFieldLength( extraFieldLength:uint) : void {
			_extraFieldLength = extraFieldLength;
		}
		public function get fileName () : String {
			return _fileName;
		}
		public function set fileName (fileName:String ) : void {
			_fileName = fileName;
		}
		public function get extraField () : ByteArray {
			return _extraField;
		}
		public function set extraField (extraField : ByteArray ) : void {
			_extraField = extraField;
		}
		
		//return the compressed size; error return -1;
		public function parse (buf:ByteArray) : int {
			_signature = buf.readUnsignedInt();
			if (!isSignatureValid(_signature)){
				return -1;
			}
			_version = buf.readShort();
			_flag = buf.readShort();
			_method = buf.readShort();
			
			var dosTime:int  = buf.readShort();
			var dosDate:int = buf.readShort();
			var hr:int = (dosTime & 0xf800) >> 11;
			var min:int = (dosTime & 0x07e0) >> 5;
			var sec:int = dosTime & 0x001f;
			var year:int = ((dosDate & 0xfe00) >> 9 ) + 1980;
			var month:int = (dosDate & 0x01e0) >> 5 ;
			var dat:int = dosDate & 0x001f;
			
			_date = new Date(year, month - 1, dat, hr, min, sec);
			trace("localHeader date="+_date);
			_crc  = buf.readUnsignedInt();
			_compressedSize = buf.readUnsignedInt();
			_uncompressedSize = buf.readUnsignedInt();
			_fileNameLength = buf.readShort();
			_extraFieldLength = buf.readShort();
			
			if (utf8) {
				_fileName = buf.readUTFBytes(_fileNameLength);
			} else {
				_fileName = buf.readMultiByte(_fileNameLength, "ansi");
			}
			//extra we don't touch it this version
			if (_extraFieldLength > 0){
				_extraField = new ByteArray();
				buf.readBytes(_extraField, 0, _extraFieldLength);
			}
			trace ("localheader parsed:"+_fileName);
			trace ("size="+_uncompressedSize+"  packed="+_compressedSize);
			return _compressedSize;
			
		}
		
		
		public function serialize(buf:ByteArray, encoding:String = "utf-8"):void {
			
			//_fileHeader.clear();
			//_fileHeader.endian = endian;
			
			buf.writeUnsignedInt(_signature); //local file header signature     4 bytes  (0x04034b50)

			buf.writeShort(_version); //version needed to extract 10       2 bytes
			if (encoding == "utf-8"){
				_flag |=  EFS_UTF8;
			}

			buf.writeShort(_flag); //general purpose bit flag 2
			buf.writeShort(_method);//2 compression method
			
			var d:Date = (_date == null) ? new Date() : _date;
			var msdosTime:uint = uint(d.getSeconds()) | (uint(d.getMinutes()) << 5) | (uint(d.getHours()) << 11);
			var msdosDate:uint = uint(d.getDate()) | (uint(d.getMonth() + 1) << 5) | (uint(d.getFullYear() - 1980) << 9);
			
			buf.writeShort(msdosTime); //2 last mod file time
			buf.writeShort(msdosDate); //2 last mod file date
			
			buf.writeUnsignedInt(_crc); //2 crc-32 
			buf.writeUnsignedInt(_compressedSize); //2
			buf.writeUnsignedInt(_uncompressedSize); //2
			
			
			var fn:ByteArray = new ByteArray();
			fn.endian = Endian.LITTLE_ENDIAN;
			if (utf8) {
				fn.writeUTFBytes(_fileName);
			} else {
				fn.writeMultiByte(_fileName, encoding);
			}
			_fileNameLength = fn.position;
			trace ("fn.position="+fn.position+"  | fn.length="+fn.length+ "  | flag="+flag);
			buf.writeShort(_fileNameLength); //2
			buf.writeShort(_extraFieldLength); //2
			buf.writeBytes(fn);
		}
			
	}
}