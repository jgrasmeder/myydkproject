package com.ydk.account.persistence.entity;

// Generated Feb 6, 2010 3:22:38 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ydk.book.persistence.entity.Book;

/**
 * Transaction generated by hbm2java
 */
@Entity
@Table(name = "transaction", catalog = "ydk")
public class Transaction implements java.io.Serializable {

	private Long id;
	private TransactionType transactionType;
	private Book book;
	private Reader reader;
	private Partner partner;
	private Date date;

	public Transaction() {
	}

	public Transaction(TransactionType transactionType, Book book,
			Reader reader, Partner partner, Date date) {
		this.transactionType = transactionType;
		this.book = book;
		this.reader = reader;
		this.partner = partner;
		this.date = date;
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
	@JoinColumn(name = "type")
	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST})
	@JoinColumn(name = "book")
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST})
	@JoinColumn(name = "reader")
	public Reader getReader() {
		return this.reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST})
	@JoinColumn(name = "partner")
	public Partner getPartner() {
		return this.partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 0)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@Transient
	public Transaction setToPlainObject(Transaction instance)
	{
		//Get self
		getDate();
		Transaction result = new Transaction();
		result.cloneTransaction(this);
		
		//Eager get @ManytoOne, @OnetoOne
		if (result.getBook() != null)
			result.setBook(
					getBook().setToPlainObjectQuick(getBook()));
		if (getReader() != null)
			result.setReader(
					getReader().setToPlainObjectQuick(getReader()));
		if (getTransactionType() != null)
			result.setTransactionType(
					getTransactionType().setToPlainObjectQuick(getTransactionType()));
		if (getPartner() != null)
			result.setPartner(
					getPartner().setToPlainObjectQuick(getPartner()));
		
		//Null oneToMany;
		

		return result;
	}
	
	@Transient
	public Transaction setToPlainObjectQuick(Transaction instance)
	{
		//Get self
		getDate();
		Transaction result = new Transaction();
		result.cloneTransaction(this);
		//Eager get @ManytoOne, @OnetoOne
		result.setBook(null);
		
		result.setReader(null);
		
		result.setTransactionType(null);
		
		result.setPartner(null);
		
		//Null oneToMany;
		

		return result;
	}
	
	public void cloneTransaction(Transaction transaction) {
		id = transaction.id;
		transactionType = transaction.transactionType;
		book = transaction.book;
		reader = transaction.reader;
		partner = transaction.partner;
		date = transaction.date;
	}

}
