package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;

public interface AccountService  {
	List<Account> findByUsername(String username);

    List<Account> findAll();

    
    List<Account> getAdministrators();
}
