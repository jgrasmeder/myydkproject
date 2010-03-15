/**
 * 
 */
package com.ydk.account.core;


/**
 * @author y140zhan
 *
 */
public class AccountDefines {
	
	//Account Role defitions
	public static final String ACCOUT_ROLE_GUEST = "_GUEST_";
	public static final String ACCOUT_ROLE_VIP = "_VIP_";
	public static final String ACCOUT_ROLE_ADMIN = "_ADMIN_";
	
	public static final int MASK_ACCOUT_ROLE_GUEST = 0;
	public static final int MASK_ACCOUT_ROLE_VIP = 1;
	public static final int MASK_ACCOUT_ROLE_ADMIN = 2;
	
	
	
	public static final String ACCOUT_ROLE_PARTNER_ADMIN = "_PARTNER_ADMIN";
	public static final String ACCOUT_ROLE_READER_ADMIN = "_READER_ADMIN";
	public static final String ACCOUT_ROLE_BOOK_ADMIN = "_BOOK_ADMIN";
	public static final String ACCOUT_ROLE_SUBJECT_ADMIN = "_SUBJECT_ADMIN";
	public static final String ACCOUT_ROLE_BUSINESS_ADMIN = "_BUSINESS_ADMIN";
	public static final String ACCOUT_ROLE_SUPER_ADMIN = "_SUPER_ADMIN";
	
	public static final int MASK_ACCOUT_ROLE_PARTNER_ADMIN = 1;
	public static final int MASK_ACCOUT_ROLE_READER_ADMIN = 2;
	public static final int MASK_ACCOUT_ROLE_BOOK_ADMIN = 4;
	public static final int MASK_ACCOUT_ROLE_SUBJECT_ADMIN = 8;
	public static final int MASK_ACCOUT_ROLE_BUSINESS_ADMIN = 16;
	public static final int MASK_ACCOUT_ROLE_SUPER_ADMIN = 127;

	
	//Account Status defitions
	public static final String ACCOUT_STATUS_INACTIVED = "_INACTIVED_";
	public static final String ACCOUT_STATUS_ACTIVED = "_ACTIVED_";
	public static final String ACCOUT_STATUS_EXPIRED = "_EXPIRED_";
	

	
	


}
