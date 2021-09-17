package com.zkzy.portal.dumu.client.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zkzy.portal.common.web.security.model.AbstractAuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Date;

public class AuthSystemUsers extends AbstractAuthUser {
    private String id;
    private String username;
    private String password;
    private String realname;
    private String organizeId;
    private String organizeName;
    private Long dutyId;
    private Long titleId;
    private String email;
    private String lang;
    private String theme;
    private Date firstVisit;
    private Date previousVisit;
    private Date lastVisits;
    private Long loginCount;
    private Long isemployee;
    private String status;
    private String ip;
    private String description;
    private Long isonline;
    private Date createDate;
    private Date updateDate;
    private Long creater;
    private Long modifyer;
    private String tel;
    private String roleName;
    private String roleId;
    private String area;
    private String location;
    private String photourl;
    private String rivergovernorid;//河长id
    private String departmentId;
    private String departmentName;
    private String areaCode;
    private String operamanid;
    private String projectmainid;

    public String getProjectmainid() {
        return projectmainid;
    }

    public void setProjectmainid(String projectmainid) {
        this.projectmainid = projectmainid;
    }

    public String getOperamanid() {
        return operamanid;
    }

    public void setOperamanid(String operamanid) {
        this.operamanid = operamanid;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getRivergovernorid() {
        return rivergovernorid;
    }
    public void setRivergovernorid(String rivergovernorid) {
        this.rivergovernorid = rivergovernorid;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }
    public String getOrganizeId() {
        return organizeId;
    }
    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId == null ? null : organizeId.trim();
    }
    public String getOrganizeName() {
        return organizeName;
    }
    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName == null ? null : organizeName.trim();
    }
    public Long getDutyId() {
        return dutyId;
    }
    public void setDutyId(Long dutyId) {
        this.dutyId = dutyId;
    }
    public Long getTitleId() {
        return titleId;
    }
    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang == null ? null : lang.trim();
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }
    public Date getFirstVisit() {
        return firstVisit;
    }
    public void setFirstVisit(Date firstVisit) {
        this.firstVisit = firstVisit;
    }
    public Date getPreviousVisit() {
        return previousVisit;
    }
    public void setPreviousVisit(Date previousVisit) {
        this.previousVisit = previousVisit;
    }
    public Date getLastVisits() {
        return lastVisits;
    }
    public void setLastVisits(Date lastVisits) {
        this.lastVisits = lastVisits;
    }
    public Long getLoginCount() {
        return loginCount;
    }
    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }
    public Long getIsemployee() {
        return isemployee;
    }
    public void setIsemployee(Long isemployee) {
        this.isemployee = isemployee;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    public Long getIsonline() {
        return isonline;
    }
    public void setIsonline(Long isonline) {
        this.isonline = isonline;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Long getCreater() {
        return creater;
    }
    public void setCreater(Long creater) {
        this.creater = creater;
    }
    public Long getModifyer() {
        return modifyer;
    }
    public void setModifyer(Long modifyer) {
        this.modifyer = modifyer;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }
    public String getPhotourl() {
        return photourl;
    }
    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
    private Collection<SimpleGrantedAuthority> authorities;
    private boolean enabled;

    public AuthSystemUsers(String id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


//    ----------------------------------------------------------------------------------------------------
    private String loginName;
    private String name;
    private String phone;
    private String mobile;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}