package com.nbb.spider.entity.full;

public class Type {
	public static Type DAILY = new Type(1, "日报");
	public static Type WEEKILY = new Type(2, "周报");
	public static Type MONTHLY = new Type(3, "月报");

	private Integer id;
	private String name;

	public Type() {
		super();
	}

	public Type(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + "]";
	}
}
