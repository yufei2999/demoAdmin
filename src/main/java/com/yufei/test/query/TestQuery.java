package com.yufei.test.query;

import com.yufei.base.BaseQuery;
import com.yufei.test.model.Test;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TestQuery extends BaseQuery<Test> {

    private static final long serialVersionUID = -1722338476002533647L;
    
    private java.math.BigDecimal testId;
    private java.lang.String content;
    // 创建时间
    private String createTimeBegin;
    private String createTimeEnd;
    
    public java.math.BigDecimal getTestId() {
        return testId;
    }
    public void setTestId(java.math.BigDecimal testId) {
        this.testId = testId;
    }
    public java.lang.String getContent() {
        return content;
    }
    public void setContent(java.lang.String content) {
        this.content = content;
    }
    public String getCreateTimeBegin() {
        return createTimeBegin;
    }
    public void setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }
    public String getCreateTimeEnd() {
        return createTimeEnd;
    }
    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
