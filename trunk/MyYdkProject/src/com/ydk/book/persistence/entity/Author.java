/**
 * 
 */
package com.ydk.book.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ydk.account.persistence.entity.Person;

/**
 * @author y140zhan
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "author", catalog = "ydk")
public class Author extends Person implements Serializable {
	
	private Long id;
	private List<Book> meFirstBooks = new ArrayList<Book>(0);
	private List<Book> meSecondBooks = new ArrayList<Book>(0);
	private List<Book> meThirdBooks = new ArrayList<Book>(0);
	private List<Book> meFourthBooks = new ArrayList<Book>(0);
	
	public Author()
	{
		super();
	}
	
	public Author(String name)
	{
		this.name = name;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	@JoinTable(
            name="book_first_author",
            joinColumns = @JoinColumn( name="book_pk"),
            inverseJoinColumns = @JoinColumn( name="author_pk")
    )
	public List<Book> getMeFirstBooks() {
		return this.meFirstBooks;
	}
	public void setMeFirstBooks(List<Book> meFirstBooks) {
		this.meFirstBooks = meFirstBooks;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author1")
	@JoinTable(
            name="book_second_author",
            joinColumns = @JoinColumn( name="book_pk"),
            inverseJoinColumns = @JoinColumn( name="author_pk")
    )
	public List<Book> getMeSecondBooks() {
		return this.meSecondBooks;
	}
	public void setMeSecondBooks(List<Book> meSecondBooks) {
		this.meSecondBooks = meSecondBooks;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author2")
	@JoinTable(
            name="book_third_author",
            joinColumns = @JoinColumn( name="book_pk"),
            inverseJoinColumns = @JoinColumn( name="author_pk")
    )
	public List<Book> getMeThirdBooks() {
		return this.meThirdBooks;
	}
	public void setMeThirdBooks(List<Book> meThirdBooks) {
		this.meThirdBooks = meThirdBooks;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author3")
	@JoinTable(
            name="book_fourth_author",
            joinColumns = @JoinColumn( name="book_pk"),
            inverseJoinColumns = @JoinColumn( name="author_pk")
    )
	public List<Book> getMeFourthBooks() {
		return this.meFourthBooks;
	}
	public void setMeFourthBooks(List<Book> meFourthBooks) {
		this.meFourthBooks = meFourthBooks;
	}
	
	
	@Transient
	public Author setToPlainObject(Author instance)
	{
		//Get self
		instance.getName();
		//Eager get @ManytoOne, @OnetoOne
		Author result = new Author();
		result.setId(instance.getId());
		result.setName(instance.getName());
		//Eager get @ManytoOne, @OnetoOne
		
		result.setMeFirstBooks(null);
		result.setMeFourthBooks(null);
		result.setMeSecondBooks(null);
		result.setMeThirdBooks(null);
				
		
		return result;
	}
	
	@Transient
	public Author setToPlainObjectQuick(Author instance)
	{
		//Get self
		instance.getName();
		Author result = new Author();
		result.setId(instance.getId());
		result.setName(instance.getName());
		//Eager get @ManytoOne, @OnetoOne
		
		result.setMeFirstBooks(null);
		result.setMeFourthBooks(null);
		result.setMeSecondBooks(null);
		result.setMeThirdBooks(null);
				
		
		return result;
	}
	
	
	
	
	

}
