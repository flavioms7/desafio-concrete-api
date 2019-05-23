package com.app.ws.projetologinjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.ws.projetologinjwt.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
	
}
