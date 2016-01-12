package com.zwb.security;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.zwb.service.CustomUserDetailsService;
/*
 * 
 * 我们可以看到，SecurityConfig上添加了@EnableWebMvcSecurity标注，使得Spring Security提供并且支持了Spring MVC的集成。

同时，SecurityConfig还继承了WebSecurityConfigurerAdapter类，并覆盖了其中的几个方法：

userDetailsService()将我们自定义的CustomUserDetailsService实例化并添加进security的上下文当中
configure(AuthenticationManagerBuilder auth) 在该方法中，我们使AuthenticationManager使用CustomUserDetailsService
作为其UserDetailsService实例。
configure(HttpSecurity http) 对URL进行权限配置，使得"/", "/home"不需要登录就可以访问，其他需要登录。登录的地址是'/login'，
当登录成功后将跳转到/helloadmin页面，并且登录、登出页面都是不需要登录就可以访问的。
这时候重启我们的应用，再次访问http://localhost:8080/hellouser，你会发现网页会自动跳转到登录页面，同时，我们的登录、登出功能已经实现好了。

实现一个UserDetailsService，再对Spring Security进行配置，这样，登录、登出以及登录的验证功能就可以实现啦，怎么样，是不是特别的简单。
 * 
 * 
 * */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		@Override
	    @Bean
	    public UserDetailsService userDetailsService() {
	        return new CustomUserDetailsService();
	    }

	    @Override  
	    protected void configure(AuthenticationManagerBuilder auth)  
	            throws Exception {  
	        auth.userDetailsService(userDetailsService());  
	    }  
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	                .antMatchers("/", "/home").permitAll()
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/helloadmin")
	                .permitAll()
	                .and()
	            .logout()
	                .permitAll();
	    }

}
