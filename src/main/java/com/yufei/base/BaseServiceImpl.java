package com.yufei.base;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 2016-10-25.
 */
@Transactional
public abstract class BaseServiceImpl<E, PK extends Serializable> implements BaseService<E, PK> {

    protected Log log = LogFactory.getLog(getClass());

    protected abstract BaseDao<E, PK> getEntityDao();

    @Transactional(readOnly = true)
    public E getById(PK id) throws DataAccessException {
        return getEntityDao().getById(id);
    }

    @Transactional
    public void save(E entity) throws DataAccessException {
        getEntityDao().save(entity);
    }

    @Transactional
    public void deleteByIds(List<PK> ids) throws DataAccessException {
        getEntityDao().deleteByIds(ids);
    }

    @Transactional
    public void update(E entity) throws DataAccessException {
        getEntityDao().update(entity);
    }

    @Transactional(readOnly = true)
    public List<E> find(PageRequest query) {
        return getEntityDao().find(query);
    }

    @Transactional(readOnly = true)
    public List<E> find(PageRequest query, int limit) {
        return getEntityDao().find(query, limit);
    }

    @Transactional(readOnly = true)
    public Page<E> findPage(PageRequest query) {
        return getEntityDao().findPage(query);
    }

    @Transactional
    public void deleteByEntity(E entity) throws DataAccessException {
        getEntityDao().deleteByEntity(entity);
    }

    @Transactional(readOnly = true)
    public E getObject(PageRequest query) throws DataAccessException {
        return getEntityDao().getObject(query);
    }

}
