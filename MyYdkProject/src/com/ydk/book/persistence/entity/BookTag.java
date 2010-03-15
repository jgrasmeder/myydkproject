package com.ydk.book.persistence.entity;

// Generated Feb 6, 2010 2:13:01 PM by Hibernate Tools 3.2.4.GA

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.ydk.book.persistence.entity.Book;

/**
 * Category
 */
@Entity
@Table(name = "book_tag", catalog = "ydk",
		uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class BookTag implements java.io.Serializable {

	private Long id;
	private String name;
	private List<Book> books = new ArrayList<Book>(0);

	public BookTag() {
	}
	
	public BookTag(Long id) {
		this.id = id;
	}

	public BookTag(String name) {
		this.name = name;
	}


	/**
	 * @param id
	 * @param name
	 * @param books
	 */
	public BookTag(String name, List<Book> books) {
		super();
		this.name = name;
		this.books = books;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", unique = true, nullable = false)
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	
	@ManyToMany(
			fetch = FetchType.LAZY, 
			mappedBy = "bookTags",
			targetEntity=Book.class)
	public List<Book> getBooks() {
		return this.books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	@Transient
	public BookTag setToPlainObject(BookTag instance)
	{
		//Get self
		getName();
		
//		//Eager get @ManytoOne, @OnetoOne
//		setMeFirstBooks(null);
//		setMeFourthBooks(null);
//		setMeSecondBooks(null);
//		setMeThirdBooks(null);
		BookTag result = new BookTag();
		result.setId(getId());
		result.setName(getName());
		//Eager get @ManytoOne, @OnetoOne
		result.setBooks(null);
		
		return result;
	}
	
	@Transient
	public BookTag setToPlainObjectQuick(BookTag instance)
	{
		//Get self
		getName();
		
		BookTag result = new BookTag();
		result.setId(getId());
		result.setName(getName());
		//Eager get @ManytoOne, @OnetoOne
		result.setBooks(null);
				
		return result;
	}
	
	

}
