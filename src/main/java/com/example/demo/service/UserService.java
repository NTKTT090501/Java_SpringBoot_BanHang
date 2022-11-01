package com.example.demo.service;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountDao;
import com.example.demo.entity.Account;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	AccountDao dao;
	@Autowired
	BCryptPasswordEncoder pe;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
			Account account = dao.findById(username).get();
			System.out.println(account);
			String password = account.getPassword();
			String[] roles = account.getAuthorities().stream()
								.map(au -> au.getRole().getId())
								.collect(Collectors.toList()).toArray(new String[0]);
			return User.withUsername(username).password(pe.encode(password)).roles(roles).build();
		}catch(Exception e) {
			throw new UsernameNotFoundException(username + " not found username!");
		}	
	}


}
