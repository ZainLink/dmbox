package com.zkzy.portal.dumu.server.system.provider.mapper.dm;

import com.zkzy.zyportal.system.api.entity.dm.DmCameraB;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface DmCameraBMapper {
    int deleteByPrimaryKey(@Param("deviceuuid") String deviceuuid, @Param("channelno") Integer channelno);

    int insert(DmCameraB record);

    int insertCamera(@Param("deviceuuid") String deviceuuid, @Param("channelno") Integer channelno);

    DmCameraB selectByPrimaryKey(@Param("deviceuuid") String deviceuuid, @Param("channelno") Integer channelno);

    List<DmCameraB> selectAll(String param);

    int updateByPrimaryKey(DmCameraB record);

    int updateBaseByPrimaryKey(DmCameraB record);

    int updateByProto(DmCameraB dmCameraB);
}