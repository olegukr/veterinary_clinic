package org.factoriaf5.vcp.services;

import java.util.List;
import java.util.Optional;

import org.factoriaf5.vcp.model.User;
import org.factoriaf5.vcp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
 
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
    
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new IllegalArgumentException("Usuario con ID " + id + " no encontrado");
        }
    }

    public User updateUser(Long id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
    
        if (userOptional.isPresent()) {
            User user = userOptional.get();
    
            if (userDetails.getUsername() != null && !userDetails.getUsername().equals(user.getUsername())) {
                if (userRepository.findByUsername(userDetails.getUsername()).isPresent()) {
                    throw new IllegalArgumentException("El nombre de usuario ya existe");
                }
                user.setUsername(userDetails.getUsername());
            }
    
            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                user.setPassword(userDetails.getPassword());
            }
    
            if (userDetails.getUsertype() != null) {
                user.setUsertype(userDetails.getUsertype());
            }
    
            if (userDetails.getPhone() != null) {
                user.setPhone(userDetails.getPhone());
            }
    
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Usuario con ID " + id + " no encontrado");
        }
    }
}
