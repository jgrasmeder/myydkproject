package ydk.zip
{
	import flash.utils.ByteArray;
	import flash.utils.Endian;

	public class FileHeader extends LocalHeader
	{
		static public const  FILE_HEADER_SIGNATURE:uint = 0x02014b50;
		public function FileHeader(endian:String=Endian.LITTLE_ENDIAN)
		{
			super();
			_signature = FILE_HEADER_SIGNATURE;
		}
		public function CloneFileHeader(lh:LocalHeader, endian:String=Endian.LITTLE_ENDIAN):void
		{
			if (lh == null){
				trace ("clone File Header failed, null localheader");
				return;
			}
			//super(endian);
			_signature = FILE_HEADER_SIGNATURE;
			_version = lh.version;
			_flag = lh.flag;
			_method = lh.method;
			_date = lh.date;
			_crc = lh.crc;
			_compressedSize = lh.compressedSize;
			_uncompressedSize = lh.uncompressedSize;
			_fileNameLength = lh.fileNameLength;
			_extraFieldLength = lh.extraFieldLength;
			_fileName = lh.fileName;
			_extraField = lh.extraField;

			_versionMade = 0x0a;
			_fileCommentsLength = 0;
			_diskNumberStart = 0;
			_internalFileAttributes = 0;
			_externalFileAttributes = 0;
			_offsetLocalHeader = 0;
			_fileComments = "";

			
		}
		protected var _versionMade:int = 0x0a;
		protected var _diskNumberStart:int;
		protected var _internalFileAttributes:int;
		protected var _externalFileAttributes:uint;
		protected var _offsetLocalHeader:uint;
		protected var _fileCommentsLength:int;
		protected var _fileComments:String;
		
		public function get versionMade () : int {
			return _versionMade;
		}
		public function set versionMade (version:int) : void {
			_versionMade = version;
		}
		public function get diskNumberStart() : int {
			return _diskNumberStart;
		}
		public function set diskNumberStart (diskNumber:int) : void {
			_diskNumberStart = diskNumber;
		}
		public function get internalFileAttributes () : int {
			return _internalFileAttributes;
		}
		public function set internalFileAttributes (att:int) : void {
			_internalFileAttributes = att;
		}
		public function get externalFileAttributes () : uint {
			return _externalFileAttributes;
		}
		public function set exeternalFileAttrbiutes (att:uint) : void {
			_externalFileAttributes = att;
		}
		public function get fileCommentsLength () : int {
			return _fileCommentsLength;
		}
		public function set fileCommentsLength (length:int) : void {
			_fileCommentsLength = length;
		}
		public function get fileComments () : String {
			return _fileComments;
		}
		public function set fileComments(comments:String) : void {
			_fileComments = comments;
		}
		public function get offsetLocalHeader () : uint {
			return _offsetLocalHeader;
		}
		public function set offsetLocalHeader(offsetLocalHeader:uint) : void {
			_offsetLocalHeader = offsetLocalHeader;
		}
		public override function isSignatureValid (sig:uint):Boolean {
			return sig == FILE_HEADER_SIGNATURE;
		}
		//return the compressed size; error return -1;
		public override function parse (buf:ByteArray) : int {
			_signature = buf.readUnsignedInt();
			if (!isSignatureValid(_signature)){
				trace ("invalid fileheader signature");
				return -1;
			}
			_versionMade = buf.readShort();
			_version = buf.readShort();
			_flag = buf.readShort();
			_method = buf.readShort();
			
			var dosTime:int  = buf.readShort();
			var dosDate:int = buf.readShort();
			var hr:int = (dosTime & 0xf800) >> 11;;
			var min:int = (dosTime & 0x07e0) >> 5;
			var sec:int = dosTime & 0x001f;
			var year:int = ((dosDate & 0xfe00) >> 9 ) + 1980;
			var month:int = (dosDate & 0x01e0) >> 5 ;
			var dat:int = dosDate & 0x001f;
			
			_date = new Date(year, month - 1, dat, hr, min, sec);
			trace("FileHeader date="+_date);
			_crc  = buf.readUnsignedInt();
			_compressedSize = buf.readUnsignedInt();
			_uncompressedSize = buf.readUnsignedInt();
			_fileNameLength = buf.readShort();
			_extraFieldLength = buf.readShort();
			_fileCommentsLength = buf.readShort();
			_diskNumberStart = buf.readShort();
			_internalFileAttributes = buf.readShort();
			_externalFileAttributes = buf.readUnsignedInt();
			_offsetLocalHeader = buf.readUnsignedInt();
			
			if (utf8) {
				_fileName = buf.readUTFBytes(_fileNameLength);
			} else {
				_fileName = buf.readMultiByte(_fileNameLength, "ansi");
			}
			//extra we don't touch it this version
			if (_extraFieldLength > 0){
				_extraField = new ByteArray();
				buf.readBytes(_extraField,0, _extraFieldLength);
			}
			if (_fileCommentsLength > 0 ){
				if (utf8) {
					_fileComments = buf.readUTFBytes(_fileCommentsLength);
				} else {
					_fileComments = buf.readMultiByte(_fileCommentsLength, "ansi");
				}
			}
			trace ("file header parsed:"+ _fileName);
			return _compressedSize;
			
		}
		
		
		public override function serialize(buf:ByteArray, encoding:String = "utf-8"):void {
			
			//_fileHeader.clear();
			//_fileHeader.endian = endian;
			
			buf.writeUnsignedInt(_signature); //local file header signature     4 bytes  (0x04034b50)
			buf.writeShort(_versionMade);
			buf.writeShort(_version); //version needed to extract 10       2 bytes
			if (encoding == "utf-8"){
				_flag |= EFS_UTF8;
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
			
			if (_fileComments != null){
				var fc:ByteArray = new ByteArray();
				fc.endian = Endian.LITTLE_ENDIAN;
				if (utf8) {
					fc.writeUTFBytes(_fileComments);
				} else {
					fc.writeMultiByte(_fileComments, encoding);
				}
				
				_fileCommentsLength = fc.position;
			}else{
				_fileCommentsLength = 0;
			}
			buf.writeShort(_fileNameLength); //2
			buf.writeShort(_extraFieldLength); //2
			buf.writeShort(_fileCommentsLength); //2
			buf.writeShort(_diskNumberStart);
			buf.writeShort(_internalFileAttributes);
			buf.writeUnsignedInt(_externalFileAttributes);
			buf.writeUnsignedInt(_offsetLocalHeader);
			buf.writeBytes(fn);
			//extra field is not touched.
			if (_fileCommentsLength > 0){
				buf.writeBytes(fc);	
			}
		}
		
	}
}