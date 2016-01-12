package com.zwb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zwb.dao.UserRepository;
import com.zwb.pojo.User;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired UserRepository userRepository;
    
	public UserDetails loadUserByUsername(String username){
	 User user = userRepository.findByUsername(username);
    if(user == null){
        throw new UsernameNotFoundException("not found");
    }
    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
    System.err.println("username is " + username + ", " + user.getRole().name());
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
            user.getPassword(), authorities);
	}

}
