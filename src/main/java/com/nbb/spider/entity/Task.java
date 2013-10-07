package com.nbb.spider.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {
	@Column
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	private Date created;
	@Column
	private Date target;

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTarget() {
		return target;
	}

	public void setTarget(Date target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", created=" + created + ", target=" + target
				+ "]";
	}

}
