package com.zkzy.portal.dumu.server.system.provider.mapper.dm;

import com.zkzy.zyportal.system.api.entity.dm.WxGasDstB;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WxGasDstBMapper {
    int deleteByPrimaryKey(String cid);

    int insert(WxGasDstB record);

    WxGasDstB selectByPrimaryKey(String cid);

    List<WxGasDstB> selectAll();

    int updateByPrimaryKey(WxGasDstB record);
}