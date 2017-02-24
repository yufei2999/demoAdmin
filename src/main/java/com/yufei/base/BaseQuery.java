package com.yufei.base;

import cn.org.rapid_framework.page.PageRequest;

public class BaseQuery<T> extends PageRequest<T> {
    
    private static final long serialVersionUID = -2322274834038021800L;
    
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final String DEFAULT_SORT_COLUMNS = "create_time desc";
    public static final String QUERY_LIKE = "1";
    public static final String QUERY_EQUAL = "0";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 查询类型：0精确；1模糊
     */
    private String queryType = QUERY_EQUAL;

    public BaseQuery() {
        setPageSize(DEFAULT_PAGE_SIZE);
        setPageNumber(DEFAULT_PAGE_NUMBER);
        setSortColumns(DEFAULT_SORT_COLUMNS);
    }

    public String getQueryType() {
        return queryType;
    }
    public void setQueryType(String queryType) {
        if(!BaseQuery.QUERY_EQUAL.equals(queryType) && !BaseQuery.QUERY_LIKE.equals(queryType)){
            this.queryType = BaseQuery.QUERY_EQUAL;
        }else {
            this.queryType = queryType;
        }
    }
    
}
