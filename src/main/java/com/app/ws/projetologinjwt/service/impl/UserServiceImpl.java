package com.app.ws.projetologinjwt.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.app.ws.projetologinjwt.dto.ProfileDTO;
import com.app.ws.projetologinjwt.exceptions.NaoAutorizadoException;
import com.app.ws.projetologinjwt.exceptions.SessaoInvalidaException;
import com.app.ws.projetologinjwt.exceptions.UsuarioSenhaInvalidosException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	 * @param pUser
	 * @return
	 * @throws EmailJaCadastradoException
	 */
    public User createUser(User pUser) throws EmailJaCadastradoException {

     User user = null;
     
     	user = userRepository.findUserByEmail(pUser.getEmail()).orElse(null);

        if (user != null) {
        	
            throw new EmailJaCadastradoException(HttpStatus.UNAUTHORIZED);
            
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

	/**
	 * -
	 *
	 * @param pLoginDTO
	 * @return
	 * @throws UsuarioSenhaInvalidosException
	 */
	public User login(LoginDTO pLoginDTO) throws UsuarioSenhaInvalidosException {

		Optional<User> userOptional = userRepository.findUserByEmail(pLoginDTO.getEmail());

		User user = userOptional.orElseThrow(() -> new UsuarioSenhaInvalidosException(HttpStatus.UNAUTHORIZED));

		if(!pLoginDTO.getPassword().equals(user.getPassword())){

			throw new UsuarioSenhaInvalidosException(HttpStatus.UNAUTHORIZED);
		}

		user.setLastLogin(LocalDateTime.now());
		user.generateToken();

		userRepository.save(user);
		
		return user;
	}

	@Override
	public User profile(ProfileDTO pProfileDTO) {

		Optional<User> userOptionalToken = userRepository.findByToken(UUID.fromString(pProfileDTO.getToken()));
			userOptionalToken.orElseThrow(() -> new NaoAutorizadoException(HttpStatus.UNAUTHORIZED));

			Optional<User> userOptionalId = userRepository.findById(UUID.fromString(pProfileDTO.getIdUser()));
			if(userOptionalId.isPresent()){

			    UUID token = userOptionalId.get().getToken();
			    UUID tokenProfile = UUID.fromString(pProfileDTO.getToken());

				if(!tokenProfile.equals(token)){
					throw new NaoAutorizadoException(HttpStatus.UNAUTHORIZED);
				}else if(LocalDateTime.now().minusMinutes(30).compareTo(userOptionalId.get().getLastLogin()) > 0){

					throw new SessaoInvalidaException(HttpStatus.GONE);
				}
			}else{

				throw new NaoAutorizadoException(HttpStatus.UNAUTHORIZED);
			}

		return userOptionalId.get();
	}

}






