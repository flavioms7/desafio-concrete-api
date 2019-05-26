package com.app.ws.projetologinjwt.service;

import com.app.ws.projetologinjwt.dto.LoginDTO;
import com.app.ws.projetologinjwt.dto.ProfileDTO;
import com.app.ws.projetologinjwt.dto.UserDTO;
import com.app.ws.projetologinjwt.entities.User;

public interface UserService {

	UserDTO createUser(User pUser);
	
	UserDTO login(LoginDTO pLoginDTO);

	UserDTO profile(ProfileDTO pProfileDTO);
	
}
