package com.somil.blog.services;

import java.util.List;

import com.somil.blog.payloads.UserDTO;

public interface UserService {
	public UserDTO creatUser(UserDTO user);
	public UserDTO updateUser(UserDTO user, Integer userId);
	public UserDTO getUser(Integer userId);
	public List<UserDTO> getAllUsers();
	public void deleteUser(Integer id);
}
