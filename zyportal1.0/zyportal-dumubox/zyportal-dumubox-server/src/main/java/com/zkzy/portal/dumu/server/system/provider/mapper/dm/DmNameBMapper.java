package com.zkzy.portal.dumu.server.system.provider.mapper.dm;

import com.zkzy.zyportal.system.api.entity.dm.DmNameB;
import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DmNameBMapper {
    int deleteByPrimaryKey(@Param("deviceuuid") String deviceuuid, @Param("persontype") BigDecimal persontype);

    int insert(DmNameB record);

    DmNameB selectByPrimaryKey(@Param("deviceuuid") String deviceuuid, @Param("persontype") BigDecimal persontype);

    List<DmNameB> selectAll(String param);

    int updateByPrimaryKey(DmNameB record);

    int deleteById(String deviceuuid);
}