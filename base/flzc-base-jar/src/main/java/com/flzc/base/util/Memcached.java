package com.flzc.base.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.TextCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.SerializingTranscoder;
import net.rubyeye.xmemcached.transcoders.Transcoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class Memcached {
	private static MemcachedClient client = null;
	@SuppressWarnings("rawtypes")
	private static Transcoder transcoder = null;
	private static long maxwaittime = 4000L;

	public static boolean keyExists(String key) {
		if (key == null)
			return false;
		try {
			return client.get(key) != null;
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static Object get(String key) {
		if (key == null)
			return null;
		try {
			return client.get(key, maxwaittime, transcoder);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, Object> get(List<String> keys) {
		if ((keys == null) || (keys.size() < 1))
			return null;
		try {
			return client.get(keys, maxwaittime);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static boolean set(String key, Object value) {
		if (value == null)
			return false;
		try {
			return client.set(key, 0, value, transcoder, maxwaittime);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean remove(String key) {
		if (key == null)
			return false;
		try {
			return client.delete(key, maxwaittime);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean set(String key, Object value, Date expire) {
		if (value == null)
			return false;
		try {
			return client.set(key, (int) ((expire.getTime() - new Date().getTime()) / 1000L), value, transcoder,
					maxwaittime);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean set(String key, Object value, int calendar, int time) {
		if (value == null)
			return false;
		Calendar expire = Calendar.getInstance();
		expire.add(calendar, time);
		try {
			return client.set(key, (int) ((expire.getTime().getTime() - new Date().getTime()) / 1000L), value,
					transcoder, maxwaittime);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void flushAll() {
		try {
			client.flushAll();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	public static long incr(String key, int delta) {
		try {
			long result = client.incr(key, delta);
			if (result == 0L) {
				set(key, Integer.valueOf(delta));
				return 1L;
			}
			return result;
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return 0L;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void touch(String key, int calendar, int time) {
		if (get(key) == null)
			return;
		Calendar expire = Calendar.getInstance();
		expire.add(calendar, time);
		try {
			client.cas(key, (int) ((expire.getTime().getTime() - new Date().getTime()) / 1000L), new CASOperation() {
				public int getMaxTries() {
					return 3;
				}
				public Object getNewValue(long currentCAS, Object currentValue) {
					return currentValue;
				}
			});
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	public static boolean add(String key, Object data, int calendar, int time)
			throws TimeoutException, InterruptedException, MemcachedException {
		Calendar expire = Calendar.getInstance();
		expire.add(calendar, time);
		return client.add(key, (int) ((expire.getTime().getTime() - new Date().getTime()) / 1000L), data);
	}

	public static MemcachedClient getMemcached() {
		return client;
	}

	public static void main(String[] args) {
		try {
			get("user.userid.17");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void setMemcached(MemcachedClient client) {
		Memcached.client = client;
		transcoder = client.getTranscoder();
	}
}