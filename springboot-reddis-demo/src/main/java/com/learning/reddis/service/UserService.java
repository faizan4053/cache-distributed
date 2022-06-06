package com.learning.reddis.service;

import com.learning.reddis.entity.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);

    List<User> getAllUser();

    User getUserById(Long id);

    Long deleteUserById(Long id);
}
