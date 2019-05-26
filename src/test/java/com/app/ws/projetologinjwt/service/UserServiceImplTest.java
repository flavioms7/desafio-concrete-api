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
        user.getLastLogin(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setToken("4155133e-c57f-43c5-9b4b-3c2c4fb70eae");


        "", ,"", LocalDate.now(), LocalDateTime.now(), LocalDateTime.now(), User., List < Phone > phones

    }


}
