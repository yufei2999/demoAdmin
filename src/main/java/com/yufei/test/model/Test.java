package com.yufei.test.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import cn.org.rapid_framework.util.DateConvertUtils;

import com.yufei.base.BaseBean;
import com.yufei.utils.BeanUtil;

public class Test extends BaseBean {
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "Test";
	public static final String ALIAS_TEST_ID = "主键id";
	public static final String ALIAS_CONTENT = "名称";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_FORMAT;
	
	//columns START
	private java.math.BigDecimal testId;
	private java.lang.String content;
	private java.util.Date createTime;
	//columns END

	public Test(){
	}

	public Test(java.math.BigDecimal testId){
		this.testId = testId;
	}

	public void setTestId(java.math.BigDecimal value) {
		this.testId = value;
	}
	
	public java.math.BigDecimal getTestId() {
		return this.testId;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("TestId",getTestId())
			.append("Content",getContent())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public String toString(String separator) {
		
	    BeanUtil.setEmptyNoException(this);
		StringBuffer sb = new StringBuffer();

			sb.append(ALIAS_TEST_ID+":").append(testId).append(separator);
			sb.append(ALIAS_CONTENT+":").append(content).append(separator);
			sb.append(ALIAS_CREATE_TIME+":").append(getCreateTimeString()).append(separator);
		
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTestId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Test == false) return false;
		if(this == obj) return true;
		Test other = (Test)obj;
		return new EqualsBuilder()
			.append(getTestId(),other.getTestId())
			.isEquals();
	}
}

