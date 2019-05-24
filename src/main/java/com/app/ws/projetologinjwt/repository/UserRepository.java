package com.app.ws.projetologinjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ws.projetologinjwt.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	 Optional<User> findUserByEmail(String email);
	
}
