package com.zkzy.portal.dumu.server.system.provider.mapper.dmr;

import com.zkzy.zyportal.system.api.entity.dmr.DmBoxWhiteList;
import com.zkzy.zyportal.system.api.entity.dmr.DmStfN;

import java.math.BigDecimal;
import java.util.List;

import com.zkzy.zyportal.system.api.entity.dmr.DmWhiteList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DmStfNMapper {
    int deleteByPrimaryKey(@Param("unid") String unid, @Param("persontype") BigDecimal persontype, @Param("uuid") String uuid);

    int insert(DmStfN record);

    DmStfN selectByPrimaryKey(@Param("unid") String unid, @Param("persontype") BigDecimal persontype, @Param("uuid") String uuid);

    List<DmStfN> selectWhiteList(@Param("unid") String unid, @Param("persontype") BigDecimal persontype);

    List<DmWhiteList> selectAll(String param);

    int updateByPrimaryKey(DmStfN record);

    int deleteByUnid(String unid);

    int selectWhiteNum(@Param("unid") String unid, @Param("persontype") BigDecimal persontype);

    int selectByUUid(String uuid);

    List<DmBoxWhiteList> selectFaceByBox(String param);
}