package com.zkzy.portal.base.admin.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class JobB implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.ID
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.SHOWNAME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String showname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.JOBNAME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String jobname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.GROUPID
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String groupid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.DESCRIPTION
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.STATUS
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.CRON
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String cron;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.CREATETIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.MODIFYTIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String modifytime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.CLASSORBEAN
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String classorbean;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.USEMETHOD
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String usemethod;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.NEXTTIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String nexttime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.GROUPNAME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String groupname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.STARTTIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private String starttime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_B.RUNCOUNT
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    private BigDecimal runcount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.ID
     *
     * @return the value of JOB_B.ID
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.ID
     *
     * @param id the value for JOB_B.ID
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.SHOWNAME
     *
     * @return the value of JOB_B.SHOWNAME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getShowname() {
        return showname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.SHOWNAME
     *
     * @param showname the value for JOB_B.SHOWNAME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setShowname(String showname) {
        this.showname = showname == null ? null : showname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.JOBNAME
     *
     * @return the value of JOB_B.JOBNAME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getJobname() {
        return jobname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.JOBNAME
     *
     * @param jobname the value for JOB_B.JOBNAME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setJobname(String jobname) {
        this.jobname = jobname == null ? null : jobname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.GROUPID
     *
     * @return the value of JOB_B.GROUPID
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getGroupid() {
        return groupid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.GROUPID
     *
     * @param groupid the value for JOB_B.GROUPID
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setGroupid(String groupid) {
        this.groupid = groupid == null ? null : groupid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.DESCRIPTION
     *
     * @return the value of JOB_B.DESCRIPTION
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.DESCRIPTION
     *
     * @param description the value for JOB_B.DESCRIPTION
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.STATUS
     *
     * @return the value of JOB_B.STATUS
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.STATUS
     *
     * @param status the value for JOB_B.STATUS
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.CRON
     *
     * @return the value of JOB_B.CRON
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getCron() {
        return cron;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.CRON
     *
     * @param cron the value for JOB_B.CRON
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.CREATETIME
     *
     * @return the value of JOB_B.CREATETIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.CREATETIME
     *
     * @param createtime the value for JOB_B.CREATETIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.MODIFYTIME
     *
     * @return the value of JOB_B.MODIFYTIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getModifytime() {
        return modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.MODIFYTIME
     *
     * @param modifytime the value for JOB_B.MODIFYTIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setModifytime(String modifytime) {
        this.modifytime = modifytime == null ? null : modifytime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.CLASSORBEAN
     *
     * @return the value of JOB_B.CLASSORBEAN
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getClassorbean() {
        return classorbean;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.CLASSORBEAN
     *
     * @param classorbean the value for JOB_B.CLASSORBEAN
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setClassorbean(String classorbean) {
        this.classorbean = classorbean == null ? null : classorbean.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.USEMETHOD
     *
     * @return the value of JOB_B.USEMETHOD
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getUsemethod() {
        return usemethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.USEMETHOD
     *
     * @param usemethod the value for JOB_B.USEMETHOD
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setUsemethod(String usemethod) {
        this.usemethod = usemethod == null ? null : usemethod.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.NEXTTIME
     *
     * @return the value of JOB_B.NEXTTIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getNexttime() {
        return nexttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.NEXTTIME
     *
     * @param nexttime the value for JOB_B.NEXTTIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setNexttime(String nexttime) {
        this.nexttime = nexttime == null ? null : nexttime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.GROUPNAME
     *
     * @return the value of JOB_B.GROUPNAME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.GROUPNAME
     *
     * @param groupname the value for JOB_B.GROUPNAME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.STARTTIME
     *
     * @return the value of JOB_B.STARTTIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.STARTTIME
     *
     * @param starttime the value for JOB_B.STARTTIME
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_B.RUNCOUNT
     *
     * @return the value of JOB_B.RUNCOUNT
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public BigDecimal getRuncount() {
        return runcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_B.RUNCOUNT
     *
     * @param runcount the value for JOB_B.RUNCOUNT
     *
     * @mbggenerated Fri Jun 30 13:50:07 CST 2017
     */
    public void setRuncount(BigDecimal runcount) {
        this.runcount = runcount;
    }
}