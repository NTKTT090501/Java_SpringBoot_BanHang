package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountDao;
import com.example.demo.dao.AuthoriryDao;
import com.example.demo.entity.Account;
import com.example.demo.entity.Authority;
import com.example.demo.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AccountDao accountDao;
	@Autowired
	AuthoriryDao authoriryDao;
	@Override
	public List<Authority> findAll() {
		// TODO Auto-generated method stub
		return authoriryDao.findAll();
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		// TODO Auto-generated method stub
		
		List<Account> accounts = accountDao.getAdministrators();
		return authoriryDao.authoritiesOf(accounts);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		authoriryDao.deleteById(id);
	}

	@Override
	public Authority create(Authority auth) {
		// TODO Auto-generated method stub
		return authoriryDao.save(auth);
	}
    
}
