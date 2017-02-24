package com.yufei.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pc on 2016-09-29.
 */
@Controller
public class MainAction {

    /**
     * 到主页
     * @return
     */
    @RequestMapping("/main")
    public String toMain() {
        return "main";
    }

}
