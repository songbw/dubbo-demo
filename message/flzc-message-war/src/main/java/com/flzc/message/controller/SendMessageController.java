/**
 * 
 */
package com.flzc.message.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flzc.message.api.service.AuthenticateService;
import com.flzc.message.api.service.MessageService;

/** 
 *
 * @ClassName: SendMessageController 
 * @Description: TODO
 * @author bing.xiao
 * @date 2015-9-6 下午7:32:01 
 *  
 */
@Controller
@RequestMapping(value="message")
public class SendMessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private AuthenticateService authenticateService;
	
	@RequestMapping(value="")
	@ResponseBody
	public String message(HttpServletRequest request, HttpServletResponse response){
		//messageService.sendSystemMessage(RecipientMessageType.RECIPIENT_BROKER.getCode(), "9d52f7975915434eb0fcf447b4e4355a", "120", "你好啊");
		
		authenticateService.sendMessage("18514084395", "测试短信");
		//messageService.sendSystemMessage(RecipientMessageType.RECIPIENT_CONSUMER.getCode(), "eb6bd9ba62464d3ca92936f46789086a", "120", "你好啊");
		/*String template;
		try {
			template = this.messageService.queryTemplateByCode("120");
			messageService.sendSystemMessage(com.flzc.message.api.common.RecipientMessageType.RECIPIENT_BROKER.getCode(), "9d52f7975915434eb0fcf447b4e4355a", "120", template);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return "send success!";
	}
	
	@RequestMapping(value="custom")
	@ResponseBody
	public String custom(HttpServletRequest request, HttpServletResponse response){
		List<String> recUserIds = new ArrayList<>();
		recUserIds.add("19");
		recUserIds.add("21");
		messageService.sendCustomMessage(1, recUserIds);
		return "send success!";
	}
}
