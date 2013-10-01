package com.nbb.spider.entity.full;

public class Person {
	public Person() {
		super();
	}

	public Person(Integer id, String name) {
		super();
		Id = id;
		this.name = name;
	}

	private Integer Id;
	private String name;
	

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [Id=" + Id + ", name=" + name + "]";
	}
}
