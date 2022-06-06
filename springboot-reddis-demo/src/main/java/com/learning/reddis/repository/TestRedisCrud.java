package com.learning.reddis.repository;

import com.learning.reddis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class TestRedisCrud {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY="user";
    private static final String k="cache";

    public boolean save(String key,String value) {
        try{
            redisTemplate.opsForHash().put(k,key,value);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    public String get(String key) {
        return (String) redisTemplate.opsForHash().get(k,key);
    }

//    public User getUserById(Long id) {
//        User user;
//        user=(User) redisTemplate.opsForHash().get(KEY,id);
//        return user;
//    }

    public Long deleteUserById(String key) {
        return redisTemplate.opsForHash().delete(KEY,key);
    }


    public boolean save1(String cache,String key,String value) {
        try{
            redisTemplate.opsForHash().put(cache,key,value);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    public String get1(String cache,String key) {
        return (String) redisTemplate.opsForHash().get(cache,key);
    }

//    public User getUserById(Long id) {
//        User user;
//        user=(User) redisTemplate.opsForHash().get(KEY,id);
//        return user;
//    }

    public Long deleteUserById1(String cache,String key) {
        return redisTemplate.opsForHash().delete(cache,key);
    }

    public List<String> getAll(String cache){
        return redisTemplate.opsForHash().values(cache);
    }
}
