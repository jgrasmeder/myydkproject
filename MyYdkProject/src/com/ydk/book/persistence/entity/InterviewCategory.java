/**
 * 
 */
package com.ydk.book.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * @author y140zhan
 *
 */
@Entity
@Table(name = "interview_category", catalog = "ydk",
		uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class InterviewCategory extends SubjectCategory implements Serializable {
	
	
	private List<Interview> interviews = new ArrayList<Interview>(0);

	public InterviewCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param introduction
	 */
	public InterviewCategory(String name, String introduction) {
		super(name, introduction);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param name
	 * @param introduction
	 */
	public InterviewCategory(String name, String introduction, 
			List<Interview> interviews) {
		super(name, introduction);
		this.interviews = interviews;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the interviewCategories
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "interviewCategory", cascade = {CascadeType.REMOVE})
	public List<Interview> getInterviews() {
		return interviews;
	}
	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}
	
	
	@Transient
	public InterviewCategory setToPlainObject(InterviewCategory instance)
	{
		//Get self
		getName();
		//Eager get @ManytoOne, @OnetoOne
		
		
		//Null oneToMany;
		setInterviews(null);

		return instance;
	}
	
	@Transient
	public InterviewCategory setToPlainObjectQuick(InterviewCategory instance)
	{
		//Get self
		getName();
		//Eager get @ManytoOne, @OnetoOne
		
		
		//Null oneToMany;
		setInterviews(null);

		return instance;
	}
	
	
	
	

}
