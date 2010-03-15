/**
 * 
 */
package com.ydk.account.web;





import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;


/**
 * @author y140zhan
 *
 */
public class InterviewForm {
	

	private Interview instance;

	private Long[] keys ;
	
	/**
	 * 
	 */
	public InterviewForm() {
		super();
		instance = new Interview();
		instance.setInterviewCategory(new InterviewCategory());
		// TODO Auto-generated constructor stub
	}
	
	public InterviewForm(Interview instance) {
		super();
		this.instance = instance;

	}
	
    

	/**
	 * @param instance
	 * @param keys
	 */
	public InterviewForm(Interview instance, Long[] keys) {
		super();
		this.instance = instance;
		this.keys = keys;
	}
	

	/**
	 * @return the instance
	 */
	public Interview getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	public void setInstance(Interview instance) {
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
	
	public void prepareForModify(Interview instance)
	{
		instance.setTitle(this.instance.getTitle());
//		instance.setContent(this.instance.getContent());
		instance.setEditor(this.instance.getEditor());
		instance.setInterviewer(this.instance.getInterviewer());
		instance.setIntroduction(this.instance.getIntroduction());
		instance.setLocation(this.instance.getLocation());
		instance.setReporter(this.instance.getReporter());
		instance.setIsQa(this.instance.getIsQa());
		
	}


	
	
	
	
	

}
