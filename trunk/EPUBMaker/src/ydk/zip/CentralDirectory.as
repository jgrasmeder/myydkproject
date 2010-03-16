package ydk.zip
{
	import flash.utils.ByteArray;

	public class CentralDirectory
	{
		public function CentralDirectory()
		{
			//_signature = new DigitalSignature();
		}
		private var _fileHeaders:Array = new Array();
		private var _signature:DigitalSignature;
		
		public function get signature() : DigitalSignature {
			return _signature;
		}
		public function set signature (signature:DigitalSignature) : void {
			_signature = signature;
		}
		
		public function get fileHeaders () : Array {
			
			return _fileHeaders;
		}
		
		public function insertFileHeader(fh:FileHeader, index:int = -1) : void {
			if (_fileHeaders == null){
				_fileHeaders = new Array();
			}
			if (index == -1){
				_fileHeaders.push(fh);
			}else{
				_fileHeaders.splice(index, 0, fh);
			}
		}
		public function getFileHeader(index:int = -1) : FileHeader {
			if (_fileHeaders == null ){
				return null;
			}
			if (_fileHeaders.length == 0){
				return null;
			}
			if (index < 0){
				index = _fileHeaders.length - 1;	
			}
			
			if (index >= _fileHeaders.length){
				return null;
			}
			return _fileHeaders[index];
		}
		public function removeFileHeader(index: int = -1) : void{
			
			if (_fileHeaders == null){
				return;
			}
			if (_fileHeaders.length == 0){
				return;
			}
			if (index < 0){
				_fileHeaders.pop();
			}else{
				_fileHeaders.splice(index, 1);
			}
		}
		public function addFileHeaderByFileEntry(fe:FileEntry): void{
			
			var fh:FileHeader = new FileHeader();
			fh.CloneFileHeader(fe.localHeader);
			if (_fileHeaders == null){
				_fileHeaders = new Array();
			}
			_fileHeaders.push (fh);
			
			//keep handle to add offset during the serialize
			fe.fh = fh;
		}
		public function parse(buf:ByteArray, encoding:String="utf-8"):void {
			var i:int;
			if (_fileHeaders != null){
				for (i = 0; i < _fileHeaders.length; i++){
					_fileHeaders[i].parse(buf, encoding);
				}
			}
			if (_signature != null){
				_signature.parse(buf);
			}
		}
		public function serialize(buf:ByteArray, encoding:String="utf-8"):void {
			
			
			var i:int;
			if (_fileHeaders != null){
				for (i = 0; i < _fileHeaders.length; i++){
					_fileHeaders[i].serialize(buf, encoding);
				}
			}
			if (_signature != null){
				_signature.serialize(buf);
			}
		}
		
	}
}