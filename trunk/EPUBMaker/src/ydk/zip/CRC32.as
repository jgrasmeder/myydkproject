package ydk.zip
{
	import flash.utils.ByteArray;
	
	/**
	 * CRC33校验类。
	 * @private
	 */
	public class CRC32 {
		
		/** The fast CRC table. Computed once when the CRC32 class is loaded. */
		private static var crcTable:Array = makeCrcTable();
		
		public static function getCRC32(data:ByteArray, start:uint = 0, len:uint = 0):uint {
			if (start >= data.length) { start = data.length; }
			if (len == 0) { len = data.length - start; }
			if (len + start > data.length) { len = data.length - start; }
			var i:uint;
			var c:uint = 0xffffffff;
			for (i = start; i < len; i++) {
				c = uint(crcTable[(c ^ data[i]) & 0xff]) ^ (c >>> 8);
			}
			return (c ^ 0xffffffff);
		}
		/**
		 * 生成CRC表
		 * @return CRC表
		 */
		private static function makeCrcTable():Array {
			var crcTable:Array = [];
			for (var n:int = 0; n < 256; n++) {
				var c:uint = n;
				for (var k:int = 8; --k >= 0; ) {
					if((c & 1) != 0) c = 0xedb88320 ^ (c >>> 1);
					else c = c >>> 1;
				}
				crcTable[n] = c;
			}
			return crcTable;
		}
	}
}