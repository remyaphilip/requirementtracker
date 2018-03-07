package com.project.model;
// Generated Mar 4, 2018 7:08:47 PM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Board generated by hbm2java
 */
@Entity
@Table(name = "board", catalog = "project_mgmt")
public class Board implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6354180993141666738L;
	private Integer boardId;
	private Set<Project> projects = new HashSet<Project>(0);
	private Set<List> lists = new HashSet<List>(0);

	public Board() {
	}

	public Board(Set<Project> projects, Set<List> lists) {
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
