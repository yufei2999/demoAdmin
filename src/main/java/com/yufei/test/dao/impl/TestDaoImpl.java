package com.yufei.test.dao.impl;

import org.springframework.stereotype.Repository;

import cn.org.rapid_framework.page.Page;

import com.yufei.base.BaseDaoImpl;
import com.yufei.test.dao.TestDao;
import com.yufei.test.model.Test;
import com.yufei.test.query.TestQuery;

@Repository("testDao")
public class TestDaoImpl extends BaseDaoImpl<Test, String> implements TestDao {

    @Override
    public Page<Test> queryTestPage(TestQuery query) {
        return pageQuery("manual-Test.queryTestPage", query);
    }

}
