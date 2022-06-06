package com.memcached.demo.learning;

import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class CacheService {
    public OperationFuture<Boolean> storeKeyValue(String key, Object value) throws IOException {
        MemcachedClient mcc = MemConnection.getConnectionObject();
        log.info("Connection to server sucessfully");
        OperationFuture<Boolean> done = mcc.set(key, 900, value);
        log.info("isDone: " + done);
        return done;
    }

    public Object getImportant(String key) throws IOException {
        MemcachedClient mcc = MemConnection.getConnectionObject();
        return mcc.get(key);
    }
}
