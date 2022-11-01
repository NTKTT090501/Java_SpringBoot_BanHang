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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ProductDao;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

	@Autowired
	ProductService productService;

	@GetMapping()
	public ResponseEntity<List<Product>> getAll(Model model) {
		return ResponseEntity.ok(productService.findAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> getOne(@PathVariable("id") Integer id) {

		return ResponseEntity.ok(productService.findById(id));
	}

	@PostMapping()
	public Product create(@RequestBody Product product) {

		return productService.create(product);
	}

	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {

		return productService.update(product);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {

		productService.delete(id);
	}

}
