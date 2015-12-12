package com.flzc.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 公共dao接口层
 *
 * @author chenqi
 */
public abstract interface BaseDao extends Serializable {

    /**
     * save(保存对象)
     *
     * @param @param  paramObject
     * @param @return
     * @return Serializable 返回类型
     * @throws
     * @Title: save
     * @Description: TODO
     */
    public abstract Serializable save(Object paramObject);

    /**
     * update(修改对象)
     *
     * @param paramObject
     * @return void 返回类型
     * @throws
     * @Title: update
     * @author chenqi
     */
    public abstract void update(Object paramObject);

    /**
     * delete(删除对象)
     *
     * @param paramObject
     * @return void 返回类型
     * @throws
     * @Title: delete
     * @author chenqi
     */
    public abstract void delete(Object paramObject);

    /**
     * saveOrUpdate(保存或修改对象方法)
     *
     * @param paramObject
     * @return void 返回类型
     * @throws
     * @Title: saveOrUpdate
     * @author chenqi
     */
    public abstract void saveOrUpdate(Object paramObject);

    /**
     * saveAll(保存对象集合)
     *
     * @param paramList
     * @return void 返回类型
     * @throws
     * @Title: saveAll
     * @author chenqi
     */
    public abstract void saveAll(List<?> paramList);

    /**
     * updateAll(修改对象集合)
     *
     * @param paramList
     * @return void 返回类型
     * @throws
     * @Title: updateAll
     * @author chenqi
     */
    public abstract void updateAll(List<?> paramList);

    /**
     * findById(根据主键查询对象)
     *
     * @param paramClass
     * @param paramSerializable
     * @return Object 返回类型
     * @throws
     * @Title: findById
     * @author chenqi
     */
    public abstract <T> T findById(Class<T> paramClass,
                                   Serializable paramSerializable);

    /**
     * execUpdateHql(执行hql语句进行修改)
     *
     * @param hql
     * @return int（0或1） 返回类型
     * @throws
     * @Title: execUpdateHql
     * @author chenqi
     */
    public abstract int execUpdateHql(String hql);

    /**
     * execUpdateSql(根据sql语句进行修改)
     *
     * @param sql
     * @return int（0或1） 返回类型
     * @throws
     * @Title: execUpdateSql
     * @author chenqi
     */
    public abstract int execUpdateSql(String sql);

    /**
     * @param <T> findByHql(根据hql语句查询list集合)
     * @param hql
     * @return List 返回类型
     * @throws
     * @Title: findByHql
     * @author chenqi
     */
    public abstract <T> List<T> findByHql(String hql);

    /**
     * findBySql(根据sql语句查询list集合)
     *
     * @param sql
     * @return List 返回类型
     * @throws
     * @Title: findBySql
     * @author chenqi
     */
    public abstract <T> List<T> findBySql(String sql);

    /**
     * findBySqlAndClass(根据sql和class查询实体list)
     *
     * @param <T>
     * @param sql
     * @param cl
     * @return
     * @author chenqi
     * @date 2014年7月24日 下午7:19:04
     */
    public <T> List<T> findBySqlAndClass(String sql, Class<T> cl);

    /**
     * findByHql(这里用一句话描述这个方法的作用)
     *
     * @param hql
     * @param size
     * @return List 返回类型
     * @throws
     * @Title: findByHql
     * @author chenqi
     */
    public abstract <T> List<T> findByHql(String hql, int size);

    /**
     * findByHql(查询分页方法)
     *
     * @param hql
     * @param currSize 当前开始条数
     * @param pageSize 查询条数
     * @return List 返回类型
     * @throws
     * @Title: findByHql
     * @author chenqi
     */
    public abstract <T> List<T> findByHql(String hql, int currSize, int pageSize);

    /**
     * findByNamedParam(一个参数hql查询对象集合方法)
     *
     * @param hql
     * @param paramString 参数名
     * @param paramObject 参数值
     * @return List 返回类型
     * @throws
     * @Title: findByNamedParam
     * @author chenqi
     */
    public abstract <T> List<T> findByNamedParam(String hql, String paramString,
                                                 Object paramObject);

    /**
     * findByNamedParam(一个参数hql查询n条方法)
     *
     * @param hql
     * @param paramString 参数名
     * @param paramObject 参数值
     * @param size        条数
     * @return List 返回类型
     * @throws
     * @Title: findByNamedParam
     * @author chenqi
     */
    public abstract <T> List<T> findByNamedParam(String hql, String paramString,
                                                 Object paramObject, int size);

    /**
     * findByNamedParam(多个参数查询对象集合)
     *
     * @param hql
     * @param paramArrayOfString 数组参数名
     * @param paramArrayOfObject 数组参数值
     * @return List 返回类型
     * @throws
     * @Title: findByNamedParam
     * @author chenqi
     */
    public abstract <T> List<T> findByNamedParam(String hql,
                                                 String[] paramArrayOfString, Object[] paramArrayOfObject);

    /**
     * findByNamedParam(多个参数查询对象集合n条)
     *
     * @param hql
     * @param paramArrayOfString 数组参数名
     * @param paramArrayOfObject 数组参数值
     * @param size               条数
     * @return List 返回类型
     * @throws
     * @Title: findByNamedParam
     * @author chenqi
     */
    public abstract <T> List<T> findByNamedParam(String hql,
                                                 String[] paramArrayOfString, Object[] paramArrayOfObject, int size);

    /**
     * findByNamedParam(多个参数查询分页对象集合)
     *
     * @param hql
     * @param paramArrayOfString
     * @param paramArrayOfObject
     * @param currSize           当前条数
     * @param pageSize           查询条数
     * @return List 返回类型
     * @throws
     * @Title: findByNamedParam
     * @author chenqi
     */
    public abstract <T> List<T> findByNamedParam(String hql,
                                                 String[] paramArrayOfString, Object[] paramArrayOfObject,
                                                 int currSize, int pageSize);

    /**
     * @param <T>               evict(会把指定的缓冲对象进行清除)
     * @param paramClass
     * @param paramSerializable
     * @return void 返回类型
     * @throws
     * @Title: evict
     * @author chenqi
     */
    public abstract <T> void evict(Class<T> paramClass, Serializable paramSerializable);

    /**
     * flush(可以强制进行从内存到数据库的同步)
     *
     * @return void    返回类型
     * @throws
     * @Title: flush
     * @author chenqi
     */
    public abstract void flush();

    /**
     * findMapBySql(根据sql查询map)
     *
     * @param sql
     * @return Map 返回类型
     * @throws
     * @Title: findMapBySql
     * @author chenqi
     */
    @SuppressWarnings("rawtypes")
    public abstract Map findMapBySql(String sql);

    /**
     * findKeyMapBySql(根据sql查询key value模式map)
     *
     * @param sql
     * @return Map 返回类型
     * @throws
     * @Title: findKeyMapBySql
     * @author chenqi
     */
    @SuppressWarnings("rawtypes")
    public abstract Map findKeyMapBySql(String sql);

    /**
     * findListMapBySql(根据sql查询map集合)
     *
     * @param sql
     * @return List 返回类型
     * @throws
     * @Title: findListMapBySql
     * @author chenqi
     */
    public abstract <T> List<T> findListMapBySql(String sql);

    /**
     * getPageVO(根据条件获取分页)
     *
     * @param hql
     * @param begin      当前条数
     * @param rows       查询条数
     * @param paramValue
     * @return List 返回类型
     * @throws
     * @Title: getPageVO
     * @author chenqi
     */
    public abstract <T> List<T> getPageVO(String hql, int begin, int rows,
                                          Object[] paramValue);

    /**
     * getPageTotalVO(这里用一句话描述这个方法的作用)
     *
     * @param hql
     * @param paramValue
     * @return List 返回类型
     * @throws
     * @Title: getPageTotalVO
     * @author chenqi
     */
    public abstract <T> List<T> getPageTotalVO(String hql, Object[] paramValue);

    /**
     * callProcBySql(调用存储过程)
     *
     * @param sql
     * @param paramValue
     * @return int 返回类型
     * @throws
     * @Title: callProcBySql
     * @author chenqi
     */
    public int callProcBySql(String sql, Object[] paramValue);

    /**
     * findAllByPageAndPageSize(根据对象名、页数、当前条数获取分页集合)
     *
     * @param className
     * @param currPage
     * @param pageSize
     * @return
     * @author chenqi
     * @date 2014年7月10日 下午8:21:03
     */
    public abstract Map<String, Object> findAllByPageAndPageSize(String className, int currPage, int pageSize);

    /**
     * findAll(查询所有)
     *
     * @param className
     * @return
     * @author chenqi
     * @date 2014年7月10日 下午8:33:10
     */
    public abstract <T> List<T> findAll(String className);


    /**
     * findPageTotalCount(查询分页总数)
     *
     * @param hql
     * @return
     * @author chenqi
     * @date 2014年7月11日 上午9:27:04
     */
    public abstract int findPageTotalCount(String hql);

    /**
     * findByNamedParam(多个参数查询分页对象集合)
     *
     * @param sql
     * @param paramArrayOfString
     * @param paramArrayOfObject
     * @param currSize           当前条数
     * @param pageSize           查询条数
     * @return List 返回类型
     * @throws
     * @Title: findBySqlAndNamedParam
     * @author chenqi
     */
    public abstract <T> List<T> findBySqlAndNamedParam(String sql,
                                                       String[] paramArrayOfString, Object[] paramArrayOfObject,
                                                       int currSize, int pageSize);

    public abstract <T> List<T> findBySqlAndNamedParam(String sql,
                                                       String[] paramArrayOfString, Object[] paramArrayOfObject);

    public List<Map<String, String>> findMapBySqlAndNamedParam(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject );

    public List<Map<String, String>> findMapBySqlAndNamedParamPage(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject ,Integer page,Integer pageSize);
}
