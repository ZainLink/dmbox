package com.zkzy.portal.dumu.server.system.provider.mapper.dmr;

import com.zkzy.zyportal.system.api.entity.dmr.DmStnfR;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DmStnfRMapper {
    int deleteByPrimaryKey(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid, @Param("persontype") BigDecimal persontype, @Param("uuid") String uuid);

    int deleteBySt(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid);

    int insert(DmStnfR record);

    DmStnfR selectByPrimaryKey(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid, @Param("persontype") BigDecimal persontype, @Param("uuid") String uuid);

    List<DmStnfR> selectListByParam(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid, @Param("persontype") BigDecimal persontype, @Param("uuid") String uuid);

    List<DmStnfR> selectAll();

    int updateByPrimaryKey(DmStnfR record);

    int selectCountForSt(@Param("unid") String unid, @Param("deviceuuid") String deviceuuid, @Param("persontype") BigDecimal persontype);

    int selectCountForAdmin(@Param("deviceuuid") String deviceuuid, @Param("persontype") BigDecimal persontype);

    int selectByUUid(@Param("uuid") String uuid);

    List<DmStnfR> selectInfoByUUid(@Param("uuid") String uuid);
}