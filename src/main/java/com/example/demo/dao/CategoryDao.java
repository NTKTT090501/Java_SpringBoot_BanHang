package com.example.demo.dao;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.demo.entity.Category;

public interface CategoryDao extends JpaRepositoryImplementation<Category, String>{

}
