package com.memcached.demo.learning;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;

@Service
@Slf4j
public class MemService {

    public void storeKeyValue(String key, String value) throws IOException {
        MemcachedClient mcc = new MemcachedClient(new
                InetSocketAddress("127.0.0.1", 11211));
        log.info("Connection to server sucessfully");

        boolean done = mcc.set(key, 900, value).isDone();
        log.info("isDone: "+done);
    }

    public Object getImportant(String key) throws IOException {
        MemcachedClient mcc = new MemcachedClient(new
                InetSocketAddress("127.0.0.1", 11211));
        return mcc.get(key);

    }
}
