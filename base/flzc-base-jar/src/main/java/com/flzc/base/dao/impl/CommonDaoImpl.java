package com.flzc.base.dao.impl;

import com.flzc.base.dao.CommonDao;
import com.flzc.base.util.CommonUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by iverson on 2015/9/18.
 */
@Repository("commonDao")
public class CommonDaoImpl implements CommonDao {

    private static final  Logger LOGGER = LoggerFactory.getLogger(CommonDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @param t
     * @param <T>
     * @return 返回id
     */
    public <T> Number save(T t) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        return (Number) session.save(t);
    }

    public <T> void update(T t) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
    }

    @Override
    public <T> void delete(T t) throws Exception {
        this.sessionFactory.getCurrentSession().delete(t);
    }

    /**
     * 根据实体类属性值，动态查询
     *
     * @param t
     * @return
     */
    public <T> List<T> findObjs(T t) throws  Exception{
       return   findObjs(t,null);
    }

    @Override
    public <T> List<T> findObjsWithPage(T t, int curPage, int pageSize) throws Exception {
        if(t == null) return null;
        Query query = this.generateQuery(t,null);
        query.setFirstResult( (curPage-1)*pageSize );
        query.setMaxResults(pageSize);
        List result = query.list();
        return result;
    }

    /**
     * 按条件查询，只返回指定字段.如果不指定返回全部字段
     *
     * @param t             实体对象，包含了查询条件
     * @param expectColumns 要查询的字段
     * @return
     */
    @Override
    public <T> List<T> findObjs(T t, List<String> expectColumns) throws Exception {
        if(t == null) return null;
        Query query = this.generateQuery(t, expectColumns);
        List result = query.list();
        if(expectColumns==null || expectColumns.isEmpty()) return result;
        List<T> rows = convertResult(t, expectColumns, result);
        return rows;
    }

    private <T> List<T> convertResult(T t, List<String> expectColumns, List result) throws Exception {
        List<T> rows = new ArrayList<>(result.size());
        for(int i = 0 ;i<result.size();i++){
            Object[] o = (Object[]) result.get(i); //row record
            T obj = (T) t.getClass().newInstance();
            for(int j = 0 ;j < o.length;j++){
                 PropertyUtils.setProperty(obj, expectColumns.get(j), o[j]);
            }
            rows.add(obj);
        }
        return rows;
    }

    @Override
    public <T> List<T> findObjsWithPage(T t, List<String> expectColumns, int curPage, int pageSize) throws Exception {
        if(t == null) return null;
        Query query = this.generateQuery(t, expectColumns);
        query.setFirstResult( (curPage-1)*pageSize );
        query.setMaxResults(pageSize);
        List result = query.list();
        return this.convertResult(t,expectColumns,result);
    }

    /**
     * 查询唯一对象,只限单表查询
     *
     * @param t
     * @return
     */
    public <T> T findUniqueObj(T t) throws Exception {
        if (t == null) return null;
        Query query = this.generateQuery(t,null);
        Object o = query.uniqueResult();
        return (T)o;
    }

    /**
     * 查询唯一对象，只返回指定的字段
     *
     * @param t
     * @param expectColumns
     * @return
     * @throws Exception
     */
    @Override
    public <T> T findUniqueObj(T t, List<String> expectColumns) throws Exception {
        if(expectColumns == null || expectColumns.isEmpty())
              return this.findUniqueObj(t);
        Query query = this.generateQuery(t, expectColumns);
        Object[] os = (Object[])query.uniqueResult();
        if(os == null) return null;
        T o = (T) t.getClass().newInstance();
        for(int i = 0;i<os.length;i++){
             PropertyUtils.setProperty(o,expectColumns.get(i),os[i]);
        }
        return o;

    }

    /**
     * 清除map里空值
     * @param map
     * @return 返回清除空值后的map
     */
    private Map clearNullValue(Map map){
        map.remove("class");
        Iterator iterator = map.keySet().iterator();
        Map tmp = new HashMap();
        while (iterator.hasNext()){
            Object key = iterator.next();
            Object o = map.get(key);
            if(o != null){
                tmp.put(key,o);
            }
        }
        return tmp;
    }

    /**
     * 根据kv，生成query对象
     * @param t 实体类
     * @return
     */
    private <T> Query  generateQuery(T t , List<String> expectColumns) throws Exception {
        Session session = this.sessionFactory.getCurrentSession();
        String objAlias = "o";
        List<String> props = Collections.EMPTY_LIST;
        List<Object> values = Collections.EMPTY_LIST;

        Map kv = clearNullValue(BeanUtils.describe(t));
        StringBuffer sqlSb = new StringBuffer(concatProperty(expectColumns) + " from " + t.getClass().getName());
        if(! kv.isEmpty()){
            sqlSb.append(" ").append(objAlias + " where ");
            Iterator keys = kv.keySet().iterator();
            props = new ArrayList<>(kv.size());
            values = new ArrayList<>(kv.size());
            while (keys.hasNext()){
                String key = keys.next().toString();
                props.add(key);
                values.add(PropertyUtils.getProperty(t,key));
                //o.key=:key and
                sqlSb.append(objAlias).append(".").append(key).append("=:").append(key).append(" and ");
            }
        }
        String hql = sqlSb.toString().replaceAll("and\\s*$", "");
        Query query = session.createQuery(hql);
        for(int i = 0 ;i < props.size();i++){
            query.setParameter(props.get(i),values.get(i));
        }
        return query;
    }

    /**
     * 把要返回的字段拼成查询串 ：select o.xx,o.bb
     * @param expectColumns
     * @return
     */
    private String concatProperty(List<String> expectColumns){
        if(expectColumns==null || expectColumns.isEmpty()) return "";
        StringBuffer sb = new StringBuffer("select ");
        for (String c : expectColumns){
            sb.append("o.").append(c).append(",");
        }
        return sb.toString().replaceAll(",$","");
    }

    /**
     * 根据id查询
     *
     * @param clazz
     * @param id
     * @return
     */
    public <T> T findById(Class<T> clazz, Number id) {
        Session session = sessionFactory.getCurrentSession();
       return  (T)session.get(clazz,id);
    }



    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public <T> List<T> findAll(Class<T> clazz) throws Exception {
        Session session = getSessionFactory().getCurrentSession();
        List list = session.createQuery("from " + clazz.getName()).list();
        return list;
    }

    @Override
    public List<Map<String, Object>> findMapBySqlAndNamedParamPage(String sql, List<String> params, List<Object> vals, Integer page, Integer pageSize) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery(sql);
        if (params != null) {
            for (int i = 0; i < params.size(); i++)
                query.setParameter(params.get(i), vals.get(i));
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult( page);
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * 根据sql查询返回多条数据
     *
     * @param sql
     * @return
     */
    @Override
    public List<Map<String, Object>> findBySql(String sql ,int curPage, int pageSize) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setFirstResult( curPage);
        query.setMaxResults(pageSize);
        return query.list();
    }

    /**
     * 根据sql查询返回多条数据
     *
     * @param sql
     * @return
     */
    @Override
    public List<Map<String, Object>> findBySql(String sql) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    /**
     * 根据 sql 查询出唯一对象。
     * @param sql
     * @return
     */
    @Override
    public Map<String,Object> findUniqueBySql(String sql ){
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (Map)query.uniqueResult();
    }
    /**
     * 根据 hql 查询出唯一对象。 hql 必须是 select a as aa ,b as bb from xxxx 以避免的map key是数字
     * @param hql
     * @return
     */
    @Override
    public Map<String,Object> findUniqueByHql(String hql){
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery(hql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (Map)query.uniqueResult();
    }
    /**
     * 根据sql查询出总条数
     *
     * @param sql
     * @return
     */
    @Override
    public int findCountBySql(String sql) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = currentSession.createSQLQuery(sql);
        Object o = sqlQuery.uniqueResult();
        if(o != null){
           return   Integer.valueOf(o.toString());
        }
        return 0;
    }

    @Override
    public int findCountByHql(String hql) {
        Session currentSession = this.getSessionFactory().getCurrentSession();
        Query o = currentSession.createQuery(hql);
        Object re =  o.uniqueResult();
        if(re != null){
            return   Integer.valueOf(re.toString());
        }
        return 0;
    }

    @Override
    public <T> int findCountByParams(T t) throws Exception {
        Map kv = BeanUtils.describe(t);
        kv = CommonUtils.clearMapBlankVal(kv);
        StringBuffer sqlSb = new StringBuffer("select count(id) as total from " + t.getClass().getName());
        String objAlias = "o";
        List<String> props = Collections.EMPTY_LIST;
        List<Object> values = Collections.EMPTY_LIST;
        if(! kv.isEmpty()){
            sqlSb.append(" ").append(objAlias + " where ");
            Iterator entry = kv.entrySet().iterator();
            props = new ArrayList<>(kv.size());
            values = new ArrayList<>(kv.size());
            while (entry.hasNext()){
                Map.Entry<String,Object> next = (Map.Entry<String,Object>)entry.next();
                String key = next.getKey();
                props.add(key);
                values.add(next.getValue());
                //o.key=:key and
                sqlSb.append(objAlias).append(".").append(key).append("=:").append(key).append(" and ");
            }
        }
        String hql = sqlSb.toString().replaceAll("and\\s*$", "");
        LOGGER.debug("根据条件计算总条数：hql = "+hql);
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        for(int i = 0 ;i < props.size();i++){
            query.setParameter(props.get(i),values.get(i));
        }
        Long o = (Long)query.uniqueResult();
        if(o!= null ){
            return o.intValue();
        }
        return 0 ;
    }

    /**
     * 根据hql更新
     * @param hql
     * @throws Exception
     */
    @Override
    public void  updateByHql(String hql, String[] params, Object[] values) throws  Exception{
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        if(params != null && values!= null && params.length == values.length){
            for (int i = 0; i<params.length;i++){
                query.setParameter(params[i] , values[i]);
            }
        }
        query.executeUpdate();
    }
}
