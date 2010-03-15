package com.ydk.account.persistence.entity;

// Generated Jan 25, 2010 9:49:58 AM by Hibernate Tools 3.2.4.GA

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ydk.book.persistence.entity.Book;



/**
 * Owners generated by hbm2java
 */
@Entity
@Table(name = "file_descriptor", catalog = "ydk")
public class FileDescriptor implements java.io.Serializable {

	private Long id;
	private String fileName;
	private String fileType;
	private Long fileSize;
	private String discription;
	private String width;
	private String length;
	
	
	private Long fileId;
	

	public FileDescriptor() {
	}

	public FileDescriptor(String fileName, String fileType, Long fileSize, String discription,
			Long fileId) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.discription = discription;
		this.fileId = fileId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "file_name", nullable = false)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "file_type", nullable = false)
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Column(name = "file_size", nullable = false)
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	@Column(name = "discription")
	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	@Column(name = "width")
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	
	@Column(name = "length")
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
	
//	@Lob
//	@Column(name = "fileContent")
//	public byte[] getFileContent() {
//		return fileContent;
//	}
//
//	public void setFileContent(byte[] fileContent) {
//		this.fileContent = fileContent;
//	}
	
	@Column(name = "file_id", nullable=true)
	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	/*
	 * Add more helper methods here...
	 */
	@Transient
	public boolean isNew() {
		return (this.id == null);
	}
	/**
	 * For more strict JPA
	 */
	@Transient
	public void setNew(boolean isNew) {
		;
	}
	@Transient
	public boolean getNew() {
		return (this.id == null);
	}
	
	
	@Transient
	public FileDescriptor setToPlainObject(FileDescriptor instance)
	{
		//Get self
		instance.getFileName();
		
		//Eager get @ManytoOne, @OnetoOne
		
		return instance;
	}
	
	@Transient
	public FileDescriptor setToPlainObjectQuick(FileDescriptor instance)
	{
		//Get self
		instance.getFileName();
		
		//Eager get @ManytoOne, @OnetoOne
		
		return instance;
	}


}
