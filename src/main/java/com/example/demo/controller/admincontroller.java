package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Controller

public class admincontroller {
	@Autowired
	ProductService productService;
	@RequestMapping("/index.com")
	public String index(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		return "giaodien/layout/index";
	}
	
	@RequestMapping("/index.com/product/list")
	public String list( Model model, @RequestParam("cid") Optional<String> cid) {
		if(cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		}
		else {
			List<Product> list = productService.findAll();
			model.addAttribute("items", list);
		}
		return "giaodien/product/list";
	}
	@RequestMapping("/index.com/product/detail/{id}")
	public String detail(Model model,@PathVariable Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "giaodien/product/detail";
	}
	@RequestMapping("/index.com/cart")
	public String Cart() {
		return "giaodien/cart/shopping-cart";
	}
}
