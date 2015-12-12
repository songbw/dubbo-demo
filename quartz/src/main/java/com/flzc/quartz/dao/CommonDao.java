package com.flzc.quartz.dao;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by iverson on 2015/9/18.
 *  通用数据库操作接口
 */
public interface CommonDao {

     public <T>  Number save(T t) throws  Exception;

     public <T> void update(T t) throws  Exception;

     /**
      * 物理删除
      * @param t
      * @param <T>
      * @throws Exception
      */
     public <T>  void delete(T t) throws Exception;

     /**
      * 根据实体类属性值，动态查询
      * @param t
      * @param <T>
      * @return
      */
     public <T> List<T> findObjs(T t) throws  Exception;

     public <T> List<T> findObjsWithPage(T t, int curPage, int pageSize) throws  Exception;

     /**
      * 按条件查询，只返回指定字段
      * @param t 实体对象，包含了查询条件
      * @param expectColumns 要查询的字段
      * @param <T>
      * @return
      */
     public <T> List<T> findObjs(T t, List<String> expectColumns) throws Exception;


     public <T> List<T> findObjsWithPage(T t, List<String> expectColumns, int curPage, int pageSize) throws Exception;


     public <T> List<T> findAll(Class<T> clazz) throws  Exception;


     /**
      * 查询唯一对象
      * @param t
      * @param <T>
      * @return
      */
     public <T> T findUniqueObj(T t) throws  Exception;

     /**
      * 查询唯一对象，只返回指定的字段
      * @param t
      * @param expectColumns
      * @param <T>
      * @return
      * @throws Exception
      */
     public <T> T findUniqueObj(T t, List<String> expectColumns) throws  Exception;

     /**
      * 根据id查询
      * @param clazz
      * @param id
      * @param <T>
      * @return
      */
     public <T> T findById(Class<T> clazz, Number id) throws Exception;

     /**
      *
      * @param sql 查询sql
      * @param params 占位符名称
      * @param vals 点位符值
      * @param curPage
      * @param pageSize
      * @return
      */
     public List<Map<String, Object>> findMapBySqlAndNamedParamPage(String sql, List<String> params, List<Object> vals, Integer curPage, Integer pageSize) ;

     /**
      * 根据sql查询返回多条数据
      * @param sql
      * @return
      */
     public List<Map<String,Object>> findBySql(String sql, int curPage, int pageSize);

     /**
      * 根据sql查询返回多条数据
      * @param sql
      * @return
      */
     public List<Map<String,Object>> findBySql(String sql);

     Map<String,Object> findUniqueBySql(String sql);

     Map<String,Object> findUniqueByHql(String hql);

     /**
      * 根据sql查询出总条数
      * @param sql
      * @return
      */
     public int findCountBySql(String sql);

     public int findCountByHql(String hql);

     public <T> int findCountByParams(T t) throws Exception;

     public SessionFactory getSessionFactory();

     /**
      * 根据hql更新
      * @param hql
      * @throws Exception
      */
     void  updateByHql(String hql, String[] params, Object[] values) throws  Exception;

     List<Map<String, Object>> findMapBySqlParams(String sql, String[] params, Object[] vals);

     void updateBySql(String sql);
}
