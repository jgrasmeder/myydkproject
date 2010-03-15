/**
 * 
 */
package com.ydk.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.ydk.account.persistence.entity.Key;
import com.ydk.account.persistence.entity.UploadedFile;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.utility.YdkRandomString;
import com.ydk.book.persistence.interfaces.BookDbMngr;

/**
 * @author y140zhan
 *
 */
public class KeyManager {
	
	private final static Logger logger = Logger.getLogger(KeyManager.class.getName());

	
	protected final AccountDbMngr accountDbMngr;
	protected final BookDbMngr bookDbMngr;
	
	@Autowired
	public KeyManager(AccountDbMngr accountDbMngr, 
			BookDbMngr bookDbMngr) {
		this.accountDbMngr = accountDbMngr;
		this.bookDbMngr = bookDbMngr;
	}
	
	
	
	public Key generateStoredKeyPair(Date date)
	{
		Key keyPair = generateKeyPair(date);
		//TODO here we need to do the generaton of key;
		accountDbMngr.storeKey(keyPair);
		return keyPair;
	}
	
	public static Key generateKeyPair(Date date)
	{
		Key keyPair = new Key();
		//TODO here we need to do the generaton of key;
		keyPair.setPrivateKey(YdkRandomString.getRandomString(8));
		keyPair.setPublicKey(YdkRandomString.getRandomString(8));
		
		keyPair.setValidFrom(date);
		keyPair.setExpired(false);
		return keyPair;
	}
	
	public static Long generateMagicNumber()
	{
		Random random = new Random();
		return random.nextLong();
	}
	
	
	
	


	

}
