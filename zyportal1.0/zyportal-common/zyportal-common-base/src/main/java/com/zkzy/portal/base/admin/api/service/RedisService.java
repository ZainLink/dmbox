package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.entity.SystemOrganization;
import com.zkzy.portal.base.admin.api.viewModel.CacheSystemCodeCur;

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
      void afterExecuse();
      String getSystemCodeIdByCode(String code);
      Map<String,CacheSystemCodeCur> getCacheSystemCode();
}
