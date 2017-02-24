package com.yufei.base;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pc on 2016-10-25.
 */
public interface BaseDao<E, PK extends Serializable> {

    /**
     * 查询-by id
     *
     * @param id
     * @return
     */
    public E getById(PK id);

    /**
     * 新增
     *
     * @param entity
     */
    public void save(E entity);

    /**
     * 删除-by ids
     *
     * @param ids
     */
    public void deleteByIds(List<PK> ids);

    /**
     * 更新
     *
     * @param entity
     */
    public void update(E entity);

    /**
     * 列表查询
     *
     * @param query
     * @return
     */
    public List<E> find(PageRequest query);

    /**
     * 列表查询-限制条数
     *
     * @param query
     * @param limit
     * @return
     */
    public List<E> find(PageRequest query, int limit);

    /**
     * 列表查询-分页
     *
     * @param query
     * @return
     */
    public Page<E> findPage(PageRequest query);

    /**
     * 删除-by entity
     *
     * @param entity
     * @throws DataAccessException
     */
    public void deleteByEntity(E entity) throws DataAccessException;

    /**
     * 查询-by entity
     *
     * @param query
     * @return
     */
    public E getObject(PageRequest query);

}
