package com.nbb.spider.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nbb.spider.dao.AbstractDao;
import com.nbb.spider.dao.CategoryDao;
import com.nbb.spider.entity.full.Category;

@Repository
public class CategoryDaoImpl extends AbstractDao<Category> implements
		CategoryDao {

	@Override
	@Transactional
	public Category getByName(String name) {
		Category cateGory = (Category) this.getSession()
				.createQuery("from Category where name = :name")
				.setString("name", name).uniqueResult();
		if (cateGory == null) {
			cateGory = new Category();
			cateGory.setName(name);
			this.save(cateGory);
		}
		return cateGory;
	}

}
