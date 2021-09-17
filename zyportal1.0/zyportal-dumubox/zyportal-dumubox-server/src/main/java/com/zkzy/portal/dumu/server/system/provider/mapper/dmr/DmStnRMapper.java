package com.zkzy.portal.dumu.server.system.provider.mapper.dmr;

import com.zkzy.zyportal.system.api.entity.dmr.DmStnR;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DmStnRMapper {
    int deleteByPrimaryKey(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid, @Param("persontype") BigDecimal persontype);

    int deleteBySt(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid);

    int insert(DmStnR record);

    DmStnR selectByPrimaryKey(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid, @Param("persontype") BigDecimal persontype);

    DmStnR  selectInfoByPrimaryKey(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid, @Param("persontype") BigDecimal persontype);

    List<DmStnR> selectPraviteNames(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid);

    List<DmStnR> selectAll(String param);

    int updateByPrimaryKey(DmStnR record);
}