package com.somil.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somil.blog.payloads.UserDTO;
import com.somil.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	//Post User
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
		UserDTO creatUserDto = this.userService.creatUser(userDto);
		return new ResponseEntity<>(creatUserDto,HttpStatus.CREATED);
	}
	
	//Put Mapping 
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto, @PathVariable("userId") Integer id){
		UserDTO updateUser = this.userService.updateUser(userDto, id);
		return ResponseEntity.ok(updateUser);
	}
	
	//Delete Mapping
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uId){
		this.userService.deleteUser(uId);
		return new ResponseEntity(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
	}
	
	//Get All users
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getALlUsers(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//Get user by Id
		@GetMapping("/{userID}")
		public ResponseEntity<UserDTO> getUserById(@PathVariable("userID") int userId ){
			return ResponseEntity.ok(this.userService.getUser(userId));
		}
	
	
	
}
