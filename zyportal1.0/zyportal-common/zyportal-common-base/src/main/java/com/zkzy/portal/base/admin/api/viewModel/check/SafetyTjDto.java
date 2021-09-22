package com.zkzy.portal.base.admin.api.viewModel.check;

import java.io.Serializable;

/**
 * @ClassName SafetyTjDto
 * @Description TODO
 * @Author gezb
 * @Date 2020-07-20 13:53
 **/
public class SafetyTjDto implements Serializable {
    private String status;
    private Integer amount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
