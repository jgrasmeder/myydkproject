package ydk.epub.maker
{
	import com.hurlant.crypto.*;
	import com.hurlant.crypto.symmetric.ICipher;
	import com.hurlant.crypto.symmetric.IPad;
	import com.hurlant.crypto.symmetric.PKCS5;
	import com.hurlant.util.Base64;
	import com.hurlant.util.Hex;
	
	import flash.utils.ByteArray;
	
	import ydk.epub.data.Container;
	import ydk.epub.data.Encryption;
	import ydk.epub.data.Ncx;
	import ydk.epub.data.Opf;
	import ydk.zip.FileEntry;
	import ydk.zip.ZipFile;

	public class EpubBook
	{
		public function EpubBook(buf:ByteArray)
		{
			if (buf == null){
				//we are creating our own book
				_zip = new ZipFile("ydk_epub");
				_zip.addTextFile(MIMETYPE, "mimetype", "ansi", false);
				_container = new Container(null);
				_opf = new Opf(null);
				_ncx = new Ncx(null);
				_encryption = new Encryption(null);
				
			}else{
				//we are reading;
				parseEbpub(buf);
			}
			MEDIATYPE = new Array();
			MEDIATYPE["jpg"] = "image/jpeg";
			MEDIATYPE["png"] = "image/png";
			MEDIATYPE["gif"] = "image/png";		
			
			
		}
		private var  MEDIATYPE:Array;
		public function getMediaType(suffix:String):String {
			return MEDIATYPE[suffix.toLowerCase()];
		}
		private var _mode:ICipher; 
		private var _container:Container;
		private var _opf:Opf;
		private var _ncx:Ncx;
		private var _encryption:Encryption;
		private var _oebpsPrefix:String = "OEBPS";
		public static const  MIMETYPE:String = "application/epub+zip";
		public static const  NCX_CLASS:Array = ["chapter", "section", "subsection","sub-subsection"];
		public static const  idPrefix:String = "ydk_lvl_";
		private var _zip:ZipFile = new ZipFile("epub"); 
		
		private var _encryptKey:String;
//		private var _signature:String;
//		private var _rights:String;
//		private var _contents:Array = new Array();
//		private var _binaryFiles:Array = new Array();
//		private var _toc:XML = <Toc></Toc>;
		
		public function get opf() : Opf {
			return _opf;
		}
		public function parseEbpub(buf:ByteArray):void{
			
			_zip.parse(buf);
			var fe:FileEntry =  _zip.getFileEntry("mimetype");
			if (fe == null){
				throw Error("not a valid epub file: no mimetype file found");
				return;
			}
			if (fe.data == null || fe.data.length == 0){
				throw Error("not a valid epub file: mimetype file is null or empty");
				return;
			}
//			if (fe.method == 8){
//				fe.data.deflate();
//			}
			var mimetype:String = fe.data.readMultiByte(fe.data.bytesAvailable, "ansi");
			trace("EpubBook | mimetype="+mimetype);
			if (mimetype != MIMETYPE){
				throw Error("not a valid epub file: mimetype is invalid:"+mimetype);
			}
			fe = _zip.getFileEntry(Container.fileName);
			if (fe == null){
				throw Error("not a valid epub file: container file not found:"+Container.fileName);
				return;
			}
//			if (fe.method == 8){
//				fe.data.deflate();
//			}
			_container = new Container(fe.data);
			var roots:XMLList = _container.rootFile;
			var opfFile:String = null;
			for (var i:int = 0; i < roots.length(); i++){
				if (roots[i].@["media-type"] == "application/oebps-package+xml"){
					opfFile = roots[i].@["full-path"];
					break;
				}
			}
			if (opfFile == null){
				throw Error("not a valid epub file: opf file not found" );
				return;
			}
			
			var idx:int = opfFile.indexOf("/");
			if (idx > 0){
				_oebpsPrefix = opfFile.substr(0,idx);
			}
			fe = _zip.getFileEntry(opfFile);
			
			_opf = new Opf(fe.data);
			
			var ncxFile:String;
			
			var items:XMLList = _opf.getManifestItem("ncx");
			if (items[0] == null){
				throw Error("not a valid epub file: ncx file not presented" );
				return;
			}
			if (items[0].@["media-type"] != "application/x-dtbncx+xml"){
				throw Error("not a valid epub file: ncx file media-type error:"+ items[0].@["media-type"]);
				return;
			}
			ncxFile = items[0].@href;
			if (ncxFile == null || ncxFile == ""){
				throw Error("not a valid epub file: ncx file media-type error:"+ items[0].@["media-type"]);
				return;
			}
			
			if ( ncxFile.indexOf("/") < 0){
				ncxFile = _oebpsPrefix + "/"+ncxFile;
				trace("ncxFile = "+ncxFile);
			}
			
			fe = _zip.getFileEntry(ncxFile);
			
			if (fe == null){
				throw Error("not a valid epub file: ncx file not found :");
				return;
			}
			
			_ncx = new Ncx(fe.data);
			
			fe = _zip.getFileEntry(Encryption.fileName);
			if (fe == null){
				trace ("this file has not encryption");
			}else{
				_encryption = new Encryption(fe.data);
			}
		}
		
		private function verifyManifest():Boolean{
			if (_opf == null){
				return false;
			}
			//_opf.getManifestItem();
			return true;
		}
		
		
		public function get encryptKey ():String{
			return _encryptKey;
		}
		public function setEncryptKey(key:String):void{
			// tis is the rsa AES key.
			_encryptKey = key;
			var pad:IPad = new PKCS5();
			var kdata:ByteArray = Hex.toArray(key);//Base64.decodeToByteArray(key); 
			_mode = Crypto.getCipher("aes", kdata, pad);
			trace (_mode);
			pad.setBlockSize(_mode.getBlockSize());
			
			_encryption.addKey("EK", "YDK KEY", "");
			
		}
		
		
		public function addChapter(parent:XML, chapterId:String, chapterName:String, content:String, fileName:String,  level:int, order:int, encrypted:Boolean = false, mediaType:String = "application/xhtml+xml" ):XML {
			//return the node for this chapter, parent = null is the 1st level chapter;
			if (encrypted){
				content = encryptChapter(content);
				_encryption.addData(chapterId, fileName, "http://www.yueduke.com/getbooklicense");
			}
			_zip.addTextFile(content, _oebpsPrefix+"/"+fileName, "utf-8");
			
			_opf.addManifestItem(chapterId, fileName, mediaType);
			_opf.addSpineItem(chapterId);
			
			return _ncx.addNavPoint(parent, chapterName, fileName, chapterId, NCX_CLASS[level], order+"");  
		}
		public function addCover(content:String, imageName:String, image:ByteArray):void {
			//return the node for this chapter, parent = null is the 1st level chapter;
			
			_zip.addTextFile(content, _oebpsPrefix+"/cover.xhtml", "utf-8");
			
			_opf.addManifestItem("cover", "cover.xhtml", "application/xhtml+xml");
			_opf.addSpineItem("cover");
			
//			var fileName:String = _oebpsPrefix + "/"+imageName;
//			_opf.addManifestItem("cover_image", fileName, "image/" + imageName.substring(imageName.lastIndexOf(".")));
//			
//			_zip.addFile(image, fileName);
		}
		public function getChapter(id:String, src:String):String {
			var fn:String = _oebpsPrefix + "/" + src;
			var fe:FileEntry = _zip.getFileEntry(fn);
			var data:ByteArray = fe.data;
			var content:String = data.readMultiByte(data.length, "utf-8");
			if (_encryption.getData(id) != null){
				
				content = dencryptChapter(content);				
			}
			return content;
		}
		private function encryptChapter(content:String) : String{
			if (_mode == null){
				return content;
			}else{
				var buf:ByteArray = new ByteArray();
				buf.writeUTFBytes(content);
				_mode.encrypt(buf);
				return Base64.encodeByteArray(buf);
			}
		}
		private function dencryptChapter(content:String) : String{
			if (_mode == null){
				return content;
			}else{
				var buf:ByteArray = Base64.decodeToByteArray(content);
				_mode.decrypt(buf);
				content = buf.readMultiByte(buf.bytesAvailable, "utf-8");
				return content;
			}
			
			
		}
		public function updateOpfInfo(start:XMLList, level:int, order:int):void{
			//go over the ncx, update following information:
			// 1. class, display order of ncx
			// 2. update opf manifest reference
			// 3. update opf guide
			// 4. update opf spine.
			if (start == null){
				start = _ncx.navMap.children();
			}
			level = level > 3? 3 : level;
			for (var i:int = 0; i < start.length();i++){
				//start[i].@["class"] = NCX_CLASS[level];
				start[i].@playOrder = ++order;
				var id:String = idPrefix+level+order;
				//start[i].@id = id;
				//_opf.addManifestItem(id, start[i].content.@src, "text/x-oeb1-document");
				//_opf.addSpineItem(id);
			//	_opf.addGuideItem(start[i].navLabel.text, start[i].content.@src);
				var children:XMLList = start[i].navPoint;
				updateOpfInfo(children, ++level, order);
			}
		}
		
		public function addBinaryFile(id:String, fileName:String, data:ByteArray, mediaType:String):void{
			//the fileName is unter OEBPS directory and in opf mainfest.
			_opf.addManifestItem(id, fileName, mediaType);
			fileName = _oebpsPrefix + "/" +fileName;
			_zip.addFile(data, fileName);
		}
		
		public function complete():void{
			//updateOpfInfo(null, 0, 0);
			_opf.addManifestNcx(_ncx.fileName);
			_zip.addFile(_container.serialize(), Container.fileName);
			_zip.addFile(_opf.serialize(), Opf.fileName);
			_zip.addFile(_encryption.serialize(), Encryption.fileName);
			_zip.addFile(_ncx.serialize(), _oebpsPrefix + "/" +_ncx.fileName);
		}
		public function serialize(data:ByteArray):void {
			_zip.serialize(data);
		}
	}
}