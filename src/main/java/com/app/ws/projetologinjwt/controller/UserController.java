package com.app.ws.projetologinjwt.controller;

import com.app.ws.projetologinjwt.dto.LoginDTO;
import com.app.ws.projetologinjwt.dto.ProfileDTO;
import com.app.ws.projetologinjwt.entities.User;
import com.app.ws.projetologinjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) {

        User user = userService.login(loginDTO);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/profile")
    public ResponseEntity<User> profile(@RequestBody ProfileDTO profileDTO) {

        User user = userService.profile(profileDTO);

        return ResponseEntity.ok().body(user);
    }


}
