package com.harman.um.service;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.harman.um.dao.UserDetailsDao;
import com.harman.um.dao.UserDetailsDaoImpl;
import com.harman.um.model.SaveUserResponse;
import com.harman.um.model.UserDetails;

@RunWith(PowerMockRunner.class)
public class UserDetailsServiceImplTest {

	@MockBean
	UserDetailsDao userDetailsDao;

	@Mock
	UserDetailsServiceImpl userDetailsService;

	@Before
	public void setup() {
		userDetailsDao = mock(UserDetailsDaoImpl.class);
		userDetailsService = Mockito.spy(UserDetailsServiceImpl.class);
		userDetailsService.setUserDao(userDetailsDao);
	}

	@Test
	public void getUserByIdTest() {
		UserDetails user = getUserObject();
		Mockito.when(userDetailsDao.getUserById(Long.parseLong("1"))).thenReturn(user);
		Assert.assertEquals(userDetailsService.getUserById(Long.parseLong("1")).getId(), user.getId());
	}

	@Test
	public void getAllUsersTest() {
		List<UserDetails> userDetailsList =  new ArrayList<>();
		UserDetails user = getUserObject();
		userDetailsList.add(user);
		Mockito.when(userDetailsDao.getAllUsers()).thenReturn(userDetailsList);
		Assert.assertEquals(userDetailsService.getAllUsers(), userDetailsList);
	}
	
	
	@Test
	public void saveUser() {
		UserDetails user = getUserObject();
		Mockito.when(userDetailsDao.saveUser(user)).thenReturn(user);
		Assert.assertEquals(userDetailsService.saveUser(user).getStatus(), "success");
	}
	
	@Test
	public void validateUserDetailsTest() {
		SaveUserResponse saveUserResponse = new SaveUserResponse();
		saveUserResponse.setStatus("success");
		UserDetails user = getUserObject();
		Assert.assertEquals(userDetailsService.validateUserDetails(saveUserResponse, user).getStatus(), "success");
	}
	
	@Test
	public void validateUserDetailsNegetiveTest() {
		SaveUserResponse saveUserResponse = new SaveUserResponse();
		saveUserResponse.setStatus("success");
		UserDetails user = getUserObject();
		user.setPhone("9978989899");
		Assert.assertEquals(userDetailsService.validateUserDetails(saveUserResponse, user).getStatus(), "failure");
	}
	
	@Test
	public void getUsersByFilterTest() {
		List<UserDetails> userDetailsList =  new ArrayList<>();
		UserDetails user = getUserObject();
		userDetailsList.add(user);
		Mockito.when(userDetailsDao.getUsersByFilter("Lokesh")).thenReturn(userDetailsList);
		Assert.assertEquals(userDetailsService.getUsersByFilter("Lokesh"), userDetailsList);
	}
	
	
	public UserDetails getUserObject() {
		UserDetails user = new UserDetails();
		user.setId(Long.parseLong("1"));
		user.setFirstName("Lokesh");
		user.setLastName("Gupta");
		user.setEmail("abc@gmail.com");
		user.setPhone("+919999999999");
		user.setAge(27);
		return user;
	}

}
