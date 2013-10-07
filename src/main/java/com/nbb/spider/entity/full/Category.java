package com.nbb.spider.entity.full;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends Dimension {
	public Category() {
		super();
	}

	public Category(int i, String name) {
		super();
		this.id = i;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
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
