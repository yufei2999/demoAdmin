package com.yufei.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.rapid_framework.page.Page;

import com.yufei.test.dao.TestDao;
import com.yufei.test.model.Test;
import com.yufei.test.query.TestQuery;
import com.yufei.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;
    
    @Override
    public Page<Test> queryTestPage(TestQuery query) {
        return testDao.queryTestPage(query);
    }

}
