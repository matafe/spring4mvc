package com.matafe.springmvc.core.message;

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

import com.matafe.springmvc.core.security.User;

/**
 * <p>
 * Represents a Message.
 * 
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
	private Long id;

	@Column(nullable = false)
	private String text;

	@Column(name = "creation_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();

	@ManyToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;

	public Message() {
	}

	public Message(Long id, String text) {
		this.id = id;
		this.text = text;
	}

	public Message(Long id, String text, Date creationDate) {
		this.id = id;
		this.text = text;
		this.creationDate = creationDate;
	}

	public Message(Long id, String text, User createdBy) {
		this.id = id;
		this.text = text;
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + "]";
	}

}
