package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.RoleDao;
import com.example.demo.entity.Authority;
import com.example.demo.service.UserService;
import com.example.demo.dao.AccountDao;
import com.example.demo.dao.AuthoriryDao;



@RestController
public class AuthorityCOntroller {
	@Autowired
	AuthoriryDao authoriryDao;
	@Autowired
	RoleDao roleDAO;
	@Autowired
	AccountDao accountDAO;
	@Autowired
	UserService userService;
	@GetMapping("/rest/authorities")
	public Map<String, Object> getAuthorities(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("authorities", authoriryDao.findAll());
		data.put("roles", roleDAO.findAll());
		data.put("accounts", accountDAO.findAll());
		return data;
	}
	@PostMapping("/rest/authorities")
	public Authority create(@RequestBody Authority authorities) {
		
		
		return authoriryDao.save(authorities);
	}
	@DeleteMapping("/rest/authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authoriryDao.deleteById(id);
		System.out.println(id);
	}
}
