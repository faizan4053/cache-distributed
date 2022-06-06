package com.learning.reddis.repository;

import com.learning.reddis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY="user";

    @Override
    public boolean saveUser(User user) {
        try{
            redisTemplate.opsForHash().put(KEY,user.getId(),user);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return redisTemplate.opsForHash().values(KEY);
    }

    @Override
    public User getUserById(Long id) {
        User user;
         user=(User) redisTemplate.opsForHash().get(KEY,id);
        return user;
    }

    @Override
    public Long deleteUserById(Long id) {
        return redisTemplate.opsForHash().delete(KEY,id);
    }
}
