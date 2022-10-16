package com.acks.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.acks.model.User5;
@Component
public interface UserRepository extends JpaRepository<User5, Integer>{
	@Query("select u From User5 u where u.email= :email")
	public User5 getUserByName(@Param("email") String email);
  
}
