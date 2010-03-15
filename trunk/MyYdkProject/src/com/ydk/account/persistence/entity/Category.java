package com.ydk.account.persistence.entity;

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
@Table(name = "category", catalog = "ydk",
		uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Category implements java.io.Serializable {

	private Long id;
	private String name;
	private Library library;
	private List<Book> books = new ArrayList<Book>(0);

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public Category(String name, Library library) {
		this.name = name;
		this.library = library;
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
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "library")
	public Library getLibrary() {
		return this.library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public List<Book> getBooks() {
		return this.books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	@Transient
	public Category setToPlainObject(Category instance)
	{
		//Get Self
		getName();
		//Eager get @ManytoOne, @OnetoOne
		Category result = new Category();
		result.setId(getId());
		result.setName(getName());
		if (getLibrary() != null)
		{
			result.setLibrary(
					getLibrary().setToPlainObjectQuick(getLibrary()));
		}
		//Null oneToMany;
		result.setBooks(null);
		
		

		return result;
	}
	
	@Transient
	public Category setToPlainObjectQuick(Category instance)
	{
		
		//Get Self
		getName();
		//Eager get @ManytoOne, @OnetoOne
//		Library ref = new Library();
//		ref.setId(getLibrary().getId());
		Category result = new Category();
		result.setId(getId());
		result.setName(getName());
//		setLibrary(null);
//		//Null oneToMany;
		result.setBooks(null);

		return result;
	}
	
	

}
