package com.zkzy.zyportal.system.api.service.base;



import com.zkzy.zyportal.system.api.entity.base.SystemOrganization;
import com.zkzy.zyportal.system.api.viewModel.CacheSystemCodeCur;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public interface RedisService {
      String getParameterValueByCode(String code) throws Exception;
      String getSystemCodeListByCode(String code) throws Exception;
      String getSystemCodeNameByCode(String code) throws Exception;
      String getOrganizationListByCode(String code) throws Exception;
      List<SystemOrganization> getOrganizationListBeanByCode(String code) throws Exception;
      String getOrganizationNameByCode(String code) throws Exception;
      String getOnlineUserList(String param);
      String getSystemCodeIdByCode(String code);
      Map<String,CacheSystemCodeCur> getCacheSystemCode();
}
