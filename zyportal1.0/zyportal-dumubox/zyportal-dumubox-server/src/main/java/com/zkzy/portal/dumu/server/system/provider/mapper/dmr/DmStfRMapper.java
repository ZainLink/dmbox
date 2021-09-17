package com.zkzy.portal.dumu.server.system.provider.mapper.dmr;

import com.zkzy.zyportal.system.api.entity.dmr.DmStfR;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DmStfRMapper {
    int deleteByPrimaryKey(@Param("unid") String unid, @Param("persontype") BigDecimal persontype);

    int deleteByUnid(String unid);

    int insert(DmStfR record);

    DmStfR selectByPrimaryKey(@Param("unid") String unid, @Param("persontype") BigDecimal persontype);

    List<DmStfR> selectAll();

    int updateByPrimaryKey(DmStfR record);

    int updateLgs(DmStfR record);
}