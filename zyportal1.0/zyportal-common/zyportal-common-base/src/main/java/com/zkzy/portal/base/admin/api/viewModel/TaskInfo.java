package com.zkzy.portal.base.admin.api.viewModel;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 管理定时任务
 */
public class TaskInfo implements Serializable{
	private static final long serialVersionUID = -8054692082716173379L;

	@ApiModelProperty(value = "id", required = false)
	private String id ;

	@ApiModelProperty(value = "任务名称", required = false)
	private String jobName;

	@ApiModelProperty(value = "任务分组", required = false)
	private String jobGroup;

	@ApiModelProperty(value = "任务描述", required = false)
	private String jobDescription;

	@ApiModelProperty(value = "任务状态", required = false)
	private String jobStatus;

	@ApiModelProperty(value = "任务表达式", required = false)
	private String cronExpression;

	@ApiModelProperty(value = "创建时间,后台自动生成", required = false)
	private String createTime;

	@ApiModelProperty(value = "修改时间,后台自动生成", required = false)
	private String modifyTime;







	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}


}