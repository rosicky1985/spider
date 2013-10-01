package com.nbb.spider.entity.full;

public class DataSource {
	private Integer id;
	private String name;
	private Company company;
	private String url;
	private Type type;

	public DataSource() {
		super();
	}

	public DataSource(Integer id, String name, Company company, String url,
			Type type) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.url = url;
		this.type = type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
		return "DataSource [id=" + id + ", name=" + name + ", company="
				+ company + ", url=" + url + ", type=" + type + "]";
	}
}
