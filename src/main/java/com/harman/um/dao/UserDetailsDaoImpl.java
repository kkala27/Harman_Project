package com.harman.um.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.harman.um.model.UserDetails;
import com.harman.um.repo.UserDetailsRepo;

@Component
public class UserDetailsDaoImpl implements UserDetailsDao {

	@Autowired
	UserDetailsRepo repo;

	@Override
	public UserDetails getUserById(Long id) {
		// TODO Auto-generated method stub
		Optional<UserDetails> user = repo.findById(id);
		UserDetails userDetails = null;
		if (user.isPresent()) {
			userDetails = user.get();
		}
		return userDetails;
	}

	@Override
	public UserDetails saveUser(UserDetails user) {
		// TODO Auto-generated method stub
		return repo.save(user);
	}

	@Override
	public List<UserDetails> getAllUsers() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<UserDetails> getUsersByFilter(String filter) {
		// TODO Auto-generated method stub
		return repo.search(filter);
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	public void setRepo(UserDetailsRepo repo) {
		this.repo = repo;
	}

}
