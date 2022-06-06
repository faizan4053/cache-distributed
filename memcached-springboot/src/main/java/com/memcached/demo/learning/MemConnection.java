package com.memcached.demo.learning;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;

@Slf4j
public class MemConnection {

    private static  MemcachedClient client=null;

    private MemConnection(){
        try {
            client=new MemcachedClient(new InetSocketAddress("127.0.0.1",11211));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static MemcachedClient getConnectionObject(){
        if(client==null)
            new MemConnection();
        return client;
    }
}
