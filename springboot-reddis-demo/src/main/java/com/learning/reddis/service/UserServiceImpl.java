package com.learning.reddis.service;

import com.learning.reddis.entity.User;
import com.learning.reddis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public boolean saveUser(User user) {
        return repository.saveUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return repository.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return repository.getUserById(id);
    }

    @Override
    public Long deleteUserById(Long id) {
        return repository.deleteUserById(id);
    }
}
