package com.flzc.message.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringTools extends StringUtils {

	public static void main(String[] args) {
		System.out.println(replaceLast(",,,,,,,,,.","," ,"-" ));
	}
	
	/**
	 * replaceLast(",,,,,,,,,.","," ,"-" )	
	 * 
	 * @param text
	 * @param regex
	 * @param replacement
	 * @return
	 */
	public static String replaceLast(String text, String regex,
			String replacement) {
		return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")",replacement);
	}

	public static final String URLREGEX = "(http|https|www|ftp|)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?"; // url正则

	/**
	 * 转换数字字符串,转换失败则返回默认值def
	 * 
	 * @param s
	 * @param def
	 *            默认值
	 * @return
	 */
	public static int parseInt(String s, int def) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return def;
		}
	}

	/**
	 * 通过标签进行格式化
	 * 
	 * @param format
	 *            如a{b}c
	 * 
	 * @param values
	 * @return
	 */
	public static String formatTag(String format,
			Map<String, ? extends Object> values) {
		for (Entry<String, ? extends Object> entry : values.entrySet()) {
			while (format.indexOf("{" + entry.getKey() + "}") != -1) {
				format = format.replace("{" + entry.getKey() + "}", String
						.valueOf(entry.getValue()));
			}
		}
		return format;
	}

	/**
	 * 标签格式化工具
	 * 
	 * @author Bevis.Zhao
	 * @time Jun 24, 20117:32:03 PM
	 */
	public static class TagFormatter {
		private final String format;
		private Map<String, Object> values;

		public TagFormatter(String format) {
			this.format = format;
			values = new HashMap<String, Object>();
		}

		public TagFormatter add(String tag, Object value) {
			values.put(tag, value);
			return this;
		}

		public String format() {
			return formatTag(format, values);
		}
	}

	/**
	 * 获得给定的字符中包含的字符(用于过滤话题,不包含空字符) 例如: splitBetween("你好,今天#天气#不错呀!", "#"); 返回:
	 * 天气
	 * 
	 * @param target
	 *            目标字符串
	 * @param separator
	 *            分隔符
	 * @param repeat
	 *            是否过滤重复值 true 为过滤
	 * @return
	 */
	public static List<String> splitBetween(String target, String separator,
			final boolean repeat) {
		List<String> result = new ArrayList<String>();
		if (StringTools.isNotEmpty(separator)) {
			Pattern pattern = Pattern.compile(separator + "[^" + separator
					+ "]+" + separator);
			Matcher matcher = pattern.matcher(target);
			while (matcher.find()) {
				String str = matcher.group();
				str = StringTools.replace(str, separator, "");// 去除分隔符
				if (StringTools.isNotBlank(str)) {
					str = transaferHTML(str);
					// 是否过滤重复值
					if (repeat) {
						if (!result.contains(str)) {
							result.add(str);
						}
					} else {
						result.add(str);
					}
				}
			}
		}
		return result;
	}

	public static List<String> splitBetween(String target, String start,
			String end) {
		List<String> result = Collections.emptyList();
		if (StringTools.isNotEmpty(start) && StringTools.isNotEmpty(end)) {
			result = new ArrayList<String>();
			Pattern pattern = Pattern.compile(String.format("%s[^%s]+%s",
					start, end, end));
			Matcher matcher = pattern.matcher(target);
			while (matcher.find()) {
				String str = matcher.group();
				str = StringTools.replace(str, start, "");// 去除分隔符
				str = StringTools.replace(str, end, "");// 去除分隔符
				result.add(str);
			}
		}
		return result;
	}

	/**
	 * 字符串中字符出现的次数
	 * 
	 * @param s
	 * @return
	 */
	public static int existNumber(String str, String s) {
		int number = 0;
		if (!isEmpty(str) && !isEmpty(s)) {
			int oldLength = str.length();
			String newStr = str.replaceAll(s, "");
			int newLength = newStr.length();
			number = oldLength - newLength;
		}
		return number;
	}

	/**
	 * 给str长度为2的中间加空格
	 */
	public static String strNbspStr(String str) {
		StringBuilder strN = new StringBuilder();

		if (str.length() == 2) {
			strN.append(str.charAt(0)).append("　").append(str.charAt(1));
		} else
			strN.append(str);

		return strN.toString();
	}

	/**
	 * 转换字符串为数值
	 * 
	 * @param <T>
	 * @param value
	 * @param def
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T parseNumber(String value, T def) {
		String methodName = null;
		Class<T> type = (Class<T>) def.getClass();
		if (type == Integer.class)
			methodName = "parseInt";
		else
			methodName = "parse" + type.getSimpleName();
		try {
			Method parseMethod = type.getDeclaredMethod(methodName,
					String.class);
			return (T) parseMethod.invoke(type, value);
		} catch (Throwable e) {
			return (T) def;
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            str为null或""字符串时返回true, "  "字符串且trim为true时返回true,其他情况返回false
	 * @param trim
	 *            是否对字符串进行trim
	 * @return
	 */
	public static boolean isEmpty(String str, boolean trim) {

		if (!trim)
			return isEmpty(str);
		if (str == null || str.trim().length() == 0)
			return true;

		return false;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (c[i] / 0x80 != 0)
				len++;
		}
		return len;
	}

	/**
	 * 将HTML标记转换为文本
	 * 
	 * @param html
	 * @return
	 */
	public static String transaferHTML(String html) {
		if (html == null)
			return "";
		return html.replace("<", "&lt;").replace(">", "&gt;");
	}

	/**
	 * 去掉HTML标记
	 * 
	 * @param html
	 * @return
	 */
	public static String getHTMLText(String html) {
		if (html == null)
			return "";
		return html.replaceAll("<[^>]*>", "").replace("&nbsp;", " ");
	}

	/**
	 * 截取html 大于html内容长度 连接regix
	 * 
	 * @param html
	 *            html内容
	 * @param regix
	 *            连接符
	 * @param length
	 *            要求长度
	 * @return
	 */
	public static String getPartText(String html, String regix, int length) {
		if (html == null)
			return "";
		html = StringTools.trimToEmpty(html);
		int htmlLength = html.length();
		if (htmlLength > length) {
			String content = html.substring(0, length);
			return content.concat(regix);
		} else {
			return html;
		}
	}

	/**
	 * 分割字符串 返回集合
	 * 
	 * @param s
	 *            需要分割的字符串
	 * @param c
	 *            分割符
	 * @param trim
	 *            是否trim
	 * @return
	 * 
	 * @author Bevis.Zhao
	 */
	public static List<String> split(String s, String c, boolean trim) {
		if (s == null) {
			return new ArrayList<String>(0);
		}
		return Arrays.asList(s.trim().split(c));
	}

	/**
	 * 使用字节数进行截取并join
	 * 
	 * @param s
	 *            字符串
	 * @param length
	 *            字节长度
	 * @param join
	 *            间隔符
	 * @param ignore
	 *            如果最后由于双字节字符导致超长，是否忽略
	 * @return
	 */
	public static String splitJoin(String s, int length, String join,
			boolean ignore) {
		StringBuilder builder = new StringBuilder(s.length());
		while (s.length() != 0) {
			String current = realsubstring(s, length, ignore);
			builder.append(current);
			s = s.substring(current.length());
			if (s.length() != 0) {
				builder.append(join);
			}
		}
		return builder.toString();
	}

	/**
	 * 使用字节数进行截取并join {@code splitJoin(s, length, join, true)}
	 * 
	 * @param s
	 *            字符串
	 * @param length
	 *            字节长度
	 * @param join
	 *            间隔符
	 * 
	 * @return
	 */
	public static String splitJoin(String s, int length, String join) {
		return splitJoin(s, length, join, true);
	}

	/**
	 * 截取字符串，以字节截取。 {@code return realsubstring(s, length, true)}
	 * 
	 * @param s
	 * @param length
	 * @return
	 */
	public static String realsubstring(String s, int length) {
		return realsubstring(s, length, true);
	}

	/**
	 * 截取字符串，以字节截取，如果截取的最后一个字符为双字节，根据ignore决定是否省略
	 * 
	 * @param s
	 * @param length
	 * @param ignore
	 * @return
	 */
	public static String realsubstring(String s, int length, boolean ignore) {
		char[] c = s.toCharArray();
		StringBuilder builder = new StringBuilder();
		int len = 0;
		for (int i = 0; i < length && i < c.length; i++) {
			if (len < length) {
				if (c[i] / 0x80 == 0) { // 单字节
					builder.append(c[i]);
					len++;
				} else { // 双字节
					if (len + 2 <= length || !ignore) {
						builder.append(c[i]);
						len += 2;
					} else {
						break;
					}
				}
			}
		}
		return builder.toString();
	}

	/**
	 * 查看是否有重复字符串
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean duplicateLan(String... strs) {
		Set<String> set = new HashSet<String>();
		for (String str : strs) {
			set.add(str);
		}
		if (set.size() < strs.length)
			return true;
		return false;
	}

	/**
	 * 使用URLEncode进行encode
	 * 
	 * @param str
	 * @param enc
	 * @return
	 */
	public static String encode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * 使用URLEncode进行encode
	 * 
	 * @param str
	 * @param enc
	 * @return
	 */
	public static String decode(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * 验证字符串是否符合邮箱格式
	 * 
	 * @return
	 */
	public static boolean isEmail(String email) {
		return Pattern
				.compile("^[a-zA-Z0-9]+((\\.|\\-|\\_)?[a-zA-Z0-9]+)+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,}){1,})$").matcher(email).matches();
	}

	/**
	 * 获取字符串实际长度
	 * 
	 * @param s
	 * @return
	 */
	public static int getRealength(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (c[i] / 0x80 != 0)
				len++;
		}
		return len;
	}

	/**
	 * 密码评级 （定义：数字=A，英文=B，特殊字符=C） 弱：A|B|C 6位以下AB|AC|BC 中：10位以下AB|AC|BC 或6位以下ABC
	 * 强：10位以上AB|AC|BC 或ABC
	 * 
	 * @param password
	 * @return
	 */
	public static int getPasswordRate(String password) {
		char[] chars = password.toCharArray();
		// 数量统计[A, B, C]
		int count[] = new int[] { 0, 0, 0 };
		for (char c : chars) {
			if (c >= '0' && c <= '9') {
				count[0]++;
			} else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'z')) {
				count[1]++;
			} else {
				count[2]++;
			}
		}
		int types = 0;
		// 统计样式数量
		for (int type : count) {
			if (type != 0) {
				types++;
			}
		}

		if (types <= 1) {
			return -1;
		} else if (types == 2) {
			if (chars.length < 6) {
				return -1;
			}
			if (chars.length < 10) {
				return 0;
			} else {
				return 1;
			}
		} else {
			if (chars.length < 6) {
				return 0;
			} else {
				return 1;
			}
		}

	}
	public static boolean patternParam(String str, String paramValue){
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(paramValue);
		if(m.find()){
			return true;
		}else{
			return false;
		}
	} 
}
