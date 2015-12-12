package com.flzc.message.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.flzc.message.api.service.AuthenticateService;



/**
 * 发送email
 * 
 * @author chenqi
 * 
 */
@Service("authService")
public class AuthenticateEmailServiceImpl implements AuthenticateService {
	
	private final static String subject="房链众筹邮件";
//	private final static String sendAddress ="daied@thtim.com";
	private final static String sendAddress ="coolading@coolading.com";
	private final static String sendHost="smtp.exmail.qq.com";
//	private final static String sendHost="smtp.qq.com";
//	private final static String passWord="a123456";
	private final static String passWord="kulading123";
	private final static String contentType="text/html;charset=gb2312";
	private final static StringBuffer sbuffer1=new StringBuffer();
	private final static StringBuffer sbuffer2=new StringBuffer();
	
	private AuthenticateEmailServiceImpl(){
		sbuffer1.append("<h4>标题：房链众筹提醒<br/>");
		sbuffer1.append("亲爱的战友，<br/>");
		sbuffer1.append("以下是用户");
		
		sbuffer2.append("</h4>");
	}

	public void sendMessage(String email, String content) {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", sendHost);
		props.put("mail.smtp.auth", true);
		// 创建一个程序与邮件服务器的通信
		Session mailConnection = Session.getInstance(props);
		mailConnection.setDebug(true);//设置debug模式   在控制台看到交互信息
		
		//创建消息
		Message msg = new MimeMessage(mailConnection);
		try {
			//msg.setText(uuid);//设置简单的发送内容
			// 设置发送人和接受人
			Address sender = new InternetAddress(sendAddress);
			Address receiver = new InternetAddress(email);
			msg.setFrom(sender);
			msg.setRecipient(RecipientType.TO, receiver);
			msg.setSentDate(new Date()); 
			msg.setSubject(subject);  
			// 给消息对象设置内容 
			content = content+"<br/>";
			msg.setContent(sbuffer1.toString()+content+sbuffer2.toString(), contentType); 
		    msg.saveChanges(); 
			
		    Transport trans=mailConnection.getTransport("smtp");
            //邮件服务器名,用户名，密码
            trans.connect(sendHost,sendAddress,passWord);
            trans.sendMessage(msg, msg.getAllRecipients());
            trans.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
