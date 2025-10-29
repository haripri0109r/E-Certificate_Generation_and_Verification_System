package com.example.E_Certificate_Generation_and_Verification_System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_Certificate_Generation_and_Verification_System.entity.User;
import com.example.E_Certificate_Generation_and_Verification_System.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setRole(userDetails.getRole());
            return userRepository.save(user);
        }).orElseGet(() -> {
            // If user doesn't exist, create a new one with provided details
            User newUser = new User();
            newUser.setName(userDetails.getName());
            newUser.setEmail(userDetails.getEmail());
            newUser.setRole(userDetails.getRole());
            return userRepository.save(newUser);
        });
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }
}
