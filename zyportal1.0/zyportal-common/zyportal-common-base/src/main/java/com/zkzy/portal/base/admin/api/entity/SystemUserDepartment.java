package com.zkzy.portal.base.admin.api.entity;

import java.io.Serializable;

public class SystemUserDepartment implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYSTEM_USER_DEPARTMENT.ID
     *
     * @mbggenerated Fri Jan 19 12:43:50 CST 2018
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYSTEM_USER_DEPARTMENT.USERID
     *
     * @mbggenerated Fri Jan 19 12:43:50 CST 2018
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SYSTEM_USER_DEPARTMENT.DEPARTMENTID
     *
     * @mbggenerated Fri Jan 19 12:43:50 CST 2018
     */
    private String departmentid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYSTEM_USER_DEPARTMENT.ID
     *
     * @return the value of SYSTEM_USER_DEPARTMENT.ID
     *
     * @mbggenerated Fri Jan 19 12:43:50 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYSTEM_USER_DEPARTMENT.ID
     *
     * @param id the value for SYSTEM_USER_DEPARTMENT.ID
     *
     * @mbggenerated Fri Jan 19 12:43:50 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYSTEM_USER_DEPARTMENT.USERID
     *
     * @return the value of SYSTEM_USER_DEPARTMENT.USERID
     *
     * @mbggenerated Fri Jan 19 12:43:50 CST 2018
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYSTEM_USER_DEPARTMENT.USERID
     *
     * @param userid the value for SYSTEM_USER_DEPARTMENT.USERID
     *
     * @mbggenerated Fri Jan 19 12:43:50 CST 2018
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SYSTEM_USER_DEPARTMENT.DEPARTMENTID
     *
     * @return the value of SYSTEM_USER_DEPARTMENT.DEPARTMENTID
     *
     * @mbggenerated Fri Jan 19 12:43:50 CST 2018
     */
    public String getDepartmentid() {
        return departmentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SYSTEM_USER_DEPARTMENT.DEPARTMENTID
     *
     * @param departmentid the value for SYSTEM_USER_DEPARTMENT.DEPARTMENTID
     *
     * @mbggenerated Fri Jan 19 12:43:50 CST 2018
     */
    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }
}