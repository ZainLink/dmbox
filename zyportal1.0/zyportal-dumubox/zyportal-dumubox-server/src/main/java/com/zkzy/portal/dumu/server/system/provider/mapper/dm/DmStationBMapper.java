package com.zkzy.portal.dumu.server.system.provider.mapper.dm;

import com.zkzy.zyportal.system.api.entity.dm.DmStList;
import com.zkzy.zyportal.system.api.entity.dm.DmStationB;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DmStationBMapper {
    int deleteByPrimaryKey(String unid);

    int insert(DmStationB record);

    DmStationB selectByPrimaryKey(String unid);

    List<DmStationB> selectAll(String param);

    List<DmStList> selectlist();

    int updateByPrimaryKey(DmStationB record);
}