package ydk.test
{
	import flexunit.framework.TestCase;
	
	import ydk.epub.maker.EpubBook;
	
	public class TestEpubBook extends TestCase
	{
		// please note that all test methods should start with 'test' and should be public
		
		// Reference declaration for class to test
		private var classToTestRef : ydk.epub.maker.EpubBook;
		
		public function TestEpubBook(methodName:String=null)
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
		
		public function testEpubBook():void
		{
			// Add your test logic here
			fail("Test method Not yet implemented");
		}
	}
}