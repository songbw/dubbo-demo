package com.flzc.base.service;

import java.util.List;
import java.util.Map;

/**通用数据库访问服务，提供一些基础的增，删，改，查
 * Created by iverson on 2015/9/19.
 */
public interface CommonService {

    public <T>  Number save(T t) throws  Exception;

    public <T> void update(T t) throws  Exception;

    /**
     * 根据实体类属性值，动态查询,支持map
     * @param t
     * @param <T>
     * @return
     */
    public <T> List<T> findObj(T t) throws  Exception;

    /**
     * 查询唯一对象
     * @param t
     * @param <T>
     * @return
     */
    public <T> T findUniqueObj(T t) throws  Exception;

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
    public List<Map<String, Object>> findMapBySqlAndNamedParamPage(String sql, List<String> params, List<Object> vals, Integer curPage, Integer pageSize) throws Exception;


}
