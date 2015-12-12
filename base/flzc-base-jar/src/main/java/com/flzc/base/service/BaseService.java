package com.flzc.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseService {

	/**
	 * save(保存对象)
	 * 
	 * @Title: save
	 * @Description: TODO
	 * @param @param paramObject
	 * @param @return
	 * @return Serializable 返回类型
	 * @throws
	 */
	public abstract Serializable save(Object paramObject);

	/**
	 * update(修改对象)
	 * 
	 * @Title: update
	 * @author chenqi
	 * @param paramObject
	 * @return void 返回类型
	 * @throws
	 */
	public abstract void update(Object paramObject);

	/**
	 * delete(删除对象)
	 * 
	 * @Title: delete
	 * @author chenqi
	 * @param paramObject
	 * @return void 返回类型
	 * @throws
	 */
	public abstract void delete(Object paramObject);

	/**
	 * saveOrUpdate(保存或修改对象方法)
	 * 
	 * @Title: saveOrUpdate
	 * @author chenqi
	 * @param paramObject
	 * @return void 返回类型
	 * @throws
	 */
	public abstract void saveOrUpdate(Object paramObject);

	/**
	 * saveAll(保存对象集合)
	 * 
	 * @Title: saveAll
	 * @author chenqi
	 * @param paramList
	 * @return void 返回类型
	 * @throws
	 */
	public abstract void saveAll(List<?> paramList);

	/**
	 * updateAll(修改对象集合)
	 * 
	 * @Title: updateAll
	 * @author chenqi
	 * @param paramList
	 * @return void 返回类型
	 * @throws
	 */
	public abstract void updateAll(List<?> paramList);

	/**
	 * findById(根据主键查询对象)
	 * 
	 * @Title: findById
	 * @author chenqi
	 * @param paramClass
	 * @param paramSerializable
	 * @return 对象
	 * @return Object 返回类型
	 * @throws
	 */
	public abstract <T> T findById(Class<T> paramClass,
								   Serializable paramSerializable);

	/**
	 * execUpdateHql(执行hql语句进行修改)
	 * 
	 * @Title: execUpdateHql
	 * @author chenqi
	 * @param hql
	 * @return int（0或1） 返回类型
	 * @throws
	 */
	public abstract int execUpdateHql(String hql);

	/**
	 * execUpdateSql(根据sql语句进行修改)
	 * 
	 * @Title: execUpdateSql
	 * @author chenqi
	 * @param sql
	 * @return int（0或1） 返回类型
	 * @throws
	 */
	public abstract int execUpdateSql(String sql);

	/**
	 * @param <T>
	 * findByHql(根据hql语句查询list集合)
	 * 
	 * @Title: findByHql
	 * @author chenqi
	 * @param hql
	 * @return
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findByHql(String hql);

	/**
	 * findBySql(根据sql语句查询list集合)
	 * 
	 * @Title: findBySql
	 * @author chenqi
	 * @param sql
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findBySql(String sql);
	
	/**
	 * findBySqlAndClass(根据sql和class查询实体list)
	 * @author chenqi
	 * @param <T>
	 * @date 2014年7月24日 下午7:19:04
	 * @param sql
	 * @param cl
	 * @return
	 */
	public <T> List<T> findBySqlAndClass(String sql, Class<T> cl);

	/**
	 * findByHql(这里用一句话描述这个方法的作用)
	 * 
	 * @Title: findByHql
	 * @author chenqi
	 * @param hql
	 * @param size
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findByHql(String hql, int size);

	/**
	 * findByHql(查询分页方法)
	 * 
	 * @Title: findByHql
	 * @author chenqi
	 * @param hql
	 * @param currSize
	 *            当前开始条数
	 * @param pageSize
	 *            查询条数
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findByHql(String hql, int currSize, int pageSize);

	/**
	 * findByNamedParam(一个参数hql查询对象集合方法)
	 * 
	 * @Title: findByNamedParam
	 * @author chenqi
	 * @param hql
	 * @param paramString
	 *            参数名
	 * @param paramObject
	 *            参数值
	 * @return
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findByNamedParam(String hql, String paramString,
												 Object paramObject);

	/**
	 * findByNamedParam(一个参数hql查询n条方法)
	 * 
	 * @Title: findByNamedParam
	 * @author chenqi
	 * @param hql
	 * @param paramString
	 *            参数名
	 * @param paramObject
	 *            参数值
	 * @param size
	 *            条数
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findByNamedParam(String hql, String paramString,
												 Object paramObject, int size);

	/**
	 * findByNamedParam(多个参数查询对象集合)
	 * 
	 * @Title: findByNamedParam
	 * @author chenqi
	 * @param hql
	 * @param paramArrayOfString
	 *            数组参数名
	 * @param paramArrayOfObject
	 *            数组参数值
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findByNamedParam(String hql,
												 String[] paramArrayOfString, Object[] paramArrayOfObject);

	/**
	 * findByNamedParam(多个参数查询对象集合n条)
	 * 
	 * @Title: findByNamedParam
	 * @author chenqi
	 * @param hql
	 * @param paramArrayOfString
	 *            数组参数名
	 * @param paramArrayOfObject
	 *            数组参数值
	 * @param size
	 *            条数
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findByNamedParam(String hql,
												 String[] paramArrayOfString, Object[] paramArrayOfObject, int size);

	/**
	 * findByNamedParam(多个参数查询分页对象集合)
	 * 
	 * @Title: findByNamedParam
	 * @author chenqi
	 * @param hql
	 * @param paramArrayOfString
	 * @param paramArrayOfObject
	 * @param currSize
	 *            当前条数
	 * @param pageSize
	 *            查询条数
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findByNamedParam(String hql,
												 String[] paramArrayOfString, Object[] paramArrayOfObject,
												 int currSize, int pageSize);

	/**
	 * @param <T>
	 * evict(会把指定的缓冲对象进行清除)
	 * 
	 * @Title: evict
	 * @author chenqi
	 * @param paramClass
	 * @param paramSerializable
	 * @return void 返回类型
	 * @throws
	 */
	public abstract <T> void evict(Class<T> paramClass, Serializable paramSerializable);

	/**
	  * flush(可以强制进行从内存到数据库的同步)
	  *
	  * @Title: flush
	  * @author chenqi    
	  * @return void    返回类型
	  * @throws
	 */
	public abstract void flush();

	/**
	 * findMapBySql(根据sql查询map)
	 * 
	 * @Title: findMapBySql
	 * @author chenqi
	 * @param sql
	 * @return Map 返回类型
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public abstract Map findMapBySql(String sql);

	/**
	 * findKeyMapBySql(根据sql查询key value模式map)
	 * 
	 * @Title: findKeyMapBySql
	 * @author chenqi
	 * @param sql
	 * @return Map 返回类型
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public abstract Map findKeyMapBySql(String sql);

	/**
	 * findListMapBySql(根据sql查询map集合)
	 * 
	 * @Title: findListMapBySql
	 * @author chenqi
	 * @param sql
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findListMapBySql(String sql);

	/**
	 * getPageVO(根据条件获取分页)
	 * 
	 * @Title: getPageVO
	 * @author chenqi
	 * @param hql
	 * @param begin
	 *            当前条数
	 * @param rows
	 *            查询条数
	 * @param paramValue
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> getPageVO(String hql, int begin, int rows,
										  Object[] paramValue);

	/**
	 * getPageTotalVO(这里用一句话描述这个方法的作用)
	 * 
	 * @Title: getPageTotalVO
	 * @author chenqi
	 * @param hql
	 * @param paramValue
	 * @return
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> getPageTotalVO(String hql, Object[] paramValue);

	/**
	 * callProcBySql(调用存储过程)
	 * 
	 * @Title: callProcBySql
	 * @author chenqi
	 * @param sql
	 * @param paramValue
	 * @return int 返回类型
	 * @throws
	 */
	public int callProcBySql(String sql, Object[] paramValue);
	
	/**
	 * findAllByPageAndPageSize(根据对象名、页数、当前条数获取分页集合)
	 * @author chenqi
	 * @date 2014年7月10日 下午8:21:03
	 * @param className
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public abstract Map<String,Object> findAllByPageAndPageSize(String className, int currPage, int pageSize);
	
	/**
	 * findAll(查询所有)
	 * @author chenqi
	 * @date 2014年7月10日 下午8:33:10
	 * @param className
	 * @return
	 */
	public abstract <T> List<T> findAll(String className);
	
	
	/**
	 * findPageTotalCount(查询分页总数)
	 * @author chenqi
	 * @date 2014年7月11日 上午9:27:04
	 * @param hql
	 * @return
	 */
	public abstract int findPageTotalCount(String hql);
	
	/**
	 * findByNamedParam(多个参数查询分页对象集合)
	 * 
	 * @Title: findBySqlAndNamedParam
	 * @author chenqi
	 * @param sql
	 * @param paramArrayOfString
	 * @param paramArrayOfObject
	 * @param currSize
	 *            当前条数
	 * @param pageSize
	 *            查询条数
	 * @return List 返回类型
	 * @throws
	 */
	public abstract <T> List<T> findBySqlAndNamedParam(String sql,
													   String[] paramArrayOfString, Object[] paramArrayOfObject,
													   int currSize, int pageSize);

	/**
	 * 根据传入的对象动态查询，返回该对象所以属性值.暂不支持日期的范围查询
	 * @param obj 查询参数对象
	 * @param expectedCols 需要返回的字段
	 * @param <T>
	 * @return
	 */
	public <T>  List<T> findByParam(T obj ,String[] expectedCols);

	public <T> List<T> findByParamWithPage(T t, String[] expectedCols ,int curPage,int pageSize);

	/**
	 * 根据条件参数对象查询总条数
	 * @param obj
	 * @param <T>
	 * @return
	 */
	public <T> int  countSizeByParam(T obj);



	public abstract <T> List<T> findBySqlAndNamedParam(String sql,
													   String[] paramArrayOfString, Object[] paramArrayOfObject);

	public List<Map<String, String>> findMapBySqlAndNamedParam(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject ,Integer page,Integer pageSize);

	public List<Map<String, String>> findMapBySqlAndNamedParam(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject );
}
