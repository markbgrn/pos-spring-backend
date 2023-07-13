package com.pos.posspringbackend.user.service;

import com.pos.posspringbackend.user.entity.User;

public interface UserService {
    User createUser(User user);
    User findById(Long id);
    User update(Long id, User user);
    boolean delete(Long id);
}
