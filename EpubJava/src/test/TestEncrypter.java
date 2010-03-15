package test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import com.ydk.epub.Encrypter;

public class TestEncrypter extends TestCase {
	private String key = "2134236e0faf0581d0665477c5b13e5c56220d20c469db41c18193f52df0aa31";
	private String str = "我们是中国人，我们爱自己的祖国！abcdef";
	public void testEncrypt() throws IOException {
		System.out.println("before:"+str);
		Encrypter enc = new Encrypter(key);
		String s = enc.encrypt(str);
		
		str = s;
		System.out.println("after:"+str);
		
		enc = new Encrypter(key);
		ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes());
		InputStream ins = enc.decrypt(in);
		BufferedReader fr = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
		 s = "";
		String line;
		while ((line =fr.readLine()) != null){
			s += line;
		}
		
		
		str = s;
		System.out.println("after D:"+str);
	}

	public void testDecrypt() throws IOException {
		System.out.println("before DE:"+str);
		Encrypter enc = new Encrypter(key);
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
