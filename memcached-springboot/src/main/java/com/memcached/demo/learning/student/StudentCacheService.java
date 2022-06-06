package com.memcached.demo.learning.student;

import com.memcached.demo.learning.MemConnection;
import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
@Slf4j
public class StudentCacheService {

    public boolean storeKeySpaceShip(String key, Student value) throws IOException {
        MemcachedClient mcc = MemConnection.getConnectionObject();
//                new MemcachedClient(new
//                InetSocketAddress("127.0.0.1", 11211));
        log.info("Connection to server sucessfully");
         var done=mcc.set(key, 900, value).isDone();
        log.info("Is done:" + done);
        return done;
    }

//    public SpaceShip getSpaceShip(String key) throws IOException {
//        MemcachedClient mcc =MemConnection.getConnectionObject();
////                new MemcachedClient(new
////                InetSocketAddress("127.0.0.1", 11211));
//        return (SpaceShip)mcc.get(key);
//

    public Mono<Student> getSpaceShip(String key) throws IOException {
        MemcachedClient mcc =MemConnection.getConnectionObject();
//                new MemcachedClient(new
//                InetSocketAddress("127.0.0.1", 11211));
        Student spaceShip= (Student)mcc.get(key);
        return Mono.just(spaceShip);
    }
}
