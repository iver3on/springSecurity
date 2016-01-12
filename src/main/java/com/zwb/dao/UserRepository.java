package com.zwb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zwb.pojo.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
}
