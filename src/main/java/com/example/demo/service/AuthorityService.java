package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Authority;

public interface AuthorityService {

	List<Authority> findAll();
	
	
	void delete(Integer id);

	Authority create(Authority auth);

	List<Authority> findAuthoritiesOfAdministrators();

}
