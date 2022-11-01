package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.dao.AccountDao;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import com.example.demo.service.impl.UserService;



@Configuration
@EnableWebSecurity

public class AuthConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;
	@Autowired
	BCryptPasswordEncoder pe;
	@Autowired
	UserService service;
	
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //quản lý dữ liệu người dùng 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(service);
//    	auth.inMemoryAuthentication()
//        .withUser("user1").password("$2a$12$PUXFUqReaB6nXm/XpguoFuA8DWhVzF3EVntGResjv5xwmVKBy062q").roles("GUEST").and()
//        .withUser("user2").password(getBCryptPasswordEncoder().encode("123")).roles("GUEST","USER").and()
//        .withUser("user3").password(getBCryptPasswordEncoder().encode("123")).roles("GUEST","USER","ADMIN");
    	
    }

    //Phân quyền sử dụng  và hình thức đăng nhập
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //CSRF,CORS
    	http.csrf().disable();
    	http.authorizeRequests()
    		.antMatchers("/order/**").authenticated()
    		.antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
    		.antMatchers("/rest/authorities").hasRole("DIRE")
    		.anyRequest().permitAll();
    	http.formLogin()
    	.loginPage("/security/login/form")
    	.loginProcessingUrl("/security/login")
    	.defaultSuccessUrl("/security/login/success",false)
    	.failureUrl("/security/login/error");
    	http.logout()
    	.logoutUrl("/security/logoff")
    	.logoutSuccessUrl("/security/logoff/success");
    	
    	
    	
//    	http.csrf().disable().cors().disable();
//
//        // phân quyền sử dụng
//        http.authorizeRequests()
//        //Bài 1,2  .antMatchers("/home/index","/auth/login/**").permitAll().anyRequest().authenticated();
//        
//        //bài3
//        .antMatchers("/home/users").hasAnyRole("ADMIN","USER")
//        
//        .antMatchers("/home/authenticated").authenticated().anyRequest().permitAll();
//       
//
//        
//
//
//        http.exceptionHandling().accessDeniedPage("/auth/access/denied");
//
//        // Đăng nhập hộp thoại trình duyệt bài 1
//        //http.httpBasic();
//
//        //Đăng nhập bằng form
//        http.formLogin()
//        .loginPage("/auth/login/form")
//        .loginProcessingUrl("/auth/login") //login
//        .defaultSuccessUrl("/auth/login/success",false)
//        .failureUrl("/auth/login/error")
//        .usernameParameter("username")
//        .passwordParameter("password");
//        http.rememberMe().rememberMeParameter("remember");
//
//        //đăng xuất
//        http.logout()
//        .logoutUrl("/auth/logoff")
//        .logoutSuccessUrl("/auth/logoff/success");
//// 
//        http.oauth2Login()
//        .loginPage("/oauth2/login/form")
//        .defaultSuccessUrl("/oauth2/login/success",true)
//        .failureUrl("/auth/login/error")
//        .authorizationEndpoint()
//        	.baseUri("/oauth2/authorization");
    }
}
