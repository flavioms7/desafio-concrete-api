package com.app.ws.projetologinjwt.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.app.ws.projetologinjwt.dto.ProfileDTO;
import com.app.ws.projetologinjwt.dto.UserDTO;
import com.app.ws.projetologinjwt.entities.Phone;
import com.app.ws.projetologinjwt.exceptions.NaoAutorizadoException;
import com.app.ws.projetologinjwt.exceptions.SessaoInvalidaException;
import com.app.ws.projetologinjwt.exceptions.UsuarioSenhaInvalidosException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
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
	@Autowired
    private PhoneRepository phoneRepository;

	/**
	 * Cria o usuário na base de dados
	 *
	 * @param pUser
	 * @return
	 * @throws EmailJaCadastradoException
	 */
    public UserDTO createUser(User pUser) throws EmailJaCadastradoException {

     User user;
     UserDTO userDTO = new UserDTO();
     
     user = userRepository.findUserByEmail(pUser.getEmail()).orElse(null);

        if (user != null) {
            throw new EmailJaCadastradoException(HttpStatus.UNAUTHORIZED);
        }

	 pUser = this.getPasswordEncrypted(pUser);
	 pUser.setCreated(LocalDate.now());
	 pUser.setModified(LocalDateTime.now());
	 pUser.setLastLogin(LocalDateTime.now());
 	 pUser.generateToken();

	 user = userRepository.save(pUser);

	 //Atualiza os itens da tabela de Phones com a Chave Estrangeira
	 if (pUser.getPhones() != null && !pUser.getPhones().isEmpty()) {
		for (Phone phone : pUser.getPhones()) {
				phone.setUser(user);
		}
		phoneRepository.saveAll(pUser.getPhones());
	 }

		//Transfere os dados que são necessários serem retornados para o userDTO
	 	BeanUtils.copyProperties(user, userDTO);

		return userDTO;
    }

	/**
	 * - Realiza o login do usuário
	 *
	 * @param pLoginDTO
	 * @return
	 * @throws UsuarioSenhaInvalidosException
	 */
	public UserDTO login(LoginDTO pLoginDTO) throws UsuarioSenhaInvalidosException {

		Optional<User> userOptional = userRepository.findUserByEmail(pLoginDTO.getEmail());
		User user = userOptional.orElseThrow(() -> new UsuarioSenhaInvalidosException(HttpStatus.UNAUTHORIZED));

			if(!this.passwordAuthentication(pLoginDTO.getPassword(), user.getPassword())){

				throw new UsuarioSenhaInvalidosException(HttpStatus.UNAUTHORIZED);
			}

		user.setLastLogin(LocalDateTime.now());
		user.generateToken();

		userRepository.save(user);

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);

		return userDTO;
	}

	/**
	 * Acessa o perfil do usuário caso o usuário e senha estejam corretos
	 * @param pProfileDTO
	 * @return
	 * @throws NaoAutorizadoException
	 */
	public UserDTO profile(ProfileDTO pProfileDTO) throws NaoAutorizadoException {

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

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userOptionalId.get(), userDTO);

		return userDTO;
	}

	/**
	 * Realiza a criptografia do password informado pelo usuário
	 * @param pUser
	 * @return
	 */
	private User getPasswordEncrypted(User pUser){

	    //Gera um sal aleatório
	    String salt = BCrypt.gensalt();

	    //Gera a password hash utilizando o sal gerado
        String passwordHash = BCrypt.hashpw(pUser.getPassword(), salt);

        //Atualiza o password do usuário
        pUser.setPassword(passwordHash);

        return pUser;
    }

	/**
	 * Verifica se o password informado corresponde ao password em hash armazenado.
	 * @param passwordDTO
	 * @param userPassword
	 * @return
	 */
	private boolean passwordAuthentication(String passwordDTO, String userPassword){

        // Usa o BCrypt para verificar se a senha passada está correta.
        boolean isAuthenticationSuccessful = BCrypt.checkpw(passwordDTO, userPassword);

        return isAuthenticationSuccessful;
    }
}






