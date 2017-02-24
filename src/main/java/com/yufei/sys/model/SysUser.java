package com.yufei.sys.model;

import cn.org.rapid_framework.util.DateConvertUtils;
import com.yufei.base.BaseBean;
import com.yufei.utils.BeanUtil;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class SysUser extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "SysUser";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_USER_NAME = "用户名";
	public static final String ALIAS_SHOW_NAME = "显示名";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_MOBILE = "手机号";
	public static final String ALIAS_STATUS = "状态：0停用；1启用";
	public static final String ALIAS_LAST_LOGIN_IP = "最后登录ip";
	public static final String ALIAS_LAST_LOGIN_TIME = "最后登录时间";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_LAST_MODIFIER = "最后修改人";
	public static final String ALIAS_LAST_MODIFY_TIME = "最后修改时间";
	public static final String ALIAS_DELETED = "删除标记：0删除；1正常";
	
	//date formats
	public static final String FORMAT_LAST_LOGIN_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_LAST_MODIFY_TIME = DATE_TIME_FORMAT;
	
	//columns START
	private String id;
	private String userName;
	private String showName;
	private String password;
	private String mobile;
	private Integer status;
	private String lastLoginIp;
	@DateTimeFormat(pattern = DATE_TIME_FORMAT)
	private java.util.Date lastLoginTime;
	private String remark;
	private String creator;
	@DateTimeFormat(pattern = DATE_TIME_FORMAT)
	private java.util.Date createTime;
	private String lastModifier;
	@DateTimeFormat(pattern = DATE_TIME_FORMAT)
	private java.util.Date lastModifyTime;
	private Integer deleted;
	//columns END

	public SysUser(){
	}

	public SysUser(String id){
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}
	public String getId() {
		return this.id;
	}
	public void setUserName(String value) {
		this.userName = value;
	}
	public String getUserName() {
		return this.userName;
	}
	public void setShowName(String value) {
		this.showName = value;
	}
	public String getShowName() {
		return this.showName;
	}
	public void setPassword(String value) {
		this.password = value;
	}
	public String getPassword() {
		return this.password;
	}
	public void setMobile(String value) {
		this.mobile = value;
	}
	public String getMobile() {
		return this.mobile;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	public Integer getStatus() {
		return this.status;
	}
	public void setLastLoginIp(String value) {
		this.lastLoginIp = value;
	}
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}
	public String getLastLoginTimeString() {
		return DateConvertUtils.format(getLastLoginTime(), FORMAT_LAST_LOGIN_TIME);
	}
	public void setLastLoginTimeString(String value) {
		setLastLoginTime(DateConvertUtils.parse(value, FORMAT_LAST_LOGIN_TIME,java.util.Date.class));
	}
	public void setLastLoginTime(java.util.Date value) {
		this.lastLoginTime = value;
	}
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setCreator(String value) {
		this.creator = value;
	}
	public String getCreator() {
		return this.creator;
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
	public void setLastModifier(String value) {
		this.lastModifier = value;
	}
	public String getLastModifier() {
		return this.lastModifier;
	}
	public String getLastModifyTimeString() {
		return DateConvertUtils.format(getLastModifyTime(), FORMAT_LAST_MODIFY_TIME);
	}
	public void setLastModifyTimeString(String value) {
		setLastModifyTime(DateConvertUtils.parse(value, FORMAT_LAST_MODIFY_TIME,java.util.Date.class));
	}
	public void setLastModifyTime(java.util.Date value) {
		this.lastModifyTime = value;
	}
	public java.util.Date getLastModifyTime() {
		return this.lastModifyTime;
	}
	public void setDeleted(Integer value) {
		this.deleted = value;
	}
	public Integer getDeleted() {
		return this.deleted;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserName",getUserName())
			.append("ShowName",getShowName())
			.append("Password",getPassword())
			.append("Mobile",getMobile())
			.append("Status",getStatus())
			.append("LastLoginIp",getLastLoginIp())
			.append("LastLoginTime",getLastLoginTime())
			.append("Remark",getRemark())
			.append("Creator",getCreator())
			.append("CreateTime",getCreateTime())
			.append("LastModifier",getLastModifier())
			.append("LastModifyTime",getLastModifyTime())
			.append("Deleted",getDeleted())
			.toString();
	}
	
	public String toString(String separator) {
	    BeanUtil.setEmptyNoException(this);
		StringBuffer sb = new StringBuffer();
		sb.append(ALIAS_ID+":").append(id).append(separator);
		sb.append(ALIAS_USER_NAME+":").append(userName).append(separator);
		sb.append(ALIAS_SHOW_NAME+":").append(showName).append(separator);
		sb.append(ALIAS_PASSWORD+":").append(password).append(separator);
		sb.append(ALIAS_MOBILE+":").append(mobile).append(separator);
		sb.append(ALIAS_STATUS+":").append(status).append(separator);
		sb.append(ALIAS_LAST_LOGIN_IP+":").append(lastLoginIp).append(separator);
		sb.append(ALIAS_LAST_LOGIN_TIME+":").append(getLastLoginTimeString()).append(separator);
		sb.append(ALIAS_REMARK+":").append(remark).append(separator);
		sb.append(ALIAS_CREATOR+":").append(creator).append(separator);
		sb.append(ALIAS_CREATE_TIME+":").append(getCreateTimeString()).append(separator);
		sb.append(ALIAS_LAST_MODIFIER+":").append(lastModifier).append(separator);
		sb.append(ALIAS_LAST_MODIFY_TIME+":").append(getLastModifyTimeString()).append(separator);
		sb.append(ALIAS_DELETED+":").append(deleted).append(separator);
		return sb.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysUser == false) return false;
		if(this == obj) return true;
		SysUser other = (SysUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

