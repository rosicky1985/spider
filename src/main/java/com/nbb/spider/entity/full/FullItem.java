package com.nbb.spider.entity.full;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.Task;

/**
 * 抓取视频网站排名的宽表
 * 
 * @author rosicky
 * 
 */
@Entity
@Table(name = "item")
public class FullItem implements Item {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String title;
	@Column
	private Integer rank;
	@Column(name = "[index]")
	private Integer index;// 收视指数 日报周报和月报分别不一样，但都共用该字段
	@OneToMany
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinTable(name = "item_actors", joinColumns = { @JoinColumn(name = "item_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "actor_id", referencedColumnName = "id") })
	private List<Person> actors;
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "director_id")
	private Person director;
	@OneToMany
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinTable(name = "item_categories", joinColumns = { @JoinColumn(name = "item_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "category_id", referencedColumnName = "id") })
	private List<Category> categories;
	@ManyToOne
	@JoinColumn(name = "data_source_id")
	private DataSource dataSource;
	@Column
	private String area;
	@ManyToOne
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "task_id")
	private Task task;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public int compareRankto(Item paramItem) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}

	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "FullItem [id=" + id + ", title=" + title + ", rank=" + rank
				+ ", index=" + index + ", actors=" + actors + ", director="
				+ director + ", categories=" + categories + ", dataSource="
				+ dataSource + ", area=" + area + ", task=" + task + "]";
	}

}