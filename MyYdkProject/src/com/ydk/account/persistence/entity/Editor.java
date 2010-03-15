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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author y140zhan
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "editor", catalog = "ydk")
public class Editor extends Person implements Serializable {

	private Long id;
	
	public Editor()
	{
		
	}
	
	
	
	/**
	 * @param id
	 */
	public Editor(Long id, String name) {
		this.id = id;
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
	
	
	
	@Transient
	public Editor setToPlainObject(Editor instance)
	{
		//Get Self
		getName();
		Editor result = new Editor();
		result.cloneInstance(this);
		//Eager get @ManytoOne, @OnetoOne
		

		return result;
	}
	
	@Transient
	public Editor setToPlainObjectQuick(Editor instance)
	{
		//Get Self
		instance.getName();
		Editor result = new Editor();
		result.cloneInstance(this);
		return result;
	}
	
	
	public void  cloneInstance(Editor instance) {
		id = instance.id;
		name = instance.name;
	}
}
