package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.api.Paging;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.SystemMachine;
import com.zkzy.portal.base.admin.api.viewModel.EquipmentModel;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7 0007.
 */
public interface SystemMachineService {
    PageInfo machineList(String param, Paging page);
    CodeObject addMachine(SystemMachine systemMachine);
    CodeObject updateMachine(SystemMachine systemMachine);
    CodeObject delMachine(SystemMachine systemMachine);
    List<EquipmentModel> machineHis(SystemMachine systemMachine, String startTime, String endTime) throws ParseException, IOException;
}
