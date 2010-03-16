package ydk.test
{
	import flash.events.*;
	import flash.net.FileFilter;
	import flash.net.FileReference;
	import flash.utils.ByteArray;
	
	import flexunit.framework.TestCase;
	
	import ydk.epub.data.Opf;
	
	public class TestOpf extends TestCase
	{
		// please note that all test methods should start with 'test' and should be public
		
		// Reference declaration for class to test
		private var classToTestRef : ydk.epub.data.Opf;
		
		public function TestOpf(methodName:String=null)
		{
			//TODO: implement function
			super(methodName);
		}
		
		//This method will be called before every test function
		override public function setUp():void
		{
			//TODO: implement function
			super.setUp();
		}
		
		//This method will be called after every test function
		override public function tearDown():void
		{
			//TODO: implement function
			super.tearDown();
		}
		private var  fr:FileReference;
		public function testOpf():void
		{
			var d:XML = <dt version="2.0" unique-identifier="PrimaryID" xmlns:opf="http://www.idpf.org/2007/opf"><dcd><efg>abcd</efg></dcd></dt>;
			trace("d="+d);
			trace("dt="+d.dcd.toXMLString());
			var ns:Namespace = d.namespace();
			trace("dcd="+d.ns::dcd[0].toXMLString());
			var t:XML = d.ns::dcd[0];
			trace("t="+t);
			trace("t="+t.toXMLString());
			//return;
			var opf:Opf = new Opf(null);
			opf.bookId = "123243434";
			opf.bookName = "Test book name";
			opf.author = "YDK editor";
			opf.bookFormat = "small size";
			opf.bookIsbn = "1111-111-1111-111";
			opf.bookLayout = "test layout";
			opf.bookPages = "20";
			opf.coverage ="coverage text";
			opf.creator = "YDK creator";
			opf.description = "ydk description";
			opf.originalPublishDate"2010.3.8";
			opf.title = "test book title";
			opf.translator = "some translator 1";
			opf.translator = "some translator 2";
			opf.paperCopyPrice = "20.00";
			opf.addSpineItem("idref - 1");
			opf.addSpineItem("idref - 2");
			opf.addGuideItem("guide 1", "guide1.xml");
			opf.addManifestItem("manifest 1", "href manifest1", "mediaType");
			trace("opf="+opf.toXMLString());
//			fr = new FileReference();
//			var fileFilter:FileFilter = new FileFilter("zip Files", "*.epub;*.zip");
//			
//			fr.addEventListener(Event.COMPLETE, onSaveComplete);
//			fr.addEventListener(ProgressEvent.PROGRESS, onLoadProgress);
//			fr.addEventListener(IOErrorEvent.IO_ERROR, onLoadError);
//			
//			var data:ByteArray = opf.serialize();
//			
//			fr.save(data, opf.opfName);
			
		}
		protected function onSaveComplete (e:Event) : void
		{
			trace ("Zip File saved as: "+fr.name);
		}
		protected function onLoadProgress (e:ProgressEvent) : void
		{
			var file:FileReference = FileReference(e.target);
			trace("onLoadProgress: name=" + file.name + " bytesLoaded=" + e.bytesLoaded + " bytesTotal=" + e.bytesTotal);
		}
		private function onLoadError(e:IOErrorEvent):void
		{
			trace ("loadfile error:"+e);
		}
		
	}
}