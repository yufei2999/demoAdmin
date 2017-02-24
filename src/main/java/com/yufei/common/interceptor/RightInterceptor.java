package com.yufei.common.interceptor;

import com.yufei.utils.DataTypeUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by pc on 2016-09-29.
 */
public class RightInterceptor implements HandlerInterceptor {

    private List<String> excludedUrls;

    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String requestUri = request.getRequestURI();
        for (String url : excludedUrls) {
            if (requestUri.endsWith(url)) {
                return true;
            }
        }

        if (request.getSession().getAttribute(DataTypeUtils.SESSION_LOGIN_USER_KEY) == null) {
            response.sendRedirect(request.getContextPath());
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
