package com.yufei.sys.model;

import cn.org.rapid_framework.util.DateConvertUtils;
import com.yufei.base.BaseBean;
import com.yufei.utils.BeanUtil;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class SysRoleFunc extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "SysRoleFunc";
	public static final String ALIAS_ID = "主键";
	public static final String ALIAS_ROLE_ID = "角色id";
	public static final String ALIAS_FUNC_ID = "权限id";
	public static final String ALIAS_CREATOR = "创建人";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_LAST_MODIFIER = "最后修改人";
	public static final String ALIAS_LAST_MODIFY_TIME = "最后修改时间";
	public static final String ALIAS_DELETED = "删除标记：0删除；1正常";
	
	//date formats
	public static final String FORMAT_CREATE_TIME = DATE_TIME_FORMAT;
	public static final String FORMAT_LAST_MODIFY_TIME = DATE_TIME_FORMAT;
	
	//columns START
	private String id;
	private String roleId;
	private String funcId;
	private String creator;
	@DateTimeFormat(pattern = DATE_TIME_FORMAT)
	private java.util.Date createTime;
	private String lastModifier;
	@DateTimeFormat(pattern = DATE_TIME_FORMAT)
	private java.util.Date lastModifyTime;
	private Integer deleted;
	//columns END

	public SysRoleFunc(){
	}

	public SysRoleFunc(String id){
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}
	public String getId() {
		return this.id;
	}
	public void setRoleId(String value) {
		this.roleId = value;
	}
	public String getRoleId() {
		return this.roleId;
	}
	public void setFuncId(String value) {
		this.funcId = value;
	}
	public String getFuncId() {
		return this.funcId;
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
			.append("RoleId",getRoleId())
			.append("FuncId",getFuncId())
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
		sb.append(ALIAS_ROLE_ID+":").append(roleId).append(separator);
		sb.append(ALIAS_FUNC_ID+":").append(funcId).append(separator);
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
		if(obj instanceof SysRoleFunc == false) return false;
		if(this == obj) return true;
		SysRoleFunc other = (SysRoleFunc)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

