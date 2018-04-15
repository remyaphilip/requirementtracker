package com.project.model;
// Generated Mar 10, 2018 11:16:05 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserHasProject generated by hbm2java
 */
@Entity
@Table(name = "user_has_project", catalog = "project_mgmt")
public class UserHasProject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2753822773573147122L;
	private UserHasProjectId id;
	private Role role;

	public UserHasProject() {
	}

	public UserHasProject(UserHasProjectId id, Role role) {
		this.id = id;
		this.role = role;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)),
			@AttributeOverride(name = "projectId", column = @Column(name = "project_id", nullable = false)) })
	public UserHasProjectId getId() {
		return this.id;
	}

	public void setId(UserHasProjectId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
