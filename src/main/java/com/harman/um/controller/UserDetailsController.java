package com.harman.um.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.harman.um.model.SaveUserResponse;
import com.harman.um.model.UserDetails;
import com.harman.um.service.UserDetailsService;

@RestController
public class UserDetailsController {

	@Autowired
	UserDetailsService userService;

	@GetMapping(value = "getUserById/{id}")
	public UserDetails getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}

	@PostMapping("/saveUser")
	@ResponseBody
	public SaveUserResponse login(@RequestBody UserDetails user) {
		SaveUserResponse resp = userService.saveUser(user);
		return resp;
	}

	@GetMapping(value = "getAllUsers")
	public List<UserDetails> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping(value = "getUsersByFilter/{filter}")
	public List<UserDetails> getUsersByFilter(@PathVariable("filter") String filter) {
		return userService.getUsersByFilter(filter);
	}

	@DeleteMapping(value = "deleteUserById/{id}")
	public String deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
		return "Successfully Deleted";
	}

}
