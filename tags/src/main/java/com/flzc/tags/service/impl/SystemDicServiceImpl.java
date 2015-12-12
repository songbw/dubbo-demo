package com.flzc.tags.service.impl;

import com.flzc.base.service.impl.BaseServiceImpl;
import com.flzc.base.util.Memcached;
import com.flzc.tags.bean.SystemDic;
import com.flzc.tags.service.SystemDicService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SystemDicServiceImpl extends BaseServiceImpl implements SystemDicService {
	public SystemDic getSystemDic(String nodeCode) {
		SystemDic items = getCacheSingle(nodeCode);

		if (items != null) {
			return items;
		}

		items = findSystemDicByCode(nodeCode);

		Memcached.set("dic.list.all" + nodeCode, items);
		return items;
	}

	public List<SystemDic> getSystemDics(String nodeCode) {
		List items = getCache(nodeCode);

		if ((items != null) && (items.size() > 0)) {
			return items;
		}

		items = findChildDicByParentCode(nodeCode);

		Memcached.set("dic.list.all" + nodeCode, items);
		return items;
	}

	private List<SystemDic> getCache(String nodeCode) {
		return (List) Memcached.get("dic.list.all" + nodeCode);
	}

	private SystemDic getCacheSingle(String nodeCode) {
		return (SystemDic) Memcached.get("dic.list.all" + nodeCode);
	}

	public List<SystemDic> findChildDicByParentCode(String pcode) {
		return findByHql(" from SystemDic where parentId ='" + pcode + "' order by nodeCode asc");
	}

	public SystemDic findSystemDicByCode(String nodeCode) {
		List dics = findByHql("from SystemDic where node_code ='" + nodeCode + "'");
		if ((dics != null) && (dics.size() > 0)) {
			return (SystemDic) dics.get(0);
		}
		return null;
	}

	public SystemDic findSystemDicByValue(String pCode, String nodeValue) {
		List dics = findByHql("from SystemDic where parent_id = '" + pCode + "' and node_value ='" + nodeValue + "'");
		if ((dics != null) && (dics.size() > 0)) {
			return (SystemDic) dics.get(0);
		}
		return null;
	}
}
