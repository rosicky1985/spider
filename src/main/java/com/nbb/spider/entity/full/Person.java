package com.nbb.spider.entity.full;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person extends Dimension {
	public Person() {
		super();
	}

	public Person(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [Id=" + id + ", name=" + name + "]";
	}

	@Id
	@GeneratedValue
	@Column
	@Override
	public Integer getId() {
		return super.getId();
	}

	@Column
	@Override
	public String getName() {
		return super.getName();
	}
}
