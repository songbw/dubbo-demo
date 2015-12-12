package com.flzc.service.service.impl;

import com.flzc.service.service.AuthenticateService;
import com.flzc.service.util.AuthenticateUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 通过手机短信验证
 * @author chenqi
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

	public String creatCaptcha(String mobileCaptcha) {

		String authenticationText = AuthenticateUtil.creatMobileText();
		System.out.println("生成手机验证码");
		try {
			sendMobileText(mobileCaptcha, authenticationText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authenticationText;
	}

	private static void sendMobileText(String mobile, String authenticationText)
			throws MalformedURLException, UnsupportedEncodingException {

		System.out.println("authenticationText is :" + authenticationText);
		
		String conetent = "您在房链众筹的短信验证码是：yzm 【房链众筹】";
		URL url = null;
		conetent = conetent.replace("yzm", authenticationText);
		conetent = URLEncoder.encode(conetent.replaceAll("<br/>", " "), "GBK");// 发送内容
		System.out.println(conetent);
		url = new URL("  http://inolink.com/WS/BatchSend.aspx?CorpID=" + corpID + "&Pwd=" + passWord + "&Mobile="
				+ mobile + "&Content=" + conetent + "&Cell=&SendTime=" + "");
		BufferedReader in = null;
		int inputLine = 0;
		try {
			System.out.println("开始发送短信手机号码为 ：" + mobile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = new Integer(in.readLine()).intValue();
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

	public void sendMessage(String mobileCaptcha, String conent) {

		try {
			sendMobileMessage(mobileCaptcha, conent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void sendMobileMessage(String mobile, String content)
			throws MalformedURLException, UnsupportedEncodingException {

		URL url = null;
		content = URLEncoder.encode(content.replaceAll("<br/>", " "), "GBK");// 发送内容
		System.out.println(content);
		url = new URL("  http://inolink.com/WS/BatchSend.aspx?CorpID=" + corpID + "&Pwd=" + passWord + "&Mobile="
				+ mobile + "&Content=" + content + "&Cell=&SendTime=" + "");
		BufferedReader in = null;
		int inputLine = 0;
		try {
			System.out.println("开始发送短信手机号码为 ：" + mobile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			// System.out.println(in.readLine());
			inputLine = new Integer(in.readLine()).intValue();
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
