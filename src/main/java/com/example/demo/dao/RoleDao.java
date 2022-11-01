package com.example.demo.dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.demo.entity.Role;

public interface RoleDao extends JpaRepositoryImplementation<Role, String>{

}
