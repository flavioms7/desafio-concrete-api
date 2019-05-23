package com.app.ws.projetologinjwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ws.projetologinjwt.entities.User;
import com.app.ws.projetologinjwt.repository.UserRepository;
import com.app.ws.projetologinjwt.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {
		
		return userRepository.save(user);
	}

}
