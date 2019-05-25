package com.app.ws.projetologinjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.ws.projetologinjwt.entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	 Optional<User> findUserByEmail(String email);
	 
	 Optional<User> findByToken(UUID token);

    //@Query("SELECT u FROM User u where u.token = :token")
    //public Optional<User> findByToken(@Param("token") UUID token);

	 Optional<User> findById(UUID id);
}

