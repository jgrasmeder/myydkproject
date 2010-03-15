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
@Table(name = "book_review_category", catalog = "ydk",
		uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class BookReviewCategory extends SubjectCategory implements Serializable {
	
	
	private List<BookReview> bookReviews = new ArrayList<BookReview>(0);

	public BookReviewCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param introduction
	 */
	public BookReviewCategory(String name, String introduction) {
		super(name, introduction);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param name
	 * @param introduction
	 */
	public BookReviewCategory(String name, String introduction, 
			List<BookReview> bookReviews) {
		super(name, introduction);
		this.bookReviews = bookReviews;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the bookNewss
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookReviewCategory", cascade = {CascadeType.REMOVE})
	public List<BookReview> getBookReviews() {
		return bookReviews;
	}
	public void setBookReviews(List<BookReview> bookReviews) {
		this.bookReviews = bookReviews;
	}
	
	
	
	@Transient
	public BookReviewCategory setToPlainObject(BookReviewCategory instance)
	{
		//Get self
		getName();
		//Eager get @ManytoOne, @OnetoOne
		
		
		//Null oneToMany;
		setBookReviews(null);

		return instance;
	}
	
	@Transient
	public BookReviewCategory setToPlainObjectQuick(BookReviewCategory instance)
	{
		//Get self
		getName();
		//Eager get @ManytoOne, @OnetoOne
		
		
		//Null oneToMany;
		setBookReviews(null);

		return instance;
	}
	
	
	

}
