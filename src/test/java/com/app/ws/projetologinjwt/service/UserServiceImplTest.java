package com.app.ws.projetologinjwt.service;

import com.app.ws.projetologinjwt.entities.Phone;
import com.app.ws.projetologinjwt.entities.User;
import com.app.ws.projetologinjwt.repository.UserRepository;
import com.app.ws.projetologinjwt.service.impl.UserServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    public void deveVerificarSeEmailExiste() {

        String email = "joao@silva.org";

    }

    public void deveCriarUmUsuario() {


        User user = new User();
        user.setName("João da Silva");
        user.setEmail("joao@silva.org");
        user.setPassword("hunter2");
        user.setCreated(LocalDate.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.generateToken();

        //when(userService.createUser(user)).thenReturn(user);
        //when(userRepository.findUserByEmail(user.getEmail()).thenReturn(user));





    }


}
