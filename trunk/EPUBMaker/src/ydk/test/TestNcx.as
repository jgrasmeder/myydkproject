package ydk.test
{
	import flexunit.framework.TestCase;
	
	import ydk.epub.data.Ncx;
	
	public class TestNcx extends TestCase
	{
		// please note that all test methods should start with 'test' and should be public
		
		// Reference declaration for class to test
		private var classToTestRef : ydk.epub.data.Ncx;
		
		public function TestNcx(methodName:String=null)
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
		
		public function testAddNavPoint():void
		{
			// Add your test logic here
			var _ncx:Ncx = new Ncx(null);
			_ncx.docAuthor = "doc author";
			_ncx.docTitle = "doc title";
			_ncx.dtbUid = "111111";
			
			_ncx.addNavPoint(null, "chapter", "0001", "1", "chapter 1 I love you", "chapter1.xhmtl");
			trace("ncx="+_ncx.toXMLString());
		}
		
		
	}
}