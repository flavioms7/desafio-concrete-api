package com.app.ws.projetologinjwt.service;

import com.app.ws.projetologinjwt.dto.LoginDTO;
import com.app.ws.projetologinjwt.dto.ProfileDTO;
import com.app.ws.projetologinjwt.entities.User;

public interface UserService {

	User createUser(User pUser);
	
	User login(LoginDTO pLoginDTO);

	User profile(ProfileDTO pProfileDTO);
	
}
