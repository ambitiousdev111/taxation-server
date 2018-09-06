package com.taxation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxation.dao.interfaces.IPersonDAO;
import com.taxation.model.Person;
import com.taxation.service.interfaces.IPersonService;

@Service
public class PersonService implements IPersonService {
	@Autowired
	private IPersonDAO iPersonDAO;
	
	@Override
	public void save(Person person) {
		iPersonDAO.save(person);
	}

	@Override
	public List<Person> getAll() {
		return iPersonDAO.findAll();
	}

	@Override
	public Person getPersonBySamagraId(String id) {
		return iPersonDAO.findBySamagraId(id);
	}

	@Override
	public Person getPersonByPhoneNumber(String number) {
		// TODO Auto-generated method stub
		return iPersonDAO.findByPhoneNumber(number);
	}
	
}