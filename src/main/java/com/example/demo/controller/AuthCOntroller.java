package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.dao.AccountDao;
import com.example.demo.entity.Account;
import com.example.demo.service.impl.UserService;

@Controller
public class AuthCOntroller {

	@RequestMapping("/auth/login/form")
	public String form() {
	
		return "auth/login";
	}
	@RequestMapping("/auth/login/success")
	public String success(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		
		return "forward:/auth/login/form";
	}
	@RequestMapping("/auth/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		
		return "forward:/auth/login/form";
	}
	@RequestMapping("/auth/logoff/success")
	public String logout(Model model) {
		model.addAttribute("message", "Đăng xuất thành công");
		return "forward:/auth/login/form";
	}
	@RequestMapping("/auth/access/denied")
	  public String denied(Model model){
	    model.addAttribute("message","Bạn Không Có Quyền Truy Xuất!");
	    return "auth/login";
	  }
	@Autowired
	UserService userService;
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
		userService.loginFromOAuth2(oAuth2AuthenticationToken);
		return "forward:/auth/login/success";
	}

	
}
