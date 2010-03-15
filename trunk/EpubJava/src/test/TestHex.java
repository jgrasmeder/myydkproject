package test;

import junit.framework.TestCase;

public class TestHex extends TestCase {
	public void testHex(){
		String s = "3a4b";
		int x = Integer.parseInt(s.substring(0, 2), 16);
		System.out.println(x);
		System.out.println(Integer.toHexString(x));
	}
}
