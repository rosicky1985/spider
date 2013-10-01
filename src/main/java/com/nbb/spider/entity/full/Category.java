package com.nbb.spider.entity.full;

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

}
