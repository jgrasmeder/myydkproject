/**
 * 
 */
package com.ydk.book.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.ydk.account.persistence.entity.FileDescriptor;

/**
 * @author y140zhan
 *
 */
@MappedSuperclass
public class Subject implements java.io.Serializable {

	public static final String SUBJECT_TYPE_EVENT = "EventSubject";
	public static final String SUBJECT_TYPE_BOOKNEWS = "BookNewsSubject";
	public static final String SUBJECT_TYPE_BOOKREVIEW = "BookReviewSubject";
	public static final String SUBJECT_TYPE_INTERVIEW = "InterviewSubject";
	protected Long id;
	protected String title;
	protected String subjectType;
	protected Date date;
	protected Integer order;
	protected Boolean isNew;
	protected Boolean recommanded;
	protected Boolean visible;
	protected String introduction;
//	protected FileDescriptor titleImage;
//	protected List<FileDescriptor> images = new ArrayList<FileDescriptor>(0);
	protected String titleImage;
	protected ArrayList<String> images = new ArrayList<String>();
	protected Book book;

	
	public Subject()
	{
		return;
	}
	
	public Subject(String title)
	{
		this.title = title;
	}
	

	public Subject(Long id, String title, String subjectType, Date date,
			Integer order, Boolean isNew, Boolean recommanded, Boolean visible,
			String introduction, String titleImage, ArrayList<String> images,
			Book book) {
		this.id = id;
		this.title = title;
		this.subjectType = subjectType;
		this.date = date;
		this.order = order;
		this.isNew = isNew;
		this.recommanded = recommanded;
		this.visible = visible;
		this.introduction = introduction;
		this.titleImage = titleImage;
		this.images = images;
		this.book = book;
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
	
	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "subjectType", length=40)
	public String getSubjectType() {
		return this.subjectType;
	}
	public void setSubjectType(String type) {
		this.subjectType = type;
	}
	
	@Column(name = "date")
	public Date getDate() {
		return this.date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name = "myOrder")
	public Integer getOrder() {
		return this.order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Column(name = "is_new")
	public Boolean getIsNew() {
		return this.isNew;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	
	@Column(name = "recommanded")
	public Boolean getRecommanded() {
		return this.recommanded;
	}
	public void setRecommanded(Boolean recommanded) {
		this.recommanded = recommanded;
	}
	
	@Column(name = "visible")
	public Boolean getVisible() {
		return this.visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	@Column(name = "introduction")
	public String getIntroduction() {
		return this.introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	@Column(name = "images")
	public ArrayList<String> getImages()
    {
		return this.images;
    }
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	
	@Transient
	public Book getBook() {
		return this.book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	@Column(name = "titleImage")
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	
	
	
	
	
	
	
	
}
