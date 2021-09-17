package com.zkzy.zyportal.system.api.service.base;



import com.zkzy.zyportal.system.api.entity.base.SystemParameter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public interface SystemParameterService {
    List<SystemParameter> selectAll(String param);
}
