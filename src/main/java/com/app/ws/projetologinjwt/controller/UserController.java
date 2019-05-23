package com.app.ws.projetologinjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ws.projetologinjwt.entities.User;
import com.app.ws.projetologinjwt.service.UserService;

@RestController()
@RequestMapping()
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		
		User responseUser = userService.createUser(user);
		
		return ResponseEntity.ok().body(responseUser);
		
	}
	
}
