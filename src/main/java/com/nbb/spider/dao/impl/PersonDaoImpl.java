package com.nbb.spider.dao.impl;

import org.springframework.stereotype.Repository;

import com.nbb.spider.dao.AbstractDao;
import com.nbb.spider.dao.PersonDao;
import com.nbb.spider.entity.full.Person;

;
@Repository
public class PersonDaoImpl extends AbstractDao<Person> implements PersonDao {

	@Override
	public Person getByName(String name) {
		Person person = (Person) this.getSession()
				.createQuery("from Person where name = :name")
				.setString("name", name).uniqueResult();
		if (person == null) {
			person = new Person();
			person.setName(name);
			this.save(person);
		}
		return person;
	}
}
