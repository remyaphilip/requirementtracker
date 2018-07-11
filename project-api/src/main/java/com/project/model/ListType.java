package com.project.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "listtype", catalog = "proman_db")
public class ListType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6537962060169667890L;
	private Integer listTypeId;
	private String listname;
	private Integer boardId;

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "listtypeid", unique = true, nullable = false)
	public Integer getListTypeId() {
		return listTypeId;
	}

	public void setListTypeId(Integer listTypeId) {
		this.listTypeId = listTypeId;
	}

	@Column(name = "listname", nullable = false, length = 45)
	public String getListname() {
		return listname;
	}

	public void setListname(String listname) {
		this.listname = listname;
	}

	@Column(name = "boardid", nullable = false)
	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

}
