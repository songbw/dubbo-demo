package com.flzc.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtil {

	public static final String cookieDomain = "18fanglian.com";
	
	protected static final Logger log = LoggerFactory.getLogger(CookieUtil.class);

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if ((cookies == null) || (cookies.length == 0) || (name == null) || (name.length() == 0)) {
			return null;
		}
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(name)) {
				cookie = cookies[i];
				return cookie;
			}
		}
		return null;
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		setCookie(request, response, name, value, cookieDomain, "/", -1);
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			int maxAge) {
		setCookie(request, response, name, value, cookieDomain, "/", maxAge);
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			String domain, String path, int maxAge) {
		if ((value == null) || (value.length() == 0)) {
			return;
		}

		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		if ((domain != null) && (domain.length() > 0)) {
			cookie.setDomain(domain);
		}
		log.info(cookie.getDomain() + cookie.getPath() + ":" + cookie.getName() + ":" + cookie.getValue());
		response.addCookie(cookie);
	}

	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String domain, String path) {
		if (name != null) {
			Cookie cookie = getCookie(request, name);
			if (cookie != null) {
				cookie.setPath(path);
				cookie.setValue(null);
				cookie.setMaxAge(0);
				if ((domain != null) && (domain.length() > 0)) {
					cookie.setDomain(domain);
				}
				response.addCookie(cookie);
				log.info("delete cookie name=" + name);
			}
		}
	}

	public static String getDomainName(HttpServletRequest request) {
		String domainName = request.getServerName();

		if (domainName != null) {
			if (!domainName.trim().toLowerCase().endsWith(Constants.BOOK_COOKIE_DOMAIN)) {
				int pos = domainName.indexOf(".");
				if (pos > 0) {
					domainName = domainName.substring(pos);
					return domainName;
				}
			} else {
				return Constants.BOOK_COOKIE_DOMAIN;
			}
		}

		return null;
	}
}
