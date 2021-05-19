package com.harman.um.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.harman.um.model.UserDetails;


public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {

	@Query("SELECT u FROM UserDetails u WHERE CONCAT(u.firstName, u.lastName, u.email, u.phone, u.age) LIKE %?1%")
	public List<UserDetails> search(String keyword);

}
