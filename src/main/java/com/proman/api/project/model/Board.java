package com.proman.api.project.model;
// Generated Mar 10, 2018 11:16:05 AM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.proman.api.project.utils.View;

/**
 * Board generated by hbm2java
 */
@Entity
@Table(name = "board", catalog = "proman_db")
public class Board implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5214473609887527548L;
	@JsonView(View.BoardView.class)
	private Integer boardId;
	
	@JsonView(View.BoardView.class)
	private String projectType;
	
	@JsonIgnore
	private Set<Project> projects = new HashSet<Project>(0);
	@JsonIgnore
	private Set<List> lists = new HashSet<List>(0);
	

	public Board() {
	}

	public Board(String projectType) {
		this.projectType = projectType;
	}

	public Board(String projectType, Set<Project> projects, Set<List> lists) {
		this.projectType = projectType;
		this.projects = projects;
		this.lists = lists;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "board_id", unique = true, nullable = false)
	public Integer getBoardId() {
		return this.boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	@Column(name = "project_type", nullable = false, length = 45)
	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
	public Set<List> getLists() {
		return this.lists;
	}

	public void setLists(Set<List> lists) {
		this.lists = lists;
	}

	
}
