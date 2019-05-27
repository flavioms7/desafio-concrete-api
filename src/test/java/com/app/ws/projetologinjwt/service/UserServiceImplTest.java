package com.app.ws.projetologinjwt.service;

import com.app.ws.projetologinjwt.dto.UserDTO;
import com.app.ws.projetologinjwt.entities.User;
import com.app.ws.projetologinjwt.repository.UserRepository;
import com.app.ws.projetologinjwt.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;


    //public void deveVerificarSeEmailExiste() {

        //String email = "joao@silva.org";

   // }

    @Test
    public void deveCriarUmUsuario() {

        UserDTO userDTO;

        User user = new User();
        user.setName("João da Silva");
        user.setEmail("joao@silva.org");
        user.setPassword("hunter2");
        user.setCreated(LocalDate.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.generateToken();

        userDTO = userServiceImpl.createUser(user);

        assertThat(userDTO).isNotNull();


    }


}
