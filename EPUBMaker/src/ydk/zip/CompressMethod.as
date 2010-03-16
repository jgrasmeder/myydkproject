package ydk.zip
{
	public class CompressMethod
	{
		const static private var _method:Array = initMethod; 
		public function CompressMethod()
		{
		}
		private static function initMethod():Array{
			/*
			0 - The file is stored (no compression)
			1 - The file is Shrunk
			2 - The file is Reduced with compression factor 1
			3 - The file is Reduced with compression factor 2
			4 - The file is Reduced with compression factor 3
			5 - The file is Reduced with compression factor 4
			6 - The file is Imploded
			7 - Reserved for Tokenizing compression algorithm
			8 - The file is Deflated
			9 - Enhanced Deflating using Deflate64(tm)
			10 - PKWARE Data Compression Library Imploding (old IBM TERSE)
			11 - Reserved by PKWARE
			12 - File is compressed using BZIP2 algorithm
			13 - Reserved by PKWARE
			14 - LZMA (EFS)
			15 - Reserved by PKWARE
			16 - Reserved by PKWARE
			17 - Reserved by PKWARE
			18 - File is compressed using IBM TERSE (new)
			19 - IBM LZ77 z Architecture (PFS)
			97 - WavPack compressed data
			98 - PPMd version I, Rev 1
			*/
			_method = new Array();
			_method["STORED"] = 1;
			_method["SHRUNK"] = 2;
			_method["FACTOR_1"] = 3;
			_method["FACTOR_2"] = 4;
			_method["FACTOR_3"] = 5;
			_method["FACTOR_4"] = 6;
			_method["IMPLODED"] = 7;
			_method["DEFLATED"] = 8;
			_method["DEFLATE64"] = 9;
			_method["PKWARE"] = 10;
			_method["PKWARE_RESERVED"] = 11;
			_method["BZIP2"] = 12;
			_method["PKWARE_RESERVED_1"] = 13;
			_method["LZMA"] = 14;
			_method["PKWARE_RESERVED_2"] = 15;
			_method["PKWARE_RESERVED_3"] = 16;
			_method["PKWARE_RESERVED_4"] = 17;
			_method["IBM_TERSE"] = 18;
			_method["IBM_PFS"] = 19;
			_method["WavPack"] = 97;
			_method["PPMd"] = 98;
		}
	}
	public function getMethod(methodName:String) : int{
		if (_method[methodName] != null){
			return _method[methodName];
		}else{
			return -1;
		}
	}
	public function getMethodName(method:int) : String{
		var name:String = "unknown Method:"+method;
		for each (var n in _method ) {
			if (_method[n] == method) {
				name = n;
				break;
			}
		}
		return name;
	}
}