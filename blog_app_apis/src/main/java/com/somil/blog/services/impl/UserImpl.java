package com.somil.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somil.blog.entities.User;
import com.somil.blog.payloads.UserDTO;
import com.somil.blog.repositories.UserRepo;
import com.somil.blog.services.UserService;
import com.somil.blog.exceptions.*;

@Service
public class UserImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDTO creatUser(UserDTO userDto) {
		this.userRepo.save(dtoToUser(userDto));
		return null;
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updateUser = this.userRepo.save(user);
		return this.userToDto(updateUser);
	}

	@Override
	public UserDTO getUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDTO> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
	}
	
	public User dtoToUser(UserDTO userDto) {
		User user  = new User();
		user.setId(userDto.getId());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	public UserDTO userToDto(User user) {
		UserDTO userDto  = new UserDTO();
		userDto.setId(user.getId());
		userDto.setAbout(user.getAbout());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

}
