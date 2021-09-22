package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

/**
 * Created by admin on 2017/10/19.
 */
public class SectionWater implements Serializable {
    private String id;
    private String sectionid;
    private String datatime;
    private String sectionname;
    private String rivername;
    private String rivertargetquality;
    private Double targetcodmn;
    private Double targetammonianitrogen;
    private Double targettotalphosphorus;
    private Double codmn;
    private Double ammonianitrogen;
    private Double totalphosphorus;
    private String lastmodifytime;
    private String createtime;
    private  String overstandard;
    private  String riverquality;
    private String longitude;
    private String latitude;
    private String sectiontype;
    private String riverid;
    private String rivercode;
    private String flowdirection;
    private String flowspeed;
    private String sensory;
    private String destination;
    private String sectiontypetext;

    public String getSectiontypetext() {
        return sectiontypetext;
    }

    public void setSectiontypetext(String sectiontypetext) {
        this.sectiontypetext = sectiontypetext;
    }

    public String getFlowdirection() {
        return flowdirection;
    }

    public void setFlowdirection(String flowdirection) {
        this.flowdirection = flowdirection;
    }

    public String getFlowspeed() {
        return flowspeed;
    }

    public void setFlowspeed(String flowspeed) {
        this.flowspeed = flowspeed;
    }

    public String getSensory() {
        return sensory;
    }

    public void setSensory(String sensory) {
        this.sensory = sensory;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRiverid() {
        return riverid;
    }

    public void setRiverid(String riverid) {
        this.riverid = riverid;
    }

    public String getRivercode() {
        return rivercode;
    }

    public void setRivercode(String rivercode) {
        this.rivercode = rivercode;
    }

    public String getSectiontype() {
        return sectiontype;
    }

    public void setSectiontype(String sectiontype) {
        this.sectiontype = sectiontype;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getRiverquality() {
        return riverquality;
    }

    public void setRiverquality(String riverquality) {
        this.riverquality = riverquality;
    }

    public String getOverstandard() {
        return overstandard;
    }

    public void setOverstandard(String overstandard) {
        this.overstandard = overstandard;
    }

    public String getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(String lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionid() {
        return sectionid;
    }

    public void setSectionid(String sectionid) {
        this.sectionid = sectionid;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public String getRivername() {
        return rivername;
    }

    public void setRivername(String rivername) {
        this.rivername = rivername;
    }

    public String getRivertargetquality() {
        return rivertargetquality;
    }

    public void setRivertargetquality(String rivertargetquality) {
        this.rivertargetquality = rivertargetquality;
    }

    public Double getTargetcodmn() {
        return targetcodmn;
    }

    public void setTargetcodmn(Double targetcodmn) {
        this.targetcodmn = targetcodmn;
    }

    public Double getTargetammonianitrogen() {
        return targetammonianitrogen;
    }

    public void setTargetammonianitrogen(Double targetammonianitrogen) {
        this.targetammonianitrogen = targetammonianitrogen;
    }

    public Double getTargettotalphosphorus() {
        return targettotalphosphorus;
    }

    public void setTargettotalphosphorus(Double targettotalphosphorus) {
        this.targettotalphosphorus = targettotalphosphorus;
    }

    public Double getCodmn() {
        return codmn;
    }

    public void setCodmn(Double codmn) {
        this.codmn = codmn;
    }

    public Double getAmmonianitrogen() {
        return ammonianitrogen;
    }

    public void setAmmonianitrogen(Double ammonianitrogen) {
        this.ammonianitrogen = ammonianitrogen;
    }

    public Double getTotalphosphorus() {
        return totalphosphorus;
    }

    public void setTotalphosphorus(Double totalphosphorus) {
        this.totalphosphorus = totalphosphorus;
    }
}
