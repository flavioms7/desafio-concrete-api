package com.app.ws.projetologinjwt.service;

import com.app.ws.projetologinjwt.dto.LoginDTO;
import com.app.ws.projetologinjwt.entities.User;

public interface UserService {

	User createUser(User user);
	
	User login(LoginDTO loginDTO);
	
}
