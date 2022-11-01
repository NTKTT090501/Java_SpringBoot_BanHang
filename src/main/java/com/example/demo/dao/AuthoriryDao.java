package com.example.demo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.demo.entity.Account;
import com.example.demo.entity.Authority;

public interface AuthoriryDao extends JpaRepository<Authority, Integer> {
	@Query("SELECT DISTINCT ar FROM Authority ar WHERE ar.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts);
	
	
}
