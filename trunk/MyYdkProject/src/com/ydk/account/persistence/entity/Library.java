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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Library
 */
@Entity
@Table(name = "library", catalog = "ydk", uniqueConstraints = @UniqueConstraint(columnNames = {
		"name"}))
public class Library implements java.io.Serializable {

	private Long id;
	private String name;
	private List<Category> categories = new ArrayList<Category>(0);

	public Library() {
	}

	public Library(String name) {
		this.name = name;
	}

	public Library(String name, List<Category> categories) {
		this.name = name;
		this.categories = categories;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "library", cascade = {CascadeType.REMOVE})
	public List<Category> getCategories() {
		return this.categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	@Transient
	public Library setToPlainObject(Library instance)
	{
		//Get Self
		getName();
		//Eager get @ManytoOne, @OnetoOne
//		getPartnerType().getType();
		Library result = new Library();
		result.setId(getId());
		result.setName(getName());
		//Null oneToMany;
		result.setCategories(null);
		

		return result;
	}
	@Transient
	public Library setToPlainObjectQuick(Library instance)
	{
		//Get Self
		getName();
		//Eager get @ManytoOne, @OnetoOne

		Library result = new Library();
		result.setId(getId());
		result.setName(getName());
		//Null oneToMany;
		result.setCategories(null);

		return result;
	}

}
