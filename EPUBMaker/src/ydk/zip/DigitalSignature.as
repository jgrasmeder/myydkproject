package ydk.zip
{
	import flash.utils.ByteArray;

	public class DigitalSignature
	{
		static public const  CENTRAL_DIRECTORY_HEADER_SIGNATURE:uint = 0x05054b50;
		public function DigitalSignature()
		{
		}
		private var _headerSignature:uint = CENTRAL_DIRECTORY_HEADER_SIGNATURE;
		private var _dataLength:int;
		private var _data:ByteArray;
		
		public function get signature () : uint {
			return _headerSignature;
		}
		public function set signature (signature:uint) : void {
			_headerSignature = signature;
		}
		public function get data (): ByteArray {
			return _data;
		}
		public function set data (data:ByteArray) : void {
			_data = data;
			_dataLength = _data.length;
		}
		
		public function parse (buf:ByteArray) : void {
			_headerSignature = buf.readUnsignedInt();
			_dataLength = buf.readShort();
			if (_dataLength > 0){
				_data = new ByteArray();
				buf.readBytes(_data, 0, _dataLength);
			}
		}
		
		public function serialize(buf:ByteArray) : void {
			buf.writeUnsignedInt(_headerSignature);
			buf.writeShort(_dataLength);
			if (_dataLength > 0 ){
				buf.writeBytes(_data);	
			}
		}
		
	}
}