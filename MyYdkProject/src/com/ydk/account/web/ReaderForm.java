/**
 * 
 */
package com.ydk.account.web;





import com.ydk.account.persistence.entity.Address;
import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.ReaderPortrait;
import com.ydk.book.persistence.entity.BookReviewCategory;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;


/**
 * @author y140zhan
 *
 */
public class ReaderForm {
	

	private Reader instance;

	private Long[] keys ;
	
	/**
	 * 
	 */
	public ReaderForm() {
		super();
		instance = new Reader();
//		instance.setAddress(new Address());
		instance.setPartner(new Partner());
		// TODO Auto-generated constructor stub
	}
	
	public ReaderForm(Reader instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public ReaderForm(Reader instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public Reader getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Reader instance) {
		this.instance = instance;
	}


	/**
	 * @return the keys
	 */
	public Long[] getKeys() {
		return keys;
	}

	/**
	 * @param keys the keys to set
	 */
	public void setKeys(Long[] keys) {
		this.keys = keys;
	}
	
	public void prepareForModify(Reader instance)
	{
		instance.setNickName(this.instance.getNickName());
		instance.setEmail(this.instance.getEmail());
//		instance.setTitle(this.instance.getTitle());
//		instance.setContent(this.instance.getContent());
		instance.setAddress(this.instance.getAddress());
		instance.setRealName(this.instance.getRealName());
		instance.setIsActived(this.instance.getIsActived());
		instance.setMobilePhone(this.instance.getMobilePhone());
		
	}


	
	
	
	
	

}
