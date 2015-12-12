package com.flzc.base.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flzc.base.dao.BaseDao;

@Repository
public class BaseDaoImpl implements BaseDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7966179526846650805L;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	/**
	 * getCurrentSession(获取sessionfactory)
	 * 
	 * @Title: getCurrentSession
	 * @author chenqi
	 * @return
	 * @return Session ��������
	 * @throws
	 */
	public Session getCurrentSession() {

		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(Object paramObject) {
		
		Serializable id = getCurrentSession().save(paramObject);
		return id;
	}

	@Override
	public void update(Object paramObject) {
		
		getCurrentSession().update(paramObject);
	}

	@Override
	public void delete(Object paramObject) {

		getCurrentSession().delete(paramObject);
	}

	@Override
	public void saveOrUpdate(Object paramObject) {

		getCurrentSession().saveOrUpdate(paramObject);
	}

	@Override
	public void saveAll(List<?> paramList) {

		for (Object object : paramList) {
			
			save(object);
		}
	}

	@Override
	public void updateAll(List<?> paramList) {

		for (Object object : paramList) {
			update(object);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findById(Class<T> paramClass, Serializable paramSerializable) {

		return (T) getCurrentSession().get(paramClass, paramSerializable);
	}

	@Override
	public int execUpdateHql(String hql) {
		
		Query query = getCurrentSession().createQuery(hql);
		int i = query.executeUpdate();
		return i;
	}

	@Override
	public int execUpdateSql(String sql) {
		
		Query query = getCurrentSession().createSQLQuery(sql);
		int i = query.executeUpdate();
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByHql(String hql) {
		
		return getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findBySql(String sql) {
		
		Query query = getCurrentSession().createSQLQuery(sql);
	    return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findBySqlAndClass(String sql, Class<T> cl) {

		Query query = getCurrentSession().createSQLQuery(sql).addEntity(cl);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByHql(String hql, int size) {

		Query query = getCurrentSession().createQuery(hql);
		query.setMaxResults(size);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByHql(String hql, int currSize, int pageSize) {

		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(currSize).setMaxResults(pageSize);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByNamedParam(String hql, String paramString, Object paramObject) {
		
		Query query = getCurrentSession().createQuery(hql);
	    query.setParameter(paramString, paramObject);
	    return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByNamedParam(String hql, String paramString, Object paramObject, int size) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter(paramString, paramObject);
		query.setMaxResults(size);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByNamedParam(String hql, String[] paramArrayOfString, Object[] paramArrayOfObject) {

		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		if (paramArrayOfString != null) {
			for (int i = 0; i < paramArrayOfString.length; i++)
				query.setParameter(paramArrayOfString[i], paramArrayOfObject[i]);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByNamedParam(String hql, String[] paramArrayOfString, Object[] paramArrayOfObject,
			int size) {

		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		if (paramArrayOfString != null) {
			for (int i = 0; i < paramArrayOfString.length; i++) {
				query.setParameter(paramArrayOfString[i], paramArrayOfObject[i]);
			}
		}
		query.setMaxResults(size);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findByNamedParam(String hql, String[] paramArrayOfString, Object[] paramArrayOfObject,
			int currSize, int pageSize) {

		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		if (paramArrayOfString != null) {
			for (int i = 0; i < paramArrayOfString.length; i++)
				query.setParameter(paramArrayOfString[i], paramArrayOfObject[i]);
		}
		query.setFirstResult(currSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public <T> void evict(Class<T> paramClass, Serializable paramSerializable) {
		
		Object obj = getCurrentSession().load(paramClass, paramSerializable);
		if (obj.getClass().getName().equals(paramClass.getName()))
			getCurrentSession().evict(obj);
	}

	@Override
	public void flush() {

		getCurrentSession().flush();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map findMapBySql(String sql) {
		
		Query query = getCurrentSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map hashMap = new HashMap<String, String>();
		List<?> list = query.list();
		String key = "";
		for (Object objList : list) {
			Map map = (Map) objList;
			Set<?> keyset = map.keySet();
			for (Object object : keyset) {
				key = key + object.toString() + "|";
			}
			hashMap.put(map.get(key.split("|")[1]), map.get(key.split("|")[3]));
		}
		return hashMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map findKeyMapBySql(String sql) {

		Map hashMap = new HashMap<String, String>();
		Query query = getCurrentSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> list = query.list();
		for (Object objList : list) {
			Map map = (Map) objList;
			hashMap.put(map.get("KEY"), map);
		}
		return hashMap;
	}

	@SuppressWarnings({ "unchecked"})
	@Override
	public <T> List<T> findListMapBySql(String sql) {

		Query query = getCurrentSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getPageVO(String hql, int begin, int rows, Object[] paramValue) {

		Query query = getCurrentSession().createQuery(hql);
		if (paramValue != null) {
			for (int i = 0; i < paramValue.length; i++) {
				query.setParameter(i, paramValue[i]);
			}
		}
		query.setFirstResult(begin);
		query.setMaxResults(rows);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getPageTotalVO(String hql, Object[] paramValue) {

		Query query = getCurrentSession().createQuery(hql);
		if (paramValue != null) {
			for (int i = 0; i < paramValue.length; i++) {
				query.setParameter(i, paramValue[i]);
			}
		}
		return query.list();
	}

	@Override
	public int callProcBySql(String sql, Object[] paramValue) {

		Session session = getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		if (paramValue != null) {
			for (int i = 0; i < paramValue.length; i++) {
				query.setString(i, paramValue[i].toString());
			}
		}
		int i = query.executeUpdate();
		return i;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, Object> findAllByPageAndPageSize(String className, int currPage, int pageSize) {
		
		String hql = "from " + className;
		Session session = getCurrentSession();
		
		Query query = session.createQuery(hql);
		
		int minSize = (currPage - 1)*pageSize;
		query.setFirstResult(minSize).setMaxResults(pageSize);
		List list = query.list();
		
		int total = 0;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (list == null || list.isEmpty()){
			
			map.put("result", list);
			map.put("total", total);
			return map;
		}
			
		hql = "select count(*) from " + className;
		query = session.createQuery(hql);
		List<?> listCount = query.list();
		
		if (listCount == null || listCount.isEmpty()){
			
			map.put("result", list);
			map.put("total", total);
			return map;
		}
		
		total = Integer.valueOf(listCount.get(0).toString());
		
		map.put("result", list);
		map.put("total", total);
		
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(String className) {
		
		String hql = "from " + className;
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public int findPageTotalCount(String hql) {
		
		Session session = getCurrentSession();
		Query query = session.createQuery(hql);
		List<?> list = query.list();
		
		return (list == null || list.isEmpty())?0:Integer.valueOf(
				list.get(0).toString()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findBySqlAndNamedParam(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject,
			int currSize, int pageSize) {

		Session session = getCurrentSession();
		Query query = session.createSQLQuery(sql);
		if (paramArrayOfString != null) {
			for (int i = 0; i < paramArrayOfString.length; i++)
				query.setParameter(paramArrayOfString[i], paramArrayOfObject[i]);
		}
		query.setFirstResult(currSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public <T> List<T> findBySqlAndNamedParam(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject) {
		Session session = getCurrentSession();
		Query query = session.createSQLQuery(sql);
		if (paramArrayOfString != null) {
			for (int i = 0; i < paramArrayOfString.length; i++)
				query.setParameter(paramArrayOfString[i], paramArrayOfObject[i]);
		}
		return query.list();
	}

	@Override
	public  List<Map<String,String>> findMapBySqlAndNamedParam(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject) {
		Session session = getCurrentSession();
		Query query = session.createSQLQuery(sql);
		if (paramArrayOfString != null) {
			for (int i = 0; i < paramArrayOfString.length; i++)
				query.setParameter(paramArrayOfString[i], paramArrayOfObject[i]);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public List<Map<String, String>> findMapBySqlAndNamedParamPage(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject, Integer page, Integer pageSize) {
		Session session = getCurrentSession();
		Query query = session.createSQLQuery(sql);
		if (paramArrayOfString != null) {
			for (int i = 0; i < paramArrayOfString.length; i++)
				query.setParameter(paramArrayOfString[i], paramArrayOfObject[i]);
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		query.setFirstResult( page);
		query.setMaxResults(pageSize);
		return query.list();
	}
}
