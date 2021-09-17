package com.zkzy.portal.dumu.server.system.provider.mapper.dm;

import com.zkzy.zyportal.system.api.entity.dm.DmBoxB;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DmBoxBMapper {
    int deleteByPrimaryKey(String deviceuuid);

    int insert(DmBoxB record);

    DmBoxB selectByPrimaryKey(String deviceuuid);

    List<DmBoxB> selectAll(String param);

    int updateByPrimaryKey(DmBoxB record);
}