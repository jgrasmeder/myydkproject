package com.ydk.account.persistence.entity;

// Generated Feb 6, 2010 3:22:38 PM by Hibernate Tools 3.2.4.GA

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * YdkAccount generated by hbm2java
 */
@Entity
@Table(name = "ydk_account", catalog = "ydk")
public class YdkAccount implements java.io.Serializable {

	private Long id;
	private YdkRole ydkRole;
	private String name;
	private String password;
	private String email;
	private Boolean isActived;

	public YdkAccount() {
	}

	public YdkAccount(YdkRole ydkRole, String name, String email) {
		this.ydkRole = ydkRole;
		this.name = name;
		this.email = email;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	public YdkRole getYdkRole() {
		return this.ydkRole;
	}

	public void setYdkRole(YdkRole ydkRole) {
		this.ydkRole = ydkRole;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	/**
	 * @return the password
	 */
	@Column(name = "password", length = 50)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	@Column(name = "email", nullable = false, length = 80)
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "is_actived")
	public Boolean getIsActived() {
		return this.isActived;
	}
	public void setIsActived(Boolean isActived) {
		this.isActived = isActived;
	}

}
