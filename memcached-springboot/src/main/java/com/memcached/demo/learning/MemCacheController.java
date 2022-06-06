package com.memcached.demo.learning;

import com.memcached.demo.learning.student.Student;
import com.memcached.demo.learning.student.StudentCacheService;
import net.spy.memcached.internal.OperationFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("memcache")
public class MemCacheController {

    @Autowired
    private StudentCacheService service;

    @Autowired
    private CacheService cacheService;

    @PostMapping("/{key}")
    public Mono<String> setKey(@PathVariable(value = "key") String key, @RequestBody Student spaceShip){
        System.out.println(spaceShip);
        Boolean res=null;
        try {
            res=service.storeKeySpaceShip(key,spaceShip);
        } catch (IOException e) {
            return Mono.just(e.getMessage());
        }
       String ans=res==true?"Data is cached successfully !":"Failed to cached the data !";
        return Mono.just(ans);
    }

    @GetMapping("/{key}")
    public Mono<Student> getKey(@PathVariable(value = "key") String key) throws IOException {
        return service.getSpaceShip(key);
    }

    @PostMapping("/v1/{key}")
    public Mono<String> setKeyInCache(@PathVariable(value = "key") String key, @RequestBody Student student) throws ExecutionException, InterruptedException {
        System.out.println(student);
        Map<String,String> map=new HashMap<>();
        map.put("name",student.getName());
        map.put("id",student.getId());
        OperationFuture<Boolean> res=null;
        try {
            res=cacheService.storeKeyValue(key,map);
        } catch (IOException e) {
            return Mono.just(e.getMessage());
        }
//        System.out.println(res.get());
//        System.out.println(res.getKey());
        System.out.println(res.getStatus());
//        System.out.println(res.isCancelled());
        var status=res.getStatus();
        System.out.println(status.getMessage() + " " + status.getStatusCode() + " " +status.isSuccess());
        String ans=res.isDone()==true?"Data is cached successfully !":"Failed to cached the data !";
        return Mono.just(ans);
    }

    @GetMapping("/v1/{key}")
    public Mono<Object> getKeyFromCache(@PathVariable(value = "key") String key) throws IOException {
        Map<String,String> map= (Map<String, String>) cacheService.getImportant(key);
        System.out.println(map);
        return Mono.just(map);
    }

}
