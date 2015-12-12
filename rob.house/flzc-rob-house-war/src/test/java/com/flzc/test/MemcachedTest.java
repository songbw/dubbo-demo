package com.flzc.test;

import com.flzc.base.util.Memcached;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.Calendar;
import java.util.concurrent.TimeoutException;

/**
 * Created by iverson on 2015/11/11.
 */
@RunWith(MyJunitSpringRunner.class)
@ContextConfiguration("classpath:config/spring/applicationContext.xml")
public class MemcachedTest {


    @Test
    public void test() throws InterruptedException, MemcachedException, TimeoutException {
        Memcached.set("testKey", "test", Calendar.MINUTE ,10);
        System.out.println(Memcached.get("testKey"));
    }

}
