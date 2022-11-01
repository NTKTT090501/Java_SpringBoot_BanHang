package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;


@CrossOrigin("*")
@RestController
public class StudentAPI {
	@Autowired
	StudentDao dao;
	@GetMapping("/rest/students")
	public ResponseEntity<List<Student>> getAll(Model model){
		return ResponseEntity.ok(dao.findAll());
	}
	
	
	@GetMapping("/rest/students/{email}")
	public ResponseEntity<Student> getOne(@PathVariable("email") String email){
		if(!dao.existsById(email)) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(dao.findById(email).get());
	}
	@PostMapping("/rest/students")
	public ResponseEntity<Student> post(@RequestBody Student student){
		if(dao.existsById(student.getEmail())) {
			return ResponseEntity.badRequest().build();
			
		}
		dao.save(student);
		return ResponseEntity.ok(student);
	}
	@PutMapping("/rest/students/{email}")
	public ResponseEntity<Student> put(@PathVariable("email") String email,@RequestBody Student student){
		if(!dao.existsById(email)) {
			return ResponseEntity.notFound().build();
			
		}
		
		dao.save(student);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/rest/students/{email}")
	public ResponseEntity<Void> delete(@PathVariable("email") String email){
		if(!dao.existsById(email)) {
			return ResponseEntity.notFound().build();
			
		}
		dao.deleteById(email);
		return ResponseEntity.ok().build();
	}
}
