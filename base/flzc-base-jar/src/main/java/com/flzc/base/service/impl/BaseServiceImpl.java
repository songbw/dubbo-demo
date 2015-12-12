package com.flzc.base.service.impl;

import com.flzc.base.dao.BaseDao;
import com.flzc.base.service.BaseService;
import com.flzc.base.util.CommonUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BaseServiceImpl implements BaseService{

	@Resource
	private BaseDao baseDao;
	
	
	
	@Override
	public Serializable save(Object paramObject) {
		// TODO Auto-generated method stub
		return baseDao.save(paramObject);
	}

	@Override
	public void update(Object paramObject) {

		baseDao.update(paramObject);
	}

	@Override
	public void delete(Object paramObject) {
		
		baseDao.delete(paramObject);
	}

	@Override
	public void saveOrUpdate(Object paramObject) {
		
		baseDao.saveOrUpdate(paramObject);
	}

	@Override
	public void saveAll(List<?> paramList) {
		
		baseDao.saveAll(paramList);
	}

	@Override
	public void updateAll(List<?> paramList) {
		
		baseDao.updateAll(paramList);
	}

	@Override
	public <T> T findById(Class<T> paramClass, Serializable paramSerializable) {
		
		return baseDao.findById(paramClass, paramSerializable);
	}

	@Override
	public int execUpdateHql(String hql) {
		
		return baseDao.execUpdateHql(hql);
	}

	@Override
	public int execUpdateSql(String sql) {
		
		return baseDao.execUpdateSql(sql);
	}

	@Override
	public <T> List<T> findByHql(String hql) {
		
		return baseDao.findByHql(hql);
	}

	@Override
	public <T> List<T> findBySql(String sql) {
		
		return baseDao.findBySql(sql);
	}

	@Override
	public <T> List<T> findBySqlAndClass(String sql, Class<T> cl) {
		
		return baseDao.findBySqlAndClass(sql, cl);
	}

	@Override
	public <T> List<T> findByHql(String hql, int size) {
		
		return baseDao.findByHql(hql, size);
	}

	@Override
	public <T> List<T> findByHql(String hql, int currSize, int pageSize) {
		
		return baseDao.findByHql(hql, currSize, pageSize);
	}

	@Override
	public <T> List<T> findByNamedParam(String hql, String paramString, Object paramObject) {
		
		return baseDao.findByNamedParam(hql, paramString, paramObject);
	}

	@Override
	public <T> List<T> findByNamedParam(String hql, String paramString, Object paramObject, int size) {
		// TODO Auto-generated method stub
		return baseDao.findByNamedParam(hql, paramString, paramObject, size);
	}

	@Override
	public <T> List<T> findByNamedParam(String hql, String[] paramArrayOfString, Object[] paramArrayOfObject) {
		
		return baseDao.findByNamedParam(hql, paramArrayOfString, paramArrayOfObject);
	}

	@Override
	public <T> List<T> findByNamedParam(String hql, String[] paramArrayOfString, Object[] paramArrayOfObject,
			int size) {
		
		return baseDao.findByNamedParam(hql, paramArrayOfString, paramArrayOfObject, size);
	}

	@Override
	public <T> List<T> findByNamedParam(String hql, String[] paramArrayOfString, Object[] paramArrayOfObject,
			int currSize, int pageSize) {
		
		return baseDao.findByNamedParam(hql, paramArrayOfString, paramArrayOfObject, currSize, pageSize);
	}

	@Override
	public <T> void evict(Class<T> paramClass, Serializable paramSerializable) {
		
		baseDao.evict(paramClass, paramSerializable);
	}

	@Override
	public void flush() {
		
		baseDao.flush();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map findMapBySql(String sql) {
		
		return baseDao.findMapBySql(sql);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map findKeyMapBySql(String sql) {
		
		return baseDao.findKeyMapBySql(sql);
	}

	@Override
	public <T> List<T> findListMapBySql(String sql) {
		// TODO Auto-generated method stub
		return baseDao.findListMapBySql(sql);
	}

	@Override
	public <T> List<T> getPageVO(String hql, int begin, int rows, Object[] paramValue) {
		// TODO Auto-generated method stub
		return baseDao.getPageVO(hql, begin, rows, paramValue);
	}

	@Override
	public <T> List<T> getPageTotalVO(String hql, Object[] paramValue) {
		// TODO Auto-generated method stub
		return baseDao.getPageTotalVO(hql, paramValue);
	}

	@Override
	public int callProcBySql(String sql, Object[] paramValue) {
		// TODO Auto-generated method stub
		return baseDao.callProcBySql(sql, paramValue);
	}

	@Override
	public Map<String, Object> findAllByPageAndPageSize(String className, int currPage, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.findAllByPageAndPageSize(className, currPage, pageSize);
	}

	@Override
	public <T> List<T> findAll(String className) {
		// TODO Auto-generated method stub
		return baseDao.findAll(className);
	}

	@Override
	public int findPageTotalCount(String hql) {
		// TODO Auto-generated method stub
		return baseDao.findPageTotalCount(hql);
	}

	@Override
	public <T> List<T> findBySqlAndNamedParam(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject,
			int currSize, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.findBySqlAndNamedParam(sql, paramArrayOfString, paramArrayOfObject, currSize, pageSize);
	}

	/**
	 * 根据传入的对象动态查询，返回该对象所以属性值.暂不支持日期的范围查询
	 *
	 * @param t          查询参数对象
	 * @param expectedCols 需要返回的字段属性名称
	 * @return
	 */
	@Override
	public <T> List<T> findByParam(T t, String[] expectedCols) {
		try {
			return 	findByDynamic(t, expectedCols, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return  null;
		}
	}
	/**
	 * 根据传入的对象动态查询，返回该对象所以属性值.暂不支持日期的范围查询
	 *
	 * @param t          查询参数对象
	 * @param expectedCols 需要返回的字段属性名称
	 * @return
	 */
	@Override
	public <T> List<T> findByParamWithPage(T t, String[] expectedCols ,int curPage,int pageSize)   {
		try {
			return 	findByDynamic(t,expectedCols,curPage,pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    private <T> List<T>  findByDynamic(T t , String[] expectedCols ,Integer curPage,Integer pageSize) throws Exception {
		String fullName = t.getClass().getName();
		Map<String,Object> map = PropertyUtils.describe(t);
		map.remove("class");//去掉转化生成的多的key
		StringBuffer sbHql;

		Iterator<String> iterator = map.keySet().iterator();
		if(expectedCols == null || expectedCols.length==0){
			sbHql = new StringBuffer(" from " + fullName + " as o  where ");
		}else{
			StringBuffer props = new StringBuffer();
			for(String s: expectedCols){
				props.append("o."+ s).append(",");
			}
			String pp =  props.toString().replaceAll(",$","");
			sbHql = new StringBuffer("select " + pp + " from " + fullName + " as o  where ");
		}

		List<String> cols = new ArrayList<>();
		List<Object> vals = new ArrayList<>();
		while(iterator.hasNext()){
			String key = iterator.next();
			Object val = map.get(key);
			if(val == null ) continue;
			cols.add(key);
			if(val instanceof Date ){//处理日期类型
				if( key.equals("startDate") ||  key.equals("endDate") ){
					Object startDate = map.get("startDate");
					Object endDate = map.get("endDate");
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					if(startDate != null && endDate != null){
						sbHql.append(String.format("  o.createTime between '%1s' and '%2s' and  ",df.format(startDate) ,df.format(endDate)));
					}else if(startDate != null && endDate == null){
						sbHql.append(String.format("  o.createTime = '%1s' and  ",df.format(startDate)));
					}

				}else{
					//此处的逻辑可以提出，由开发者自定义 todo
					sbHql.append(String.format("date_format(%1s,'%2s')=:%3s", "o."+key , "%Y-%m-%d"  , key )).append(" and ");
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					vals.add(df.format(val));
				}


			}else{
				sbHql.append(" o.").append(key).append("=:").append(key).append(" and ");
				vals.add(val);
			}
		}
		String hql =  sbHql.toString().replaceAll("(and|where)\\s+$","");
		List<T> results ;
		if(curPage == null || pageSize == null){
			results = this.baseDao.findByNamedParam(hql, CommonUtils.listToStrArray(cols), vals.toArray());
		}else{
			results = this.baseDao.findByNamedParam(hql, CommonUtils.listToStrArray(cols), vals.toArray(),(curPage-1) * pageSize,pageSize);
		}
		//
		if(expectedCols != null){
			 List<T> tmpList = new ArrayList<>(results.size());
			  for( Object row : results){
				  T o = (T)t.getClass().newInstance();
				  Object[] valsObject = (Object[])row;
				  for(int i= 0;i<expectedCols.length;i++){
					   PropertyUtils.setProperty(o,expectedCols[i],valsObject[i]);
					  tmpList.add(o);
				  }
			  }
			return tmpList;
		}
		return results;
	}

	/**
	 * 根据条件参数对象查询总条数
	 *
	 * @param t
	 * @return
	 */
	@Override
	public <T> int countSizeByParam(T t) {
		String fullName = t.getClass().getName();
		Map<String,Object> map = new HashMap<>();
		BeanUtils.copyProperties(t, map);
		Iterator<String> iterator = map.keySet().iterator();
		StringBuffer sbHql = new StringBuffer(" select count(1)  from " + fullName + " as o  where ");
		List<String> cols = new ArrayList<>();
		List<Object> vals = new ArrayList<>();
		while(iterator.hasNext()){
			String key = iterator.next();
			Object val = map.get(key);
			cols.add(key);
			if(val instanceof Date){//处理日期类型
				//此处的逻辑可以提出，由开发者自定义
				sbHql.append(String.format("date_format(%1s,'%2s')=:%3s", "o."+key , "%Y-%m-%d"  , key )).append(" and ");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				vals.add(df.format(val));
			}else{
				sbHql.append(" o.").append(key).append("=:").append(key).append(" and ");
				vals.add(val);
			}
		}
		String hql =  sbHql.toString().replaceAll("and\\s+$","");
		List<Object> result = this.baseDao.findByNamedParam(hql, CommonUtils.listToStrArray(cols), vals.toArray());
		if(result == null || result.isEmpty()) return 0;
		return Integer.valueOf(result.get(0).toString()) ;
	}

	@Override
	public <T> List<T> findBySqlAndNamedParam(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject) {
		 return   this.baseDao.findBySqlAndNamedParam(sql,paramArrayOfString,paramArrayOfObject);
	}

	@Override
	public List<Map<String, String>> findMapBySqlAndNamedParam(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject) {
		return  this.baseDao.findMapBySqlAndNamedParam(sql,paramArrayOfString,paramArrayOfObject);
	}

	@Override
	public List<Map<String, String>> findMapBySqlAndNamedParam(String sql, String[] paramArrayOfString, Object[] paramArrayOfObject, Integer page, Integer pageSize) {
		page = (page==null?1:page);
		pageSize = (pageSize==null?1:pageSize);
		return  this.baseDao.findMapBySqlAndNamedParamPage(sql,paramArrayOfString,paramArrayOfObject,page,pageSize);
	}
}
