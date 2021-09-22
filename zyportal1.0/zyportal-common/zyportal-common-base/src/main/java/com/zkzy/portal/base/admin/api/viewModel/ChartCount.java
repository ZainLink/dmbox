package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;
import java.math.BigDecimal;

public class ChartCount implements Serializable {
    private String dateTime;
    private BigDecimal countValue;
    private String dataType;


    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getCountValue() {
        return countValue;
    }

    public void setCountValue(BigDecimal countValue) {
        this.countValue = countValue;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
