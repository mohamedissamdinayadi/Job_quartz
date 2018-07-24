package com.jverson.quartz.job;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 import java.io.Serializable;
@Entity
 public class ScheduleJob implements Serializable{
	private static final long serialVersionUID = 1L;
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String jobId;
    
    private String jobName;
    
    private String jobGroup;
    
    private String jobStatus;
    
    private String cronExpression;

    private String description;
    
    private String interfaceName; 
    
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
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
	public String getDesc() {
		return description;
	}
	public void setDesc(String desc) {
		this.description = desc;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	@Override
	public String toString() {
		return "ScheduleJob [jobId=" + jobId + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", jobStatus="
				+ jobStatus + ", cronExpression=" + cronExpression + ", description=" + description + ", interfaceName="
				+ interfaceName + "]";
	}



}
