/**
 * 
 */
package com.ydk.account.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author y140zhan
 *
 */
@MappedSuperclass
public class Person implements java.io.Serializable {

	protected String name;
	
	public Person()
	{
		return;
	}
	
	public Person(String name)
	{
		this.name = name;
	}
	
	@Column(name = "name")
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
