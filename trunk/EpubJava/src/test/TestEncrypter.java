package test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import com.ydk.epub.Encrypter;

public class TestEncrypter extends TestCase {
	private String key128 = "0742b1bc86d38469d7dadbb0769e874a";
	private String key256 = "e332d5f267e477d5a6114dcb05117e852c5875a3ceea0dfcdd619008d752ea5b";
//	private String iv = "fe88a84d19ceb334e4167a479a833cdf";
	private String iv = "d8b55c911af3dbe316b59e52e702ae7e";
	private String ivkey256 = "d8b55c911af3dbe316b59e52e702ae7ee332d5f267e477d5a6114dcb05117e852c5875a3ceea0dfcdd619008d752ea5b";
	private String str = "我们是中国人，我们爱自己的祖国！abcdef我们是中国人，我们爱自己的祖国！abcdef我们是中国人，我们爱自己的祖国！abcdef";
	public void testEncrypt() throws IOException {
		System.out.println("before:"+str);
		Encrypter enc = new Encrypter(key256, iv);
		String s = enc.encrypt(str);
		
		str = s;
		System.out.println("===>"+str);
		
		enc = new Encrypter(ivkey256);
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
		InputStream ins = enc.decrypt(in);
		BufferedReader fr = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
		 s = "";
		String line;
		while ((line =fr.readLine()) != null){
			s += line;
		}
		
		
		str = s;
		System.out.println("====>"+str);
	}

	public void testDecrypt() throws IOException {
		System.out.println("before DE:"+str);
		Encrypter enc = new Encrypter(key128, iv);
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
		InputStream ins = enc.decrypt(in);
		BufferedReader fr = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
		String s = "";
		String line;
		while ((line =fr.readLine()) != null){
			s += line;
		}
		
		
		str = s;
		System.out.println("after DE:"+str);
	}

}
