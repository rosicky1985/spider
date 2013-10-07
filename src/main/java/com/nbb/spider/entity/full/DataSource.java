package com.nbb.spider.entity.full;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "data_source")
public class DataSource {
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column
	private String name;
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)  
    @JoinColumn(name="company_id") 
	private Company company;
	@Column
	private String url;
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)  
    @JoinColumn(name="type_id")
	private Type type;
	@Column
	private String bean;

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

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}

}
