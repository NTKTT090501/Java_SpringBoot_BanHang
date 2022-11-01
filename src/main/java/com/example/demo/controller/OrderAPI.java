package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.example.demo.entity.Order;

import com.example.demo.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderAPI {
	@Autowired
	OrderService orderService;
	
	
	
	
	@PostMapping()
	public Order post(@RequestBody JsonNode orderData){
		
		return orderService.create(orderData);
	}
	
}
