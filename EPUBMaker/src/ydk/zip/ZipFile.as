package ydk.zip
{
	import flash.utils.ByteArray;
	import flash.utils.Endian;

	public class ZipFile
	{
		private var _zipName:String;
		
		private var _centralDirectory:CentralDirectory;
		private var _endRecord:EndCentralDirectoryRecord;
		private var _fileEntries:Array;

		public function ZipFile(zipName:String)
		{
			_zipName = zipName;
			trace ("contructed ZipFile:"+zipName);
			_centralDirectory = new CentralDirectory();
			_endRecord = new EndCentralDirectoryRecord();
			
		}
	
	//add file content by ByteArray, and give file name to the zip.
		public function addFile(contents:ByteArray, fileName:String, compress:Boolean = true):int {
			if (_fileEntries == null){
				_fileEntries = new Array();
			}
			//1. create the File Entry
			var fe:FileEntry = new FileEntry();
			fe.data = contents;
			fe.localHeader.uncompressedSize = contents.length;
			fe.localHeader.crc = CRC32.getCRC32(fe.data);
			if (compress){
				fe.data.deflate();
			}else{
				fe.localHeader.method = 0;
			}
			fe.localHeader.compressedSize = fe.data.length;
			trace("contents size="+contents.length);
			fe.fileName = fileName;
			
			//2. add it to fileEntries list;
			_fileEntries.push(fe);
			
			//3. add it to Central Directory
			_centralDirectory.addFileHeaderByFileEntry(fe);
			_endRecord.entryNumberOnDisk = _fileEntries.length;
			_endRecord.entryNumberTotal = _fileEntries.length;
			
			return 0;
		}
		public function addTextFile(content:String, fileName:String, encoding:String="ansi", compress:Boolean = true):void{
			var data:ByteArray = new ByteArray();
			data.writeMultiByte(content, encoding);
			addFile(data, fileName, compress);
		}
		public function parse(buf:ByteArray):void{
			_fileEntries = new Array();
			_endRecord = new EndCentralDirectoryRecord();
			_centralDirectory = new CentralDirectory();
			buf.endian = Endian.LITTLE_ENDIAN;
			while (buf.bytesAvailable > 0){
				var sig:uint = buf.readUnsignedInt();
				trace ("sig = "+sig);
				switch (sig){
					case LocalHeader.LOCAL_HEADER_SIGNATURE:
						var fe:FileEntry = new FileEntry();
						buf.position -= 4;
						fe.parse(buf);
						_fileEntries.push(fe);
						break;	
					case FileHeader.FILE_HEADER_SIGNATURE:
						var fh:FileHeader = new FileHeader();
						buf.position -= 4;
						fh.parse(buf);
						_centralDirectory.insertFileHeader(fh);
						break;
					case EndCentralDirectoryRecord.END_CENTRAL_DIRECTORY_RECORD_SIGNATURE:
						buf.position -= 4;
						_endRecord.parse(buf);
						break;
					default:
						trace ("invalid signature:"+sig);
						break;
				}
			}
			
		}
		public function serialize(buf:ByteArray, encoding:String="utf-8") : void{
			//1. serialize the file entries
			buf.endian = Endian.LITTLE_ENDIAN;
			var i:int;
				if (_fileEntries != null){
					for (i = 0; i < _fileEntries.length; i++){
					  	//1.1 serialize local header
						//1.2 serialize data (compress)
						var fe:FileEntry = FileEntry(_fileEntries[i]);
						fe.fh.offsetLocalHeader = buf.position;
						fe.serialize(buf);
						 //1.3 update central directory
						
					}
				}
			//2. serialize central directory
				//update the central directory offset
				_endRecord.centralDirectoryOffset = buf.position;
				if (_centralDirectory != null){
					_centralDirectory.serialize(buf, encoding);
				}
				
				_endRecord.centralDirectorySize = buf.position - _endRecord.centralDirectoryOffset;
				if (_endRecord != null){
					_endRecord.serialize(buf, encoding);
				}
			  //2.1 serialize File Headers
			  //2.2 Digital Signature
			  //2.3 End of Central Directory Record
		} 
		public function saveZipFile(fileUri:String) : void{
			//open the file buf
			//call the serialize API
			//close the file buf
		}
		public function saveLocalFile() : void{
			
		}
		public function get FileList () : Array{
			if (_centralDirectory != null){
				trace("get FileList is "+ _centralDirectory.fileHeaders);
				return _centralDirectory.fileHeaders;
			}else{
				trace("get FileList as null");
				return null;
			}
		}
		public function getFileEntry(fn:String) :  FileEntry{
			for (var i:int = 0; i < _fileEntries.length; i++){
				var fe:FileEntry = _fileEntries[i] as FileEntry;
				if (fe.fileName == fn){
					return fe;
				}
			}
			return null;
		}
	}
}