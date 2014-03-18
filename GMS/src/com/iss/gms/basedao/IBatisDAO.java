package com.iss.gms.basedao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public class IBatisDAO extends SqlMapClientDaoSupport {

    /**
     * �������.
     * 
     * @param str
     *            SqlID
     * @return ���ؽ��
     * @throws SQLException
     *             SQL�쳣
     */
    public Object insert(String str) throws SQLException {

        return getSqlMapClient().insert(str);
    }

    /**
     * �������.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql�Ĳ���
     * @return ���ؽ��
     * @throws DataAccessException
     *             SQL�쳣
     */
    public Object insert(String str, Object obj) throws DataAccessException {
        return getSqlMapClientTemplate().insert(str, obj);
    }

    /**
     * ���²���.
     * 
     * @param str
     *            SqlID
     * @throws SQLException
     *             SQL�쳣
     */
    public int update(String str) throws SQLException {
        return getSqlMapClient().update(str);
    }

    /**
     * ���²���.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql�Ĳ���
     * @throws SQLException
     *             SQL�쳣
     */
    public int update(String str, Object obj) throws SQLException {
        return getSqlMapClient().update(str, obj);
    }

    /**
     * @param str
     *            SqlID
     * @return ���ؽ��
     * @throws SQLException
     *             SQL�쳣
     */
    public List queryForList(String str) throws SQLException {
        return getSqlMapClient().queryForList(str);
    }

    /**
     * ��ѯ���������ض��󼯺�.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql�Ĳ���
     * @return ���ؽ��
     * @throws SQLException
     *             SQL�쳣
     */
    public List queryForList(String str, Object obj) throws SQLException {
        return getSqlMapClient().queryForList(str, obj);
    }

    /**
     * ��ѯ���������ض��󼯺ϣ�֧�ִ������.
     * 
     * @param str
     *            SqlID
     * @param index
     * @param length
     * @return ���ؽ��
     * @throws SQLException
     *             SQL�쳣
     */
    public List queryForList(String str, int index, int length) throws SQLException {
        return getSqlMapClient().queryForList(str, index, length);
    }
    
    /**
     * ��ѯ���������ض��󼯺ϣ�֧�ִ������������ҳ����.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql�Ĳ���
     * @param index
     *            ��ʼ��
     * @param length
     *            ��󳤶�
     * @return ���ؽ��
     * @throws SQLException
     *             SQL�쳣
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
            throw new AppException("IBatisDAO�����쳣������ϵ����Ա��");
        }
        QueryResults qr = new QueryResults();
        int pagesize = new Integer(form.getPageSize()).intValue();// ÿҳ��¼��
        int currentPage = new Integer(form.getPageNo()).intValue();// ��ǰҳ��
        int start = (currentPage - 1) * pagesize;// ��ҳ�㷨
        try {
            List results = getSqlMapClient().queryForList(sql, paramEntity, start, pagesize);
            List total = getSqlMapClient().queryForList(sql, paramEntity);
            qr.setResults(results);// ��ѯ�����
            qr.setRecordCount(total.size()); // ��¼����
            qr.setPageSize(pagesize);
            qr.setPageNo(new Integer(form.getPageNo()).intValue());
        } catch (SQLException e) {
            throw new AppException(e.getMessage());
        }
        return qr;
    }*/

    
    
    
    
    /**
     * ��ѯ����������Map.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql�Ĳ���
     * @param key
     *            ����ѯ�������ָ��������Ϊkeyֵ��Map������
     * @return ���ؽ��
     * @throws SQLException
     *             SQL�쳣
     */
    public Map queryForMap(String str, Object obj, String key) throws SQLException {
        return getSqlMapClient().queryForMap(str, obj, key);
    }

    /**
     * ��ѯ����������Map��ָ��������ΪMap�����value.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql�Ĳ���
     * @param key
     *            ����ѯ�������ָ��������Ϊkeyֵ��Map������
     * @param value
     *            ����ѯ���ִ�е�ָ��������ΪMap�����value
     * @return ���ؽ��
     * @throws SQLException
     *             SQL�쳣
     */
    public Map queryForMap(String str, Object obj, String key, String value) throws SQLException {
        return getSqlMapClient().queryForMap(str, obj, key, value);
    }

    /**
     * ��ѯ���������ص�������.
     * 
     * @param str
     *            SqlID
     * @return ���ؽ�� SQL�쳣
     * @throws SQLException
     */
    public Object queryForObject(String str) throws SQLException {
        return getSqlMapClient().queryForObject(str);
    }

    /**
     * ��ѯ���������ص�������.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql�Ĳ���
     * @return ���ؽ��
     * @throws SQLException
     *             SQL�쳣
     */
    public Object queryForObject(String str, Object obj) throws SQLException {
        return getSqlMapClient().queryForObject(str, obj);
    }

    /**
     * ��ѯ���������ص�������.
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql�Ĳ���
     * @param returnObj
     *            �������ɵĶ�����Ϊ����װ�ؼ������
     * @return ���ؽ��
     * @throws SQLException
     *             SQL�쳣
     */
    public Object queryForObject(String str, Object obj, Object returnObj) throws SQLException {
        return getSqlMapClient().queryForObject(str, obj, returnObj);
    }

    /**
     * ɾ������
     * 
     * @param str
     *            SqlID
     * @return ���ؽ��
     * @throws SQLException
     *             SQL�쳣
     */
    public int delete(String str) throws SQLException {
        return getSqlMapClient().delete(str);
    }

    /**
     * ɾ������
     * 
     * @param str
     *            SqlID
     * @param obj
     *            Sql�Ĳ���
     * @return int ���ؽ��
     * @throws SQLException
     *             SQL�쳣
     */
    public int delete(String str, Object obj) throws SQLException {
        return getSqlMapClient().delete(str, obj);
    }
}
