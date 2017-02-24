package com.yufei.test.service;

import cn.org.rapid_framework.page.Page;

import com.yufei.test.model.Test;
import com.yufei.test.query.TestQuery;

public interface TestService {
    
    /**
     * test
     * 
     * @param query 
     * @return 
     */
    Page<Test> queryTestPage(TestQuery query);

}
