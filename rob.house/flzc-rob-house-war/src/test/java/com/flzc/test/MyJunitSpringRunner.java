package com.flzc.test;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

/**
 * Created by iverson on 2015/10/14.
 */
public class MyJunitSpringRunner extends SpringJUnit4ClassRunner{
    static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j-----------------");
        }
    }

    public MyJunitSpringRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }
}
