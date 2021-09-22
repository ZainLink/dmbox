package com.zkzy.portal.base.admin.api.viewModel.check;

import com.zkzy.portal.base.admin.api.entity.SystemNodefile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName CheckDepSafetyModel
 * @Description TODO
 * @Author gezb
 * @Date 2020-07-01 8:34
 **/
public class CheckDepSafetyModel implements Serializable {
    private String id;

    private String shname;

    private String areaname;

    private String areacode;

    private String uname;

    private String uphone;

    private String uaddress;

    private String shid;

    private String inspectionName;

    private String originator;

    private String originatorid;

    private String originatorDate;

    private Short isCheck;

    private String checkuser;

    private String checkuserid;

    private String checkDate;

    private Short isProblem;

    private Short rectifyState;

    private String rectifyUser;

    private String rectifyuserid;

    private String rectifyDate;

    private String taskId;

    private String detailid;

    private String depsafetyid;

    private String tyshxydm;

    private String zzJyz;

    private String zzName;

    private String zzAddress;

    private String zzMtname;

    private String yhFzr;

    private String yhFzrdh;

    private String yhDelxr;

    private String yhDelxrdh;

    private String yhSssq;

    private String gqGqqy;

    private String gqQyfzr;

    private String gqQyfzrdh;

    private String gqSqg;

    private String gqSqgdh;

    private Short gqGqxy;

    private Short gqAqgzs;

    private BigDecimal gqFiveGpsl;

    private BigDecimal gqFiveGplx;

    private BigDecimal gqFifteenGpsl;

    private BigDecimal gqFifteenGplx;

    private BigDecimal gqFiftyGpsl;

    private BigDecimal gqFiftyGplx;

    private Short fhyqLsaq;

    private Short fhyqGlzd;

    private Short fhyqAqjcb;

    private Short fhyqZcb;

    private Short sbAzqk;

    private Short sbWbqk;

    private Short sbYxqk;

    private BigDecimal pjZjsl;

    private Short pjSfmhz;

    private Short pjSfbh;

    private String pjZjsccj;

    private Short pjSfgqqygp;

    private Short pjSfgqgp;

    private Short pjSfktj;

    private Short pjSfzyf;

    private String pjTyqsccj;

    private Short pjSfbfrg;

    private Short pjGdfs;

    private Short pjSfctwom;

    private Short pjSfygcq;

    private String xjry;

    private String detailcheckuserid;

    private String xjrq;

    private String befRemark;

    private String aftRemark;

    private List<SystemNodefile> mtFile;

    private List<SystemNodefile> cfFile;

    private List<SystemNodefile> yhqzFile;

    private List<SystemNodefile> xjqzFile;

    private List<SystemNodefile> zgFile;

    public List<SystemNodefile> getZgFile() {
        return zgFile;
    }

    public void setZgFile(List<SystemNodefile> zgFile) {
        this.zgFile = zgFile;
    }

    public List<SystemNodefile> getMtFile() {
        return mtFile;
    }

    public void setMtFile(List<SystemNodefile> mtFile) {
        this.mtFile = mtFile;
    }

    public List<SystemNodefile> getCfFile() {
        return cfFile;
    }

    public void setCfFile(List<SystemNodefile> cfFile) {
        this.cfFile = cfFile;
    }

    public List<SystemNodefile> getYhqzFile() {
        return yhqzFile;
    }

    public void setYhqzFile(List<SystemNodefile> yhqzFile) {
        this.yhqzFile = yhqzFile;
    }

    public List<SystemNodefile> getXjqzFile() {
        return xjqzFile;
    }

    public void setXjqzFile(List<SystemNodefile> xjqzFile) {
        this.xjqzFile = xjqzFile;
    }

    public String getDetailcheckuserid() {
        return detailcheckuserid;
    }

    public void setDetailcheckuserid(String detailcheckuserid) {
        this.detailcheckuserid = detailcheckuserid;
    }

    public BigDecimal getGqFiveGpsl() {
        return gqFiveGpsl;
    }

    public void setGqFiveGpsl(BigDecimal gqFiveGpsl) {
        this.gqFiveGpsl = gqFiveGpsl;
    }

    public BigDecimal getGqFiveGplx() {
        return gqFiveGplx;
    }

    public void setGqFiveGplx(BigDecimal gqFiveGplx) {
        this.gqFiveGplx = gqFiveGplx;
    }

    public BigDecimal getGqFifteenGpsl() {
        return gqFifteenGpsl;
    }

    public void setGqFifteenGpsl(BigDecimal gqFifteenGpsl) {
        this.gqFifteenGpsl = gqFifteenGpsl;
    }

    public BigDecimal getGqFifteenGplx() {
        return gqFifteenGplx;
    }

    public void setGqFifteenGplx(BigDecimal gqFifteenGplx) {
        this.gqFifteenGplx = gqFifteenGplx;
    }

    public BigDecimal getGqFiftyGpsl() {
        return gqFiftyGpsl;
    }

