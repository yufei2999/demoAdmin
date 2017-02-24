package com.yufei.test.action;

import cn.org.rapid_framework.page.Page;
import com.yufei.test.model.Test;
import com.yufei.test.query.TestQuery;
import com.yufei.test.service.TestService;
import com.yufei.utils.JsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 *
 */
@Controller
@RequestMapping(value = "/test")
public class TestAction {
    
    private static final Log log = LogFactory.getLog(TestAction.class);
    
    @Autowired
    TestService testService;
    
    /**
     * 测试列表方法
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryTestList")
    public String queryTestList(TestQuery query, Model model) {
        return "test/testList";
    }

    @ResponseBody
    @RequestMapping(value = "/queryTestListAjax")
    public String queryTestListAjax(TestQuery query, Model model) {
        
        Map<String, Object> map = new HashMap<>();
        try {
            
            Page<Test> page = testService.queryTestPage(query);
            
            map.put("total", page.getTotalCount());
            map.put("rows", page.getResult());
            
        } catch (Exception e) {
            log.error("测试方法异常", e);
        }
        
        return JsonUtil.toJSONString(map);
    }
    
    /**
     * 测试页面
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/testPage")
    public String testPage(Model model) {
        
        try {

            model.addAttribute("test", "test content");
            
        } catch (Exception e) {
           log.error("测试方法异常", e);
        }
        
        return "test/testPage";
    }

    public static void main(String[] args) {
        System.out.println("test");
    }
    
}
