package com.yufei.test.dao;

import cn.org.rapid_framework.page.Page;

import com.yufei.test.model.Test;
import com.yufei.test.query.TestQuery;

public interface TestDao {
    
    /**
     * test
     * 
     * @param query 
     * @return
     */
    Page<Test> queryTestPage(TestQuery query);

}
