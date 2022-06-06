package com.memcached.demo.learning;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemServiceTest {

    @Autowired
    MemService memService;

    @Test
    public void storeImportantStuff() throws IOException {
        String important = "The meaning of life is 42";
        String key = "mysecret";
        memService.storeKeyValue(key, important);
        Object fetchedValue = memService.getImportant(key);
        Assertions.assertEquals(important, fetchedValue);
        Assertions.assertNull(memService.getImportant("doesnotexist"));
        System.out.println("The fetched value: " + fetchedValue);
    }
}