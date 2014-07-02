package com.matafe.springmvc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Mauricio T. Ferraz
 */
@Entity
@Table(name = "MESSAGES")
@XmlRootElement
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column(nullable = false)
	private String text;
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();
	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	public Message() {
	}

	public Message(Integer id, String text) {
		this.id = id;
		this.text = text;
	}

	public Message(Integer id, String text, Date creationDate) {
		this.id = id;
		this.text = text;
		this.creationDate = creationDate;
	}

	public Message(Integer id, String text, User createdBy) {
		this.id = id;
		this.text = text;
		this.createdBy = createdBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