    public void setGqFiftyGpsl(BigDecimal gqFiftyGpsl) {
        this.gqFiftyGpsl = gqFiftyGpsl;
    }

    public BigDecimal getGqFiftyGplx() {
        return gqFiftyGplx;
    }

    public void setGqFiftyGplx(BigDecimal gqFiftyGplx) {
        this.gqFiftyGplx = gqFiftyGplx;
    }

    public String getCheckuser() {
        return checkuser;
    }

    public void setCheckuser(String checkuser) {
        this.checkuser = checkuser;
    }

    public String getCheckuserid() {
        return checkuserid;
    }

    public void setCheckuserid(String checkuserid) {
        this.checkuserid = checkuserid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShname() {
        return shname;
    }

    public void setShname(String shname) {
        this.shname = shname;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
    }

    public String getInspectionName() {
        return inspectionName;
    }

    public void setInspectionName(String inspectionName) {
        this.inspectionName = inspectionName;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getOriginatorid() {
        return originatorid;
    }

    public void setOriginatorid(String originatorid) {
        this.originatorid = originatorid;
    }

    public String getOriginatorDate() {
        return originatorDate;
    }

    public void setOriginatorDate(String originatorDate) {
        this.originatorDate = originatorDate;
    }

    public Short getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Short isCheck) {
        this.isCheck = isCheck;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public Short getIsProblem() {
        return isProblem;
    }

    public void setIsProblem(Short isProblem) {
        this.isProblem = isProblem;
    }

    public Short getRectifyState() {
        return rectifyState;
    }

    public void setRectifyState(Short rectifyState) {
        this.rectifyState = rectifyState;
    }

    public String getRectifyUser() {
        return rectifyUser;
    }

    public void setRectifyUser(String rectifyUser) {
        this.rectifyUser = rectifyUser;
    }

    public String getRectifyuserid() {
        return rectifyuserid;
    }

    public void setRectifyuserid(String rectifyuserid) {
        this.rectifyuserid = rectifyuserid;
    }

    public String getRectifyDate() {
        return rectifyDate;
    }

    public void setRectifyDate(String rectifyDate) {
        this.rectifyDate = rectifyDate;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDetailid() {
        return detailid;
    }

    public void setDetailid(String detailid) {
        this.detailid = detailid;
    }

    public String getDepsafetyid() {
        return depsafetyid;
    }

    public void setDepsafetyid(String depsafetyid) {
        this.depsafetyid = depsafetyid;
    }

    public String getTyshxydm() {
        return tyshxydm;
    }

    public void setTyshxydm(String tyshxydm) {
        this.tyshxydm = tyshxydm;
    }

    public String getZzJyz() {
        return zzJyz;
    }

    public void setZzJyz(String zzJyz) {
        this.zzJyz = zzJyz;
    }

    public String getZzName() {
        return zzName;
    }

    public void setZzName(String zzName) {
        this.zzName = zzName;
    }

    public String getZzAddress() {
        return zzAddress;
    }

    public void setZzAddress(String zzAddress) {
        this.zzAddress = zzAddress;
    }

    public String getZzMtname() {
        return zzMtname;
    }

    public void setZzMtname(String zzMtname) {
        this.zzMtname = zzMtname;
    }

    public String getYhFzr() {
        return yhFzr;
    }

    public void setYhFzr(String yhFzr) {
        this.yhFzr = yhFzr;
    }

    public String getYhFzrdh() {
        return yhFzrdh;
    }

    public void setYhFzrdh(String yhFzrdh) {
        this.yhFzrdh = yhFzrdh;
    }

    public String getYhDelxr() {
        return yhDelxr;
    }

    public void setYhDelxr(String yhDelxr) {
        this.yhDelxr = yhDelxr;
    }

    public String getYhDelxrdh() {
        return yhDelxrdh;
    }

    public void setYhDelxrdh(String yhDelxrdh) {
        this.yhDelxrdh = yhDelxrdh;
    }

    public String getYhSssq() {
        return yhSssq;
    }

    public void setYhSssq(String yhSssq) {
        this.yhSssq = yhSssq;
    }

    public String getGqGqqy() {
        return gqGqqy;
    }

    public void setGqGqqy(String gqGqqy) {
        this.gqGqqy = gqGqqy;
    }

    public String getGqQyfzr() {
        return gqQyfzr;
    }

    public void setGqQyfzr(String gqQyfzr) {
        this.gqQyfzr = gqQyfzr;
    }

    public String getGqQyfzrdh() {
        return gqQyfzrdh;
    }

    public void setGqQyfzrdh(String gqQyfzrdh) {
        this.gqQyfzrdh = gqQyfzrdh;
    }

    public String getGqSqg() {
        return gqSqg;
    }

    public void setGqSqg(String gqSqg) {
        this.gqSqg = gqSqg;
    }

    public String getGqSqgdh() {
        return gqSqgdh;
    }

    public void setGqSqgdh(String gqSqgdh) {
        this.gqSqgdh = gqSqgdh;
    }

    public Short getGqGqxy() {
        return gqGqxy;
    }

    public void setGqGqxy(Short gqGqxy) {
        this.gqGqxy = gqGqxy;
    }

    public Short getGqAqgzs() {
        return gqAqgzs;
    }

    public void setGqAqgzs(Short gqAqgzs) {
        this.gqAqgzs = gqAqgzs;
    }

    public Short getFhyqLsaq() {
        return fhyqLsaq;
    }

    public void setFhyqLsaq(Short fhyqLsaq) {
        this.fhyqLsaq = fhyqLsaq;
    }

    public Short getFhyqGlzd() {
        return fhyqGlzd;
    }

    public void setFhyqGlzd(Short fhyqGlzd) {
        this.fhyqGlzd = fhyqGlzd;
    }

    public Short getFhyqAqjcb() {
        return fhyqAqjcb;
    }

    public void setFhyqAqjcb(Short fhyqAqjcb) {
        this.fhyqAqjcb = fhyqAqjcb;
    }

    public Short getFhyqZcb() {
        return fhyqZcb;
    }

    public void setFhyqZcb(Short fhyqZcb) {
        this.fhyqZcb = fhyqZcb;
    }

    public Short getSbAzqk() {
        return sbAzqk;
    }

    public void setSbAzqk(Short sbAzqk) {
        this.sbAzqk = sbAzqk;
    }

    public Short getSbWbqk() {
        return sbWbqk;
    }

    public void setSbWbqk(Short sbWbqk) {
        this.sbWbqk = sbWbqk;
    }

    public Short getSbYxqk() {
        return sbYxqk;
    }

    public void setSbYxqk(Short sbYxqk) {
        this.sbYxqk = sbYxqk;
    }

    public BigDecimal getPjZjsl() {
        return pjZjsl;
    }

    public void setPjZjsl(BigDecimal pjZjsl) {
        this.pjZjsl = pjZjsl;
    }

    public Short getPjSfmhz() {
        return pjSfmhz;
    }

    public void setPjSfmhz(Short pjSfmhz) {
        this.pjSfmhz = pjSfmhz;
    }

    public Short getPjSfbh() {
        return pjSfbh;
    }

    public void setPjSfbh(Short pjSfbh) {
        this.pjSfbh = pjSfbh;
    }

    public String getPjZjsccj() {
        return pjZjsccj;
    }

    public void setPjZjsccj(String pjZjsccj) {
        this.pjZjsccj = pjZjsccj;
    }

    public Short getPjSfgqqygp() {
        return pjSfgqqygp;
    }

    public void setPjSfgqqygp(Short pjSfgqqygp) {
        this.pjSfgqqygp = pjSfgqqygp;
    }

    public Short getPjSfgqgp() {
        return pjSfgqgp;
    }

    public void setPjSfgqgp(Short pjSfgqgp) {
        this.pjSfgqgp = pjSfgqgp;
    }

    public Short getPjSfktj() {
        return pjSfktj;
    }

    public void setPjSfktj(Short pjSfktj) {
        this.pjSfktj = pjSfktj;
    }

    public Short getPjSfzyf() {
        return pjSfzyf;
    }

    public void setPjSfzyf(Short pjSfzyf) {
        this.pjSfzyf = pjSfzyf;
    }

    public String getPjTyqsccj() {
        return pjTyqsccj;
    }

    public void setPjTyqsccj(String pjTyqsccj) {
        this.pjTyqsccj = pjTyqsccj;
    }

    public Short getPjSfbfrg() {
        return pjSfbfrg;
    }

    public void setPjSfbfrg(Short pjSfbfrg) {
        this.pjSfbfrg = pjSfbfrg;
    }

    public Short getPjGdfs() {
        return pjGdfs;
    }

    public void setPjGdfs(Short pjGdfs) {
        this.pjGdfs = pjGdfs;
    }

    public Short getPjSfctwom() {
        return pjSfctwom;
    }

    public void setPjSfctwom(Short pjSfctwom) {
        this.pjSfctwom = pjSfctwom;
    }

    public Short getPjSfygcq() {
        return pjSfygcq;
    }

    public void setPjSfygcq(Short pjSfygcq) {
        this.pjSfygcq = pjSfygcq;
    }

    public String getXjry() {
        return xjry;
    }

    public void setXjry(String xjry) {
        this.xjry = xjry;
    }

    public String getXjrq() {
        return xjrq;
    }

    public void setXjrq(String xjrq) {
        this.xjrq = xjrq;
    }

    public String getBefRemark() {
        return befRemark;
    }

    public void setBefRemark(String befRemark) {
        this.befRemark = befRemark;
    }

    public String getAftRemark() {
        return aftRemark;
    }

    public void setAftRemark(String aftRemark) {
        this.aftRemark = aftRemark;
    }
}
