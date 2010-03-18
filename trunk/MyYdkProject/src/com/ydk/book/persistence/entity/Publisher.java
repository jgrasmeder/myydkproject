package com.ydk.book.persistence.entity;

// Generated Feb 6, 2010 3:22:38 PM by Hibernate Tools 3.2.4.GA

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * Transaction generated by hbm2java
 */
@Entity
@Table(name = "publisher", catalog = "ydk")
public class Publisher implements java.io.Serializable {

	private Long id;
	private String name;
	private String type;
	private String introduction;
	private List<Book> books = new ArrayList<Book>(0);

	public Publisher() {
	}
	
	public Publisher(String name) {
		this.name = name;
	}

	public Publisher(String name, String type,
			String introduction, List<Book> books) {
		this.name = name;
		this.type = type;
		this.introduction = introduction;
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
	
	@Column(name = "type")
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "introduction")
	public String getIntroduction() {
		return this.introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@OneToMany(fetch = FetchType.LAZY, 
			mappedBy = "publisher")
	public List<Book> getBooks() {
		return this.books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	@Transient
	public Publisher setToPlainObject(Publisher instance)
	{
		//Get self
		instance.getName();
		//Eager get @ManytoOne, @OnetoOne

		//Null oneToMany;
		instance.setBooks(null);

		return instance;
	}
	
	@Transient
	public Publisher setToPlainObjectQuick(Publisher instance)
	{
		//Get self
		instance.getName();
		//Eager get @ManytoOne, @OnetoOne

		//Null oneToMany;
		instance.setBooks(null);

		return instance;
	}
}