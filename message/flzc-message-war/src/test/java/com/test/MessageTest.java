package com.test;

import com.flzc.message.api.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2015-8-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring-dubbo-consumer.xml"})
@ContextConfiguration("classpath:config/spring/applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class MessageTest {
    @Autowired
    private MessageService messageService;
    
    @Test
    public void testFind(){
        messageService.sendSystemMessage(null,null,null,null);
    }

}
