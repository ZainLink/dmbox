package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

/**
 * Created by admin on 2017/10/24.
 */
public class WaterFunction implements Serializable {
    private String id;
    private String waterfunctionid;
    private String datatime;
    private String waterfunctionname;
    private String rivername;
    private String rivertargetquality;
    private Double tapotassiumPermanganateIndex;
    private Double targetammonianitrogen;
    private Double potassiumPermanganateIndex;
    private Double ammonianitrogen;
    private String createtime;
    private String lastmodifytime;
    private String waterfunctiontype;
    private String remarks;
    private  String overstandard;
    private  String riverquality;
    private String waterfunctioncode;

    public String getWaterfunctioncode() {
        return waterfunctioncode;
    }

    public void setWaterfunctioncode(String waterfunctioncode) {
        this.waterfunctioncode = waterfunctioncode;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getWaterfunctiontype() {
        return waterfunctiontype;
    }

    public void setWaterfunctiontype(String waterfunctiontype) {
        this.waterfunctiontype = waterfunctiontype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWaterfunctionid() {
        return waterfunctionid;
    }

    public void setWaterfunctionid(String waterfunctionid) {
        this.waterfunctionid = waterfunctionid;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getWaterfunctionname() {
        return waterfunctionname;
    }

    public void setWaterfunctionname(String waterfunctionname) {
        this.waterfunctionname = waterfunctionname;
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

    public Double getTapotassiumPermanganateIndex() {
        return tapotassiumPermanganateIndex;
    }

    public void setTapotassiumPermanganateIndex(Double tapotassiumPermanganateIndex) {
        this.tapotassiumPermanganateIndex = tapotassiumPermanganateIndex;
    }

    public Double getTargetammonianitrogen() {
        return targetammonianitrogen;
    }

    public void setTargetammonianitrogen(Double targetammonianitrogen) {
        this.targetammonianitrogen = targetammonianitrogen;
    }

    public Double getPotassiumPermanganateIndex() {
        return potassiumPermanganateIndex;
    }

    public void setPotassiumPermanganateIndex(Double potassiumPermanganateIndex) {
        this.potassiumPermanganateIndex = potassiumPermanganateIndex;
    }

    public Double getAmmonianitrogen() {
        return ammonianitrogen;
    }

    public void setAmmonianitrogen(Double ammonianitrogen) {
        this.ammonianitrogen = ammonianitrogen;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(String lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
}
