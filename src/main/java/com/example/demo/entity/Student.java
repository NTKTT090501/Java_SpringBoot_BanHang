package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Students")
public class Student {
	@Id
	String email;
	String fullname;
	Double marks;
	Boolean gender;
	String country;
}
