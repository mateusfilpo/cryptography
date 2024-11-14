package br.com.mateusfilpo.cryptography.service;

import br.com.mateusfilpo.cryptography.domain.User;
import br.com.mateusfilpo.cryptography.dto.UserDTO;
import br.com.mateusfilpo.cryptography.repository.UserRepository;
import br.com.mateusfilpo.cryptography.service.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário de id: " + id + " não existe"));
        return new UserDTO(user);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .toList();
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        user.setUserDocument(passwordEncoder.encode(user.getUserDocument()));
        user.setCreditCardToken(passwordEncoder.encode(user.getCreditCardToken()));

        return new UserDTO(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário de id: " + id + " não existe"));
        updateData(user, userDTO);
        userRepository.save(user);
        return new UserDTO(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Usuário de id: " + id + " não existe");
        }
        userRepository.deleteById(id);
    }

    private void updateData(User user, UserDTO userDTO) {
        if(userDTO.getUserDocument() != null) {
            user.setUserDocument(passwordEncoder.encode(userDTO.getUserDocument()));
        }
        if(userDTO.getCreditCardToken() != null) {
            user.setCreditCardToken(passwordEncoder.encode(userDTO.getCreditCardToken()));
        }
        if(userDTO.getValue() != null) {
            user.setValue(userDTO.getValue());
        }
    }
}
