package com.zkzy.portal.dumu.server.system.provider.mapper.dmr;

import com.zkzy.zyportal.system.api.entity.dmr.DmStR;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DmStRMapper {
    int deleteByPrimaryKey(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid);

    int insert(DmStR record);

    DmStR selectByPrimaryKey(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid);

    int selectBindNumByUnid(@Param("unid") String unid);

    List<DmStR> selectAll(String param);

    List<DmStR> selectBoxesByUnid(String unid);

    int updateByPrimaryKey(DmStR record);
}