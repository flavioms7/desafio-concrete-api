package com.app.ws.projetologinjwt.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ws.projetologinjwt.dto.LoginDTO;
import com.app.ws.projetologinjwt.entities.User;
import com.app.ws.projetologinjwt.exceptions.EmailJaCadastradoException;
import com.app.ws.projetologinjwt.repository.PhoneRepository;
import com.app.ws.projetologinjwt.repository.UserRepository;
import com.app.ws.projetologinjwt.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private PhoneRepository phoneRepository;

    /**
     * 
     */
    public User createUser(User pUser) throws EmailJaCadastradoException {

     User user = null;
     
     	user = userRepository.findUserByEmail(pUser.getEmail()).orElse(null);

        if (user != null) {
        	
            throw new EmailJaCadastradoException();
            
        } 
        	
        	pUser.setCreated(LocalDate.now());
        	pUser.setModified(LocalDateTime.now());
        	pUser.setLastLogin(LocalDateTime.now());
        	pUser.generateToken();
        	
        	user = userRepository.save(pUser);
        	
//        	if (pUser.getPhones() != null && !pUser.getPhones().isEmpty()) {
//    			for (Phone phone : pUser.getPhones()) {
//    				phone.setUser(user);
//    			}
//    			phoneRepository.saveAll(pUser.getPhones());
//    			
//        	}
        	
            return user;
    }

	@Override
	public User login(LoginDTO loginDTO) {
		
		User user = null;
		
		Optional<User> userAux = userRepository.findUserByEmail(loginDTO.getPassword());
		
		if(userAux !=  null){

        }
		
		
		
		return user;
	}
    
    
    
    
    
    
}






