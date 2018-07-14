package com.proman.api.project.model;
// Generated Mar 10, 2018 11:16:05 AM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Project generated by hbm2java
 */
@Entity
@Table(name = "project", catalog = "proman_db")
public class Project implements java.io.Serializable {

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", board=" + board + ", name=" + name + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1469532684926976748L;
	private Integer projectId;
	@JsonIgnore
	private Board board;
	private String name;
	private String organisation;

	public Project() {
	}

	public Project(Board board) {
		this.board = board;
	}

	public Project(Board board, String name, String organisation) {
		this.board = board;
		this.name = name;
		this.organisation = organisation;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "project_id", unique = true, nullable = false)
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", nullable = false)
	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "organisation", length = 45)
	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
}