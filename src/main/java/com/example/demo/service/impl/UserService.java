package com.example.demo.service.impl;


import java.util.List;
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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountDao;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;



@Service
public class UserService implements UserDetailsService {

	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder pe;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
			Account account = new Account();
			List<Account> acc = accountService.findByUsername(username);
			
			String password = pe.encode(acc.get(0).getPassword());
			String[] roles = acc.get(0).getAuthorities().stream()
								.map(au -> au.getRole().getId())
								.collect(Collectors.toList()).toArray(new String[0]);
			return User.withUsername(username).password(password).roles(roles).build();
		}catch(Exception e) {
			throw new UsernameNotFoundException(username + " not found username!");
		}
	}
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		UserDetails user = User.withUsername(email)
			.password(pe.encode(password)).roles("CUST").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
