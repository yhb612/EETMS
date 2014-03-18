package com.iss.gms.basedao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public class IBatisDAO extends SqlMapClientDaoSupport {

    /**
     * 插入操作.
     * 
     * @param str
     *            SqlID
     * @return 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public Object insert(String str) throws SQLException {

        return getSqlMapClient().insert(str);
    }

    /**
     * 插入操作.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql的参数
     * @return 返回结果
     * @throws DataAccessException
     *             SQL异常
     */
    public Object insert(String str, Object obj) throws DataAccessException {
        return getSqlMapClientTemplate().insert(str, obj);
    }

    /**
     * 更新操作.
     * 
     * @param str
     *            SqlID
     * @throws SQLException
     *             SQL异常
     */
    public int update(String str) throws SQLException {
        return getSqlMapClient().update(str);
    }

    /**
     * 更新操作.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql的参数
     * @throws SQLException
     *             SQL异常
     */
    public int update(String str, Object obj) throws SQLException {
        return getSqlMapClient().update(str, obj);
    }

    /**
     * @param str
     *            SqlID
     * @return 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public List queryForList(String str) throws SQLException {
        return getSqlMapClient().queryForList(str);
    }

    /**
     * 查询操作，返回对象集合.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql的参数
     * @return 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public List queryForList(String str, Object obj) throws SQLException {
        return getSqlMapClient().queryForList(str, obj);
    }

    /**
     * 查询操作，返回对象集合，支持传入参数.
     * 
     * @param str
     *            SqlID
     * @param index
     * @param length
     * @return 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public List queryForList(String str, int index, int length) throws SQLException {
        return getSqlMapClient().queryForList(str, index, length);
    }
    
    /**
     * 查询操作，返回对象集合，支持传入参数，带分页功能.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql的参数
     * @param index
     *            起始点
     * @param length
     *            最大长度
     * @return 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public List queryForList(String str, Object obj, int index, int length) throws SQLException {
        return getSqlMapClient().queryForList(str, obj, index, length);
    }
    
    /**
     * @param sql
     * @param currentPage
     * @param pageSize
     * @return
     * @throws AppException
     
    public QueryResults queryForPage(String sql, Object paramEntity, FWActionPageForm form) throws AppException {
        
        if (form.getPageSize() == null || form.getPageSize() == "" || form.getPageNo() == null || form.getPageNo() == "") {
            throw new AppException("IBatisDAO参数异常，请联系管理员！");
        }
        QueryResults qr = new QueryResults();
        int pagesize = new Integer(form.getPageSize()).intValue();// 每页记录数
        int currentPage = new Integer(form.getPageNo()).intValue();// 当前页面
        int start = (currentPage - 1) * pagesize;// 分页算法
        try {
            List results = getSqlMapClient().queryForList(sql, paramEntity, start, pagesize);
            List total = getSqlMapClient().queryForList(sql, paramEntity);
            qr.setResults(results);// 查询结果集
            qr.setRecordCount(total.size()); // 记录总数
            qr.setPageSize(pagesize);
            qr.setPageNo(new Integer(form.getPageNo()).intValue());
        } catch (SQLException e) {
            throw new AppException(e.getMessage());
        }
        return qr;
    }*/

    
    
    
    
    /**
     * 查询操作，返回Map.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql的参数
     * @param key
     *            将查询结果放入指定属性作为key值的Map对象中
     * @return 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public Map queryForMap(String str, Object obj, String key) throws SQLException {
        return getSqlMapClient().queryForMap(str, obj, key);
    }

    /**
     * 查询操作，返回Map，指定属性作为Map对象的value.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql的参数
     * @param key
     *            将查询结果放入指定属性作为key值的Map对象中
     * @param value
     *            将查询结果执行的指定属性作为Map对象的value
     * @return 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public Map queryForMap(String str, Object obj, String key, String value) throws SQLException {
        return getSqlMapClient().queryForMap(str, obj, key, value);
    }

    /**
     * 查询操作，返回单个对象.
     * 
     * @param str
     *            SqlID
     * @return 返回结果 SQL异常
     * @throws SQLException
     */
    public Object queryForObject(String str) throws SQLException {
        return getSqlMapClient().queryForObject(str);
    }

    /**
     * 查询操作，返回单个对象.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql的参数
     * @return 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public Object queryForObject(String str, Object obj) throws SQLException {
        return getSqlMapClient().queryForObject(str, obj);
    }

    /**
     * 查询操作，返回单个对象.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql的参数
     * @param returnObj
     *            事先生成的对象作为参数装载检索结果
     * @return 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public Object queryForObject(String str, Object obj, Object returnObj) throws SQLException {
        return getSqlMapClient().queryForObject(str, obj, returnObj);
    }

    /**
     * 删除操作
     * 
     * @param str
     *            SqlID
     * @return 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public int delete(String str) throws SQLException {
        return getSqlMapClient().delete(str);
    }

    /**
     * 删除操作
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql的参数
     * @return int 返回结果
     * @throws SQLException
     *             SQL异常
     */
    public int delete(String str, Object obj) throws SQLException {
        return getSqlMapClient().delete(str, obj);
    }
}
