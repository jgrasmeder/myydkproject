package ydk.epub.data
{
	import flash.utils.ByteArray;

	public class Encryption extends XmlEpubDocument
	{
		public function Encryption(buf:ByteArray)
		{
			if (buf == null){
				init();
			}else{
				parseXml(buf);
			}
		}
		private static var _fileName:String = "META-INF/encryption.xml";
		
		public static function get fileName():String
		{
			return _fileName;
		}

		public static function set fileName(value:String):void
		{
			_fileName = value;
		}
		
		public function init (): void{
			_encryption = 
				<encryption	 xmlns ="urn:oasis:names:tc:opendocument:xmlns:container"
				 xmlns:enc="http://www.w3.org/2001/04/xmlenc#"
				 xmlns:ds="http://www.w3.org/2000/09/xmldsig#">
				</encryption>;
		} 
		private var _encryption:XML;// = 

		public function addKey (id:String, keyName:String, cipher:String, algorithm:String = "http://www.w3.org/2001/04/xmlenc#rsa-1_5"):void{
//			var enc:Namespace = _encryption.namespace("enc");
//			var ds:Namespace = _encryption.namespace("ds");
			//default xml namespace = enc;
			var node:XML =
				<enc:EncryptedKey Id={id} xmlns:enc="http://www.w3.org/2001/04/xmlenc#" xmlns:ds="http://www.w3.org/2000/09/xmldsig#" >
					<enc:EncryptionMethod Algorithm={algorithm}/>
						<ds:KeyInfo  >
					  		<ds:KeyName>{keyName}</ds:KeyName>
						</ds:KeyInfo>
						<enc:CipherData>
						  	<enc:CipherValue>{cipher}</enc:CipherValue>
						</enc:CipherData>
					</enc:EncryptedKey>;
			_encryption.appendChild(node);
			trace("addKey encyption="+_encryption.toXMLString());
		}
		
		public function addData (id:String, cipherReference:String, retrievalMethod:String, algorithm:String = "http://www.w3.org/2001/04/xmlenc#kw-aes128", type:String = "http://www.w3.org/2001/04/xmlenc#EncryptedKey"):void{
//			var enc:Namespace = _encryption.namespace("enc");
//			var ds:Namespace = _encryption.namespace("ds");
			var node:XML = 
				<enc:EncryptedData Id={id} xmlns:enc="http://www.w3.org/2001/04/xmlenc#" xmlns:ds="http://www.w3.org/2000/09/xmldsig#" >
					<enc:EncryptionMethod Algorithm={algorithm}/>
					<ds:KeyInfo>
						<ds:RetrievalMethod URI={retrievalMethod} Type={type}/>
					</ds:KeyInfo>
					<enc:CipherData>
						<enc:CipherReference URI={cipherReference}/>
					</enc:CipherData>
				</enc:EncryptedData>
			_encryption.appendChild(node);
			trace("addData encyption="+_encryption.toXMLString());
		}
		public function getData (id:String) : XML{
			var enc:Namespace = _encryption.namespace("enc");
			var ds:Namespace = _encryption.namespace("ds");
			return _encryption.enc::EncryptedData.(@Id == id)[0];
		}
		public override function parseXml (buf:ByteArray):void {
			_encryption = new XML(buf);
		}
		
		public override function serialize ():ByteArray {
			var buf:ByteArray = new ByteArray();
			buf.writeUTFBytes(XmlEpubDocument.xmlDecl);
			buf.writeUTFBytes(_encryption.toXMLString());
			return buf;
		}
		public override function toXMLString():String{
			return _encryption.toXMLString();
			
		}


	}
}