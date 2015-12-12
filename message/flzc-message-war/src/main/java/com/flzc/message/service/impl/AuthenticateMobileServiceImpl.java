package com.flzc.message.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;

import com.flzc.message.api.service.AuthenticateService;

/**
 * 发送手机短信
 * @author bing.xiao
 *
 */
@Service("authenticateService")
public class AuthenticateMobileServiceImpl implements AuthenticateService {

	// private final static String corpID="TCLKJ02226";
	// private final static String passWord ="15330144182";

//	private final static String corpID = "tclkj03746";
//
//	private final static String passWord = "coolading@";
	
	private final static String corpID = "tclkj03507";
	
	private final static String passWord = "FLZC1106";

	public void sendMessage(String mobile, String content) {

		try {
			sendMobileMessage(mobile, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void sendMobileMessage(String mobile, String content)
			throws MalformedURLException, UnsupportedEncodingException {

		System.out.println("message content is :" + content);
		
		URL url = null;
		String conetent = "message 【房链众筹】";
		conetent = conetent.replace("message", content);
		conetent = URLEncoder.encode(conetent.replaceAll("<br/>", " "), "GBK");// 发送内容
		System.out.println(conetent);
		url = new URL("  http://inolink.com/WS/BatchSend.aspx?CorpID=" + corpID + "&Pwd=" + passWord + "&Mobile="
				+ mobile + "&Content=" + conetent + "&Cell=&SendTime=" + "");
		BufferedReader in = null;
		int inputLine = 0;
		try {
			System.out.println("开始发送短信手机号码为 ：" + mobile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			// System.out.println(in.readLine());
			inputLine = new Integer(in.readLine()).intValue();
			System.out.println(inputLine);
		} catch (Exception e) {
			System.out.println("网络异常,发送短信失败！");
			inputLine = -2;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	public boolean authentication(String authenticationText) {

		// TODO Auto-generated method stub
		return false;
	}

}
