package com.harman.um.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.harman.um.dao.UserDetailsDao;
import com.harman.um.model.SaveUserResponse;
import com.harman.um.model.UserDetails;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsDao userDao;


	@Override
	public UserDetails getUserById(Long id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	@Override
	public SaveUserResponse saveUser(UserDetails user) {
		// TODO Auto-generated method stub
		SaveUserResponse saveUserResponse = new SaveUserResponse();
		saveUserResponse.setStatus("success");
		saveUserResponse = validateUserDetails(saveUserResponse, user);
		if (saveUserResponse.getStatus().equals("failure")) {
			return saveUserResponse;
		} else {
			if (user.getName() == null || user.getName().equals("")) {
				user.setName(user.getFirstName()+" "+user.getLastName());
			}
			UserDetails userDetails = userDao.saveUser(user);
			saveUserResponse.setUserDetails(userDetails);
		}

		return saveUserResponse;
	}

	public SaveUserResponse validateUserDetails(SaveUserResponse saveUserResponse, UserDetails user) {

		if (user.getFirstName() == null || user.getFirstName().equals("")) {
			saveUserResponse.setErrorMessage("Users first name cannot be empty");
			saveUserResponse.setStatus("failure");
		} else if (user.getLastName() == null || user.getLastName().equals("")) {
			saveUserResponse.setErrorMessage("Users Last name cannot be empty");
			saveUserResponse.setStatus("failure");
		} else if (user.getEmail() == null || user.getEmail().equals("")) {
			saveUserResponse.setErrorMessage("Users Email cannot be empty");
			saveUserResponse.setStatus("failure");
		} else if (user.getAge() == null) {
			saveUserResponse.setErrorMessage("Users Age cannot be empty");
			saveUserResponse.setStatus("failure");
		} else if (user.getPhone() == null || user.getPhone().equals("")) {
			saveUserResponse.setErrorMessage("Users first name cannot be empty");
			saveUserResponse.setStatus("failure");
		} else if (!checkValidEmail(user.getEmail())) {
			saveUserResponse.setErrorMessage("Invalid email address entered");
			saveUserResponse.setStatus("failure");
		} else if (!checkValidPhoneNumber(user.getPhone())) {
			saveUserResponse.setErrorMessage("Invalid Phone Number entered");
			saveUserResponse.setStatus("failure");
		}

		return saveUserResponse;
	}

	private boolean checkValidPhoneNumber(String phone) {
		boolean result = true;

		if (phone.startsWith("+91")) {
			try {
				Long phoneNumber = Long.parseLong(phone.substring(1));
			} catch (Exception e) {
				result = false;
			}
		} else {
			result = false;
		}

		return result;
	}

	public boolean checkValidEmail(String email) {
		boolean result = true;
		if (!(email.contains("@") && email.contains(".com"))) {
			result = false;
		}
		return result;
	}

	@Override
	public List<UserDetails> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.getAllUsers();
	}

	@Override
	public List<UserDetails> getUsersByFilter(String filter) {
		// TODO Auto-generated method stub
		return userDao.getUsersByFilter(filter);
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		 userDao.deleteUserById(id);
	}
	
	public void setUserDao(UserDetailsDao userDao) {
		this.userDao = userDao;
	}


}
