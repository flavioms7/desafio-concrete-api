package com.app.ws.projetologinjwt.service.impl;

import com.app.ws.projetologinjwt.entities.User;
import com.app.ws.projetologinjwt.exceptions.EmailJaCadastradoException;
import com.app.ws.projetologinjwt.repository.UserRepository;
import com.app.ws.projetologinjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {


     User userObject = userRepository.findUserByEmail(user.getEmail()).orElse(null);

        if (userObject != null) {
            throw new EmailJaCadastradoException("E-mail já existente");
        } else {
            return userRepository.save(user);
        }
    }
}