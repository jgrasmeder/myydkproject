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
@Table(name = "book_news_category", catalog = "ydk",
		uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class BookNewsCategory extends SubjectCategory implements Serializable {
	
	
	private List<BookNews> bookNewses = new ArrayList<BookNews>(0);

	public BookNewsCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param introduction
	 */
	public BookNewsCategory(String name, String introduction) {
		super(name, introduction);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param name
	 * @param introduction
	 */
	public BookNewsCategory(String name, String introduction, 
			List<BookNews> bookNewses) {
		super(name, introduction);
		this.bookNewses = bookNewses;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the bookNewss
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookNewsCategory", cascade = {CascadeType.REMOVE})
	public List<BookNews> getBookNewss() {
		return bookNewses;
	}
	public void setBookNewss(List<BookNews> bookNewses) {
		this.bookNewses = bookNewses;
	}
	
	@Transient
	public BookNewsCategory setToPlainObject(BookNewsCategory instance)
	{
		//Get self
		getName();
		//Eager get @ManytoOne, @OnetoOne
		
		
		//Null oneToMany;
		setBookNewss(null);

		return instance;
	}
	
	@Transient
	public BookNewsCategory setToPlainObjectQuick(BookNewsCategory instance)
	{
		//Get self
		getName();
		//Eager get @ManytoOne, @OnetoOne
		
		
		//Null oneToMany;
		setBookNewss(null);

		return instance;
	}
	
	
	
	
	

}
