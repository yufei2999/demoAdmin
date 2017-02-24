package com.yufei.sys.query;

import com.yufei.base.BaseQuery;
import com.yufei.sys.model.SysRoleFunc;
import java.util.Calendar;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class SysRoleFuncQuery extends BaseQuery<SysRoleFunc> {
    
    private static final long serialVersionUID = 1L;

	/** 主键 */
	private String id;
	/** 角色id */
	private String roleId;
	/** 权限id */
	private String funcId;
	/** 创建人 */
	private String creator;
	/** 创建时间 */
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date createTimeBegin;
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date createTimeEnd;
	/** 最后修改人 */
	private String lastModifier;
	/** 最后修改时间 */
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date lastModifyTimeBegin;
	@DateTimeFormat(pattern = DATE_FORMAT)
	private java.util.Date lastModifyTimeEnd;
	/** 删除标记：0删除；1正常 */
	private Integer deleted;
	public String getId() {
		return this.id;
	}
	public void setId(String value) {
		this.id = value;
	}
	public String getRoleId() {
		return this.roleId;
	}
	public void setRoleId(String value) {
		this.roleId = value;
	}
	public String getFuncId() {
		return this.funcId;
	}
	public void setFuncId(String value) {
		this.funcId = value;
	}
	public String getCreator() {
		return this.creator;
	}
	public void setCreator(String value) {
		this.creator = value;
	}
	public java.util.Date getCreateTimeBegin() {
		return this.createTimeBegin;
	}
	public void setCreateTimeBegin(java.util.Date value) {
		this.createTimeBegin = value;
	}
	public java.util.Date getCreateTimeEnd() {
		return this.createTimeEnd;
	}
	public void setCreateTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.createTimeEnd = calendar.getTime();
		}else {
			this.createTimeEnd = value;
		}
	}
	public String getLastModifier() {
		return this.lastModifier;
	}
	public void setLastModifier(String value) {
		this.lastModifier = value;
	}
	public java.util.Date getLastModifyTimeBegin() {
		return this.lastModifyTimeBegin;
	}
	public void setLastModifyTimeBegin(java.util.Date value) {
		this.lastModifyTimeBegin = value;
	}
	public java.util.Date getLastModifyTimeEnd() {
		return this.lastModifyTimeEnd;
	}
	public void setLastModifyTimeEnd(java.util.Date value) {
		if(value != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(value);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			this.lastModifyTimeEnd = calendar.getTime();
		}else {
			this.lastModifyTimeEnd = value;
		}
	}
	public Integer getDeleted() {
		return this.deleted;
	}
	public void setDeleted(Integer value) {
		this.deleted = value;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

