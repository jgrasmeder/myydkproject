package com.ydk.epub;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Encrypter {
	//given key is HEX 
	//data is utf-8 BASE64 encoded;
	//then it is AES 256 encrypted;
	private Cipher ecipher;
	private Cipher dcipher;
	private byte[] _key = new byte[16]; //256bits AES key is not support by java by default 
	//TODO find a solution;
	private SecretKeySpec aesKey;
	private String _hexKey;
	
	public Encrypter(String key) {
		init(key);
	}

	public void init(String key){
		if (_hexKey != null){
			if (_hexKey.compareTo(key) == 0){
				//the key is same;
				return;
			}else{
				_hexKey = key;
			}
		}else{
			_hexKey = key;
		}
		if (key != null && key.length() == 32){
			for (int i = 0; i < 16; i++){
				_key[i] = (byte)Integer.parseInt(key.substring(i*2, i*2+2), 16);
			}
		}
		aesKey = new SecretKeySpec(_key, "AES");
		// Create an 8-byte initialization vector
		byte[] iv = new byte[]
		{
			0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f
			
		};
		
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
		try
		{
			ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			
			// CBC requires an initialization vector
			ecipher.init(Cipher.ENCRYPT_MODE, aesKey, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, aesKey, paramSpec);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		
	}
	private byte[] buf = new byte[1024];
	public String encrypt(String utf8String)
	{
		try
		{
			//1. get bytes 
			byte[] ba = utf8String.getBytes("UTF-8");
			//2. construct inputstream
			ByteArrayInputStream baIn = new ByteArrayInputStream(ba);
			//3. construct outputstream
			ByteArrayOutputStream baOut = new ByteArrayOutputStream();
			//4. Bytes written to out will be encrypted
			OutputStream out = new CipherOutputStream(baOut, ecipher);
			
			//5. Read in the cleartext bytes and write to out to encrypt
			int numRead = 0;
			while ((numRead = baIn.read(buf)) >= 0)
			{
				out.write(buf, 0, numRead);
			}
			out.close();
			//6. get bytes base64 encode;
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(baOut.toByteArray());
			
		}
		catch (java.io.IOException e)
		{
			return null;
		}
	}
	
	public InputStream decrypt(InputStream in)
	{
		try
		{
			//1. read utf-8 base-64;
			BufferedReader fr = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String strBase64 = "";
			String line;
			while ((line =fr.readLine()) != null){
				strBase64 += line;
			}
			//2. decode to byte
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] decodedBytes = decoder.decodeBuffer(strBase64);
			//3. construct ByteArray
			//4. construct ByteArrayInputStream
			ByteArrayInputStream byteInput = new ByteArrayInputStream(decodedBytes);
			//5. decrypted it to ByteArrayOutputStream;
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			CipherInputStream cin = new CipherInputStream(byteInput, dcipher);
			int numRead = 0;
			while ((numRead = cin.read(buf)) >= 0)
			{
				byteOutput.write(buf, 0, numRead);
			}
			byteOutput.close();
			//6. construct byte array
			byteInput = new ByteArrayInputStream(byteOutput.toByteArray());
			//7. construct ByteArrayInputStream. return;
			// Bytes read from in will be decrypted
			
			return byteInput;
		}
		catch (java.io.IOException e)
		{
			return null;
		}
	}
	
}
