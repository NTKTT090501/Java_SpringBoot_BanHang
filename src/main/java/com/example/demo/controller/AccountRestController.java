package com.example.demo.controller;

import java.util.HashMap;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Authority;
import com.example.demo.service.AccountService;
import com.example.demo.service.AuthorityService;
import com.example.demo.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	AuthorityService authorityService;
	@Autowired
	RoleService roleService;
	@Autowired
	AccountService accountService;

    @GetMapping()
	public Map<String, Object> getAuthorities(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("authorities", authorityService.findAll());
		data.put("roles", roleService.findAll());
		data.put("accounts", accountService.findAll());
		
		return data;
	}
    @PostMapping()
	public Authority create(@RequestBody Authority authorities) {
		
		
		return authorityService.create(authorities);
	}
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		authorityService.delete(id);
		System.out.println(id);
	}
//    @GetMapping
//    public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin){
//        if(admin.orElse(false)){
//            return accountService.getAdministrators();
//        }
//        System.out.println(admin);
//        return accountService.findAll();
//    }
}
