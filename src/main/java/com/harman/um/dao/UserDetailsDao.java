package com.harman.um.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harman.um.model.UserDetails;

@Service
public interface UserDetailsDao {

	
	public UserDetails getUserById(Long id);

	public UserDetails saveUser(UserDetails user);

	public List<UserDetails> getAllUsers();

	public List<UserDetails> getUsersByFilter(String filter);

	public void deleteUserById(Long id);
	
}
