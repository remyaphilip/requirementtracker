package com.project.model;
// Generated Mar 10, 2018 11:16:05 AM by Hibernate Tools 5.2.3.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * List generated by hbm2java
 */
@Entity
@Table(name = "list", catalog = "project_mgmt")
public class List implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6595222078965872317L;
	private Integer listId;
	@JsonIgnore
	private Board board;
	private String listName;
	@JsonIgnore
	private Integer projectId;
	private Set<Card> cards = new HashSet<Card>(0);

	public List() {
	}

	public List(String listName) {
		this.listName = listName;
	}

	public List(Board board, String listName, Set<Card> cards, Integer projectId) {
		this.board = board;
		this.listName = listName;
		this.cards = cards;
		this.projectId = projectId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "list_id", unique = true, nullable = false)
	public Integer getListId() {
		return this.listId;
	}

	public void setListId(Integer listId) {
		this.listId = listId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_board_id")
	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Column(name = "list_name", nullable = false, length = 45)
	public String getListName() {
		return this.listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "list")
	public Set<Card> getCards() {
		return this.cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	@Column(name = "project_id", nullable = false)
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}
