package ydk.test
{
	import flexunit.framework.TestCase;
	
	import ydk.epub.data.MetaData;
	
	public class TestMetadata extends TestCase
	{
		// please note that all test methods should start with 'test' and should be public
		
		// Reference declaration for class to test
		private var classToTestRef : ydk.epub.data.MetaData;
		private var _meta:MetaData;
		public function TestMetadata(methodName:String=null)
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
		
		public function testMetaData():void
		{
			_meta = new MetaData(null);
			
		}
		public function testSetMetatdata():void
		{
			_meta = new MetaData(null);
			//_meta.setMetadata("title", "book 1");
			_meta.setMetadata("author", "author 1");
			_meta.setMetadata("author", "author 2");
			
			_meta.setDcMetadata("title", "settitle 1");
			_meta.setDcMetadata("title", "settitle 2");
			_meta.setDcMetadata("title", "settitle 3");
			_meta.setDcMetadata("what", "this is my what");
			_meta.setDcMetadataByAttribute("date", "2010.3.8", "opf","event","ops-publication");
			
			var titles:XMLList = _meta.getDcMetadata("title");
			for (var title:String in titles){
				trace("title["+title+"]="+titles[title]);
			}
			var dates:XMLList = _meta.getDcMetadataByAttribute("date", "opf","event","ops-publication");
			for (var j:String in dates){
				trace("dates["+j+"]="+dates[j]);
			}
			trace(_meta.getDcMetadata("what"));
			trace(_meta.getDcMetadata("author"));
			trace(_meta.metadata);
			trace(_meta.getMetadata("title"));
			trace(_meta.getMetadata("author"));
			trace(_meta.getMetadata("creator"));
			var nodes:XMLList = _meta.metadata.children();
			for (var i:int = 0; i < nodes.length();i++){
				trace("["+i+"]"+nodes[i].toXMLString());
			}
		}
		
		public function testGetMetadata():void
		{
						
			//trace(_meta.metadata);
			var xml:XML = new XML();
			xml = new XML (<title><some>1234</some></title>);
			xml.addNamespace("cd");
			xml.some.prefix="cd";
			trace("dc="+xml);
		}
		
		
		
		public function testGet_metadata():void
		{
//			trace(_meta.getMetadata("title"));
//			trace(_meta.getMetadata("author"));
			var xml:XML = <title>this is book</title>;
			xml.docTypeDecl = "Test type";
			xml.xmlDecl = "version=\"1.0\" encoding=\"UTF-8\"";
			trace(xml.toXMLString());
			
		}
		
		
	}
}