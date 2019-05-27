package com.app.ws.projetologinjwt.controller;

import com.app.ws.projetologinjwt.dto.LoginDTO;
import com.app.ws.projetologinjwt.dto.ProfileDTO;
import com.app.ws.projetologinjwt.dto.UserDTO;
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

    @PostMapping("/cadastro")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        UserDTO responseUser = userService.createUser(user);

        return ResponseEntity.ok().body(responseUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {

        UserDTO userDTO = userService.login(loginDTO);

        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping("/perfil")
    public ResponseEntity<UserDTO> profile(@RequestBody ProfileDTO profileDTO) {

        UserDTO userDTO = userService.profile(profileDTO);

        return ResponseEntity.ok().body(userDTO);
    }
}
