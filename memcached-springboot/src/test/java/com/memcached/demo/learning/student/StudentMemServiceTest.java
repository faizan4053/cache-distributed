package com.memcached.demo.learning.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class StudentMemServiceTest {

    @Autowired
    StudentCacheService spaceshipMemService;

    @Test
    public void storeKeySpaceShip() throws IOException {
        String key = "student1";
        spaceshipMemService.storeKeySpaceShip(key, new Student("Faizan", "12"));
        Student student = spaceshipMemService.getSpaceShip(key).block();
        System.out.println("Student: " + student);
    }
}