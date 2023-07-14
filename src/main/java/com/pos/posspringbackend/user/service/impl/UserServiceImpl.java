package com.pos.posspringbackend.user.service.impl;

import com.pos.posspringbackend.user.entity.User;
import com.pos.posspringbackend.user.repository.UserRepository;
import com.pos.posspringbackend.user.service.UserService;
import com.pos.posspringbackend.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        User savedUser = User.builder()
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .tokens(new ArrayList<>())
                .build();
        return userRepository.save(savedUser);
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "User with id: " + id + " not found"
                )
        );
        user.setPassword("");
        user.setTokens(new ArrayList<>());
        return user;
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User savedUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "User with id " + id + " not found"
                )
        );
        savedUser.setPassword(user.getPassword());
        savedUser.setEmail(user.getEmail());
        savedUser.setRole(user.getRole());
        return userRepository.save(savedUser);
    }

    @Override
    public boolean delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
