package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homcontroller {
	@RequestMapping("/home/index1")
	public String index(Model model) {
		model.addAttribute("message", "This is home page");
		
		return "home/index";
	}
	@RequestMapping("/home/about1")
	public String about(Model model) {
		model.addAttribute("message", "This is introduction page");
		return "home/index";
	}
	// @PreAuthorize("hasRole('ADMIN')") //security bằng anno
    @RequestMapping("/home/admins1")
    public String admins(Model model) {
        
        // if(!request.isUserInRole("ADMIN")){           // security tự định nghĩa
        // return "redirect:/auth/access/denied";
        // }

        model.addAttribute("message", "Chào Admin!");
        return "home/index";
    }

    // @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/home/users1")
    public String users(Model model) {

         // if(!request.isUserInRole("ADMIN") || !request.isUserInRole("USERS")){       
        // return "redirect:/auth/access/denied";
        // }

        model.addAttribute("message", "Chào User!");
        return "home/index";
    }

    // @PreAuthorize("isAuthenticated()")
    @RequestMapping("/home/authenticated1")
    public String authenticated(Model model) {
        // if(!request.getRemoteUser() == null){      //nếu chưa đăng nhập thì trả về form login
        //     return "redirect:/auth/login/form";
        // }
        model.addAttribute("message", "Chào authenticated user!");
        return "home/index";
    }

    @RequestMapping("/home/thymeleaf11")
    public String thymeleaf1(Model model) {
        model.addAttribute("message","Thymeleaf-Without Extras");
        return "home/thymeleaf1";
    }

    @RequestMapping("/home/thymeleaf22")
    public String thymeleaf2(Model model) {
        model.addAttribute("message","Thymeleaf-With Extras");
        return "home/thymeleaf2";
    }
}
