package com.harman.um.dao;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.harman.um.model.UserDetails;
import com.harman.um.repo.UserDetailsRepo;

@RunWith(PowerMockRunner.class)

public class UserDetailsDaoImplTest {

	@Mock
	UserDetailsDaoImpl userDetailsDaoImpl;

	@MockBean
	UserDetailsRepo repository;

	@Before
	public void setup() {
		userDetailsDaoImpl = Mockito.spy(UserDetailsDaoImpl.class);
		repository = mock(UserDetailsRepo.class);
		userDetailsDaoImpl.setRepo(repository);

	}

	@Test
	public void getUserById() {
		UserDetails user = getUserObject();
		Optional<UserDetails> opt = Optional.of(user);
		Mockito.when(repository.findById(Long.parseLong("1"))).thenReturn(opt);
		Assert.assertEquals(userDetailsDaoImpl.getUserById(Long.parseLong("1")), user);
	}
	
	@Test 
	public void saveUserTest() {
		UserDetails user = getUserObject();
		Mockito.when(repository.save(user)).thenReturn(user);
		Assert.assertEquals(userDetailsDaoImpl.saveUser(user), user);
	}
	
	@Test
	public void getAllUsersTest(){
		List<UserDetails> userDetailsList =  new ArrayList<>();
		UserDetails user = getUserObject();
		userDetailsList.add(user);
		Mockito.when(repository.findAll()).thenReturn(userDetailsList);
		Assert.assertEquals(userDetailsDaoImpl.getAllUsers(), userDetailsList);
	}

	@Test
	public void getUsersByFilterTest(){
		List<UserDetails> userDetailsList =  new ArrayList<>();
		UserDetails user = getUserObject();
		userDetailsList.add(user);
		Mockito.when(repository.search("Lokesh")).thenReturn(userDetailsList);
		Assert.assertEquals(userDetailsDaoImpl.getUsersByFilter("Lokesh"), userDetailsList);
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
