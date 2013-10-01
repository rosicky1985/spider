package com.nbb.spider.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Task {

	private Integer id;
	private Date created;

	@Column(name = "created")
	public Date getCreated() {
	     return this.created;
	   }

	public void setCreated(Date created) {
	     this.created = created;
	   }

	@Id
	@GeneratedValue
	@Column(name = "id")
	public Integer getId() {
	     return this.id;
	   }

	public void setId(Integer id) {
	     this.id = id;
	   }

}
