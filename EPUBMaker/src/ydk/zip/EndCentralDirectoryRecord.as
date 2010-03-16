package ydk.zip
{
	import flash.utils.ByteArray;
	import flash.utils.Endian;

	/*
	
	end of central dir signature    4 bytes  (0x06054b50)
	number of this disk             2 bytes
	number of the disk with the
	start of the central directory  2 bytes
	total number of entries in the
	central directory on this disk  2 bytes
	total number of entries in
	the central directory           2 bytes
	size of the central directory   4 bytes
	offset of start of central
	directory with respect to
	the starting disk number        4 bytes
	.ZIP file comment length        2 bytes
	.ZIP file comment       (variable size)

	*/
	public class EndCentralDirectoryRecord
	{
		static public const  END_CENTRAL_DIRECTORY_RECORD_SIGNATURE:uint = 0x06054b50;
		public function EndCentralDirectoryRecord()
		{
		}
		protected var _signagure:uint = END_CENTRAL_DIRECTORY_RECORD_SIGNATURE;
		protected var _diskNo:int = 0;
		protected var _centralDirectoryStartDiskNo:int = 0;
		protected var _entryNumberOnDisk:int = 0;
		protected var _entryNumberTotal:int = 0;
		protected var _centralDirectorySize:uint = 0;
		protected var _centralDirectoryOffset:uint = 0;
		protected var _zipFileCommentLength:int = 0;
		protected var _zipFileComment:String;
		
		public function toString() : String {
			var ret:String = "EndCentralDirectoryRecord";
			
			ret += "\n _signagure:\t\t"+_signagure.toString(16).toUpperCase();
			ret += "\n _diskNo:\t\t"+ _diskNo;
			ret += "\n StartDiskNo:\t\t"+ _centralDirectoryStartDiskNo;
			ret += "\n _entryNumberOnDisk:\t"+ _entryNumberOnDisk;
			ret += "\n _entryNumberTotal:\t"+ _entryNumberTotal;
			ret += "\n _centralDirectorySize:\t"+ _centralDirectorySize;
			ret += "\n offset:\t\t"+ _centralDirectoryOffset;
			ret += "\n _zipFileCommentLength:\t"+ _zipFileCommentLength;
			ret += "\n _zipFileComment:\t\t"+ _zipFileComment;
			
			return ret;
			
		}
		public function get signagure() :uint {
			return _signagure;
		}
		public function set signagure (v:uint) : void {
			_signagure = v;
		}
		public function get diskNo() : int {
			return _diskNo;
		}
		public function set diskNo (v:int) : void {
			_diskNo = v;
		}
		public function get centralDirectoryStartDiskNo () :int {
			return _centralDirectoryStartDiskNo;
		}
		public function set centralDirectoryStartDiskNo(v:int) : void {
			_centralDirectoryStartDiskNo = v;
		}
		public function get entryNumberOnDisk () :int {
			return _entryNumberOnDisk;
		}
		public function set entryNumberOnDisk(v:int) : void {
			_entryNumberOnDisk = v;
		}
		public function get entryNumberTotal() :int {
			return _entryNumberTotal;
		}
		public function set entryNumberTotal(v:int) : void {
			_entryNumberTotal = v;
		}
		public function get centralDirectorySize() :uint {
			return _centralDirectorySize;
		}
		public function set centralDirectorySize(v:uint) : void {
			_centralDirectorySize = v;
		}
		public function get centralDirectoryOffset () :uint {
			return _centralDirectoryOffset;
		}
		public function set centralDirectoryOffset (v:uint) : void {
			_centralDirectoryOffset = v;
		}
		public function get zipFileCommentLength () :int {
			return _zipFileCommentLength;
		}
		public function set zipFileCommentLength (v:int) : void {
			_zipFileCommentLength = v;
		}
		public function get () :String {
			return _zipFileComment;
		}
		public function set zipFileComment (v:String) : void {
			_zipFileComment = v;
		}
		
		public function parse (buf:ByteArray, encoding:String = "utf-8") : void {
			_signagure = buf.readUnsignedInt();
			_diskNo = buf.readShort();
			_centralDirectoryStartDiskNo = buf.readShort();
			_entryNumberOnDisk = buf.readShort();
			_entryNumberTotal = buf.readShort();
			_centralDirectorySize = buf.readUnsignedInt();
			_centralDirectoryOffset = buf.readUnsignedInt();
			_zipFileCommentLength = buf.readShort();
			
			if (encoding == "utf-8") {
				_zipFileComment = buf.readUTFBytes(_zipFileCommentLength);
			} else {
				_zipFileComment = buf.readMultiByte(_zipFileCommentLength, encoding);
			}
			trace(toString());
		}
		public function serialize (buf:ByteArray, encoding:String = "utf-8") : void {
			buf.writeUnsignedInt(_signagure);
			buf.writeShort(_diskNo);
			buf.writeShort(_centralDirectoryStartDiskNo);
			buf.writeShort(_entryNumberOnDisk);
			buf.writeShort(_entryNumberTotal);
			buf.writeUnsignedInt(_centralDirectorySize);
			buf.writeUnsignedInt(_centralDirectoryOffset);
			
			if (_zipFileComment != null){
				var fc:ByteArray = new ByteArray();
				fc.endian = Endian.LITTLE_ENDIAN;
				if (encoding == "utf-8") {
					fc.writeUTFBytes(_zipFileComment);
				} else {
					fc.writeMultiByte(_zipFileComment, encoding);
				}
				_zipFileCommentLength = fc.position;
				buf.writeShort(_zipFileCommentLength); //2
				buf.writeBytes(fc);
			}else{
				_zipFileCommentLength = 0;
				buf.writeShort(_zipFileCommentLength); //2
			}
			
		}
	}
}