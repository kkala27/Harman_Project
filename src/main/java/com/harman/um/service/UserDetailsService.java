package com.harman.um.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harman.um.model.SaveUserResponse;
import com.harman.um.model.UserDetails;

@Service
public interface UserDetailsService {

	public UserDetails getUserById(Long id);

	public SaveUserResponse saveUser(UserDetails user);

	public List<UserDetails> getAllUsers();

	public List<UserDetails> getUsersByFilter(String filter);

	public void deleteUserById(Long id);

}
