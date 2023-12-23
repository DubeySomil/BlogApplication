package com.somil.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.somil.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
