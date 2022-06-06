package com.learning.reddis.repository;

import com.learning.reddis.entity.User;

import java.util.List;

public interface UserRepository {
    boolean saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    Long deleteUserById(Long id);
}
