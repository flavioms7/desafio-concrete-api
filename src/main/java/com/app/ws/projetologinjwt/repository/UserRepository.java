package com.app.ws.projetologinjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ws.projetologinjwt.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
