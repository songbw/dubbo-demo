package com.flzc.base.aop;

import com.flzc.base.util.Memcached;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by iverson on 2015/11/11.
 */

public class MemCacheInit {

    private MemcachedClient memcachedClient;

    public void init(){
        if (memcachedClient==null){
             throw new RuntimeException("memcach缓存初始化失败");
        }
       Memcached.setMemcached(memcachedClient);
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }
}
