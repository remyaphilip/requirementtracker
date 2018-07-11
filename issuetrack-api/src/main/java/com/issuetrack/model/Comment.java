package com.issuetrack.model;
// Generated Mar 10, 2018 9:58:47 PM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Comment generated by hbm2java
 */
@Entity
@Table(name = "comment", catalog = "proman_db")
public class Comment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6720561272448055330L;
	private Integer commentId;
	private Issue issue;
	private String description;
	private Date logDate;

	public Comment() {
	}

	public Comment(Issue issue) {
		this.issue = issue;
	}

	public Comment(Issue issue, String description, Date logDate) {
		this.issue = issue;
		this.description = description;
		this.logDate = logDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "comment_id", unique = true, nullable = false)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issue_id", nullable = false)
	public Issue getIssue() {
		return this.issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "log_date", length = 10)
	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

}
