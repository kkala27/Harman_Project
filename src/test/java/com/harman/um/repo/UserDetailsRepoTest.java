package com.harman.um.repo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.harman.um.model.UserDetails;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDetailsRepoTest {

	@Autowired
	UserDetailsRepo repository;

	@Test
	public void testRepository() {
		UserDetails user = new UserDetails();
		user.setFirstName("Lokesh");
		user.setLastName("Gupta");
		user.setEmail("howtodoinjava@gmail.com");

		repository.save(user);

		Assert.assertNotNull(user.getId());
	}

}
