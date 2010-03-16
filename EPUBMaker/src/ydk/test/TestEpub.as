package ydk.test
{
	import flexunit.framework.TestSuite;
	
	import ydk.test.TestMetadata;
	
	public class TestEpub extends TestSuite
	{
		public function TestEpub(param:Object=null)
		{
			super(param);
		}
		
		public static function suite():TestSuite
		{
			var newTestSuite:TestSuite = new TestSuite();
			newTestSuite.addTestSuite(ydk.test.TestMetadata);
			return newTestSuite;
		}
	}
}