package com.yufei.base;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

public abstract class BaseDaoImpl<E, PK extends Serializable> extends SqlSessionDaoSupport implements BaseDao<E, PK> {

    protected static final Log log = LogFactory.getLog(BaseDaoImpl.class);

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public E getById(PK id) throws DataAccessException {
        if (id != null || (id instanceof String && StringUtils.isNotBlank((String) id))) {
            return (E) getSqlSession().selectOne(getFindByPrimaryKeyStatement(), id);
        }
        return null;
    }

    @Override
    public void save(E entity) throws DataAccessException {
        prepareObjectForSave(entity);
        setModelNullValue(entity);
        getSqlSession().insert(getInsertStatement(), entity);
    }

    @Override
    public void deleteByIds(List<PK> ids) throws DataAccessException {
        int affectCount = 0;
        if (CollectionUtils.isNotEmpty(ids)) {
            Map<String, List<PK>> map = new HashMap<>();
            map.put("ids", ids);
            affectCount = getSqlSession().delete(getDeleteStatement(), map);
        }
        log.info("affectCount:" + affectCount);
    }

    @Override
    public void update(E entity) throws DataAccessException {
        prepareObjectForUpdate(entity);
        setModelNullValue(entity);
        int affectCount = getSqlSession().update(getUpdateStatement(), entity);
        log.info("affectCount:" + affectCount);
    }

    @Override
    public List<E> find(PageRequest query) {
        return listQuery(getFindStatement(), query);
    }

    @Override
    public List<E> find(PageRequest query, int limit) {
        return listQuery(getFindStatement(), query, limit);
    }

    @Override
    public Page<E> findPage(PageRequest query) {
        return pageQuery(getFindPageStatement(), query);
    }

    @Override
    public void deleteByEntity(E entity) {
        setModelNullValue(entity);
        getSqlSession().delete(getDeleteStatement(), entity);
    }

    @Override
    public E getObject(PageRequest query) {
        return (E) getSqlSession().selectOne(getFindStatement(), query);
    }

    private void setModelNullValue(E o) {

        Class class1 = o.getClass();
        Method[] aobj = class1.getDeclaredMethods();
        Set<String> hashSet = new HashSet<>();
        // 迭代这些方法
        for (Method method1 : aobj) {
            String s = method1.getName();
            // 取出方法为get的方法
            if (s.startsWith("get") && s.length() > 3) {
                try {
                    Object obj2 = method1.invoke(o, new Object[0]);
                    // 为空白字符串
                    if (obj2 != null && obj2.equals("")) {
                        hashSet.add(s.substring(3));
                    }
                } catch (Exception e) {
                    log.error("some error occurred", e);
                }
            }

        }

        // 迭代这些方法，重设值为null
        for (Method method1 : aobj) {
            String s = method1.getName();
            // 取出方法为get的方法
            if (s.startsWith("set") && s.length() > 3) {
                if (hashSet.contains(s.substring(3))) {
                    try {
                        // 调用设置为null值
                        method1.invoke(o, new Object[]{null});
                    } catch (Exception e) {
                        log.error("some error occurred either", e);
                    }
                }
            }

        }
    }

    /**
     * 用于子类覆盖,在insert之前调用
     *
     * @param o
     */
    protected void prepareObjectForSave(E o) {
    }

    /**
     * 用于子类覆盖,在update之前调用
     *
     * @param o
     */
    protected void prepareObjectForUpdate(E o) {
    }

    private String getFindByPrimaryKeyStatement() {
        return getIbatisSqlMapNamespace() + ".getById";
    }

    private String getInsertStatement() {
        return getIbatisSqlMapNamespace() + ".insert";
    }

    private String getUpdateStatement() {
        return getIbatisSqlMapNamespace() + ".update";
    }

    private String getDeleteStatement() {
        return getIbatisSqlMapNamespace() + ".delete";
    }

    private String getFindStatement() {
        return getIbatisSqlMapNamespace() + ".find";
    }

    private String getFindPageStatement() {
        return getIbatisSqlMapNamespace() + ".findPage";
    }

    private String getCountStatementForPaging(String statementName) {
        return statementName + "Count";
    }

    public String getIbatisSqlMapNamespace() {
        throw new RuntimeException("not yet implement");
    }

    protected Page pageQuery(String statementName, PageRequest pageRequest) {
        return pageQuery(getSqlSession(), statementName, getCountStatementForPaging(statementName), pageRequest);
    }

    public static Page pageQuery(SqlSession sqlSession, String statementName, String countStatementName, PageRequest pageRequest) {

        Number totalCount = (Number) sqlSession.selectOne(countStatementName, pageRequest);
        if (totalCount == null || totalCount.longValue() <= 0) {
            return new Page(pageRequest, 0);
        }

        Page page = new Page(pageRequest, totalCount.intValue());

        RowBounds rowBounds = new RowBounds(page.getFirstResult(), page.getPageSize());
        page.setResult(sqlSession.selectList(statementName, pageRequest, rowBounds));

        return page;
    }

    protected List<E> listQuery(String statementName, PageRequest pageRequest) {
        return getSqlSession().selectList(statementName, pageRequest);
    }

    protected List<E> listQuery(String statementName, Map paramMap) {
        return getSqlSession().selectList(statementName, paramMap);
    }

    protected List<E> listQuery(String statementName, PageRequest pageRequest, int limit) {
        return getSqlSession().selectList(statementName, pageRequest, new RowBounds(0, limit));
    }

}
