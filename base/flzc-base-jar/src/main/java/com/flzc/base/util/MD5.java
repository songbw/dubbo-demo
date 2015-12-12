package com.flzc.base.util;

import java.security.MessageDigest;

public class MD5 {
	
	public static String encryptToMD5(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes("UTF-8"));
			return byte2hex(md5.digest());
		} catch (Exception e) {
		}
		return str;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + "";
		}
		return hs;
	}
	
	public static void main(String[] args) {

		System.out.println("=== " + MD5.encryptToMD5("123456"));
	}
}