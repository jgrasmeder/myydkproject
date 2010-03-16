package ydk.test
{
	import flexunit.framework.TestCase;
	
	import ydk.epub.data.Encryption;
	
	public class TestEncryption extends TestCase
	{
		// please note that all test methods should start with 'test' and should be public
		
		// Reference declaration for class to test
		private var classToTestRef : ydk.epub.data.Encryption;
		
		public function TestEncryption(methodName:String=null)
		{
			//TODO: implement function
			super(methodName);
		}
		
		//This method will be called before every test function
		override public function setUp():void
		{
			//TODO: implement function
			super.setUp();
			classToTestRef = new Encryption(null);
		}
		
		//This method will be called after every test function
		override public function tearDown():void
		{
			//TODO: implement function
			super.tearDown();
		}
		
		public function testAddData():void
		{
			//classToTestRef = new Encryption(null);
			classToTestRef.addData("id", "reference", "method retrieve");
			var x:XML = classToTestRef.getData("id");
			trace ("x="+x);
		}
		
		public function testAddKey():void
		{
			//classToTestRef = new Encryption(null);
			classToTestRef.addKey("ek1", "key", "cipher");
			trace("addkey:"+classToTestRef);
		}
		
		public function testGetData():void
		{
			// Add your test logic here
			var data:XML = classToTestRef.getData("id");
			trace ("data="+data);
		}
	}
}