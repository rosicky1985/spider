package com.nbb.spider.entity.full;

public class Person extends Dimension{
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
}
