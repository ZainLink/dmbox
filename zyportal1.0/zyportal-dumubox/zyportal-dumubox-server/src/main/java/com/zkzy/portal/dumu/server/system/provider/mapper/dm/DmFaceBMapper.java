package com.zkzy.portal.dumu.server.system.provider.mapper.dm;

import com.zkzy.zyportal.system.api.entity.dm.DmFaceB;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DmFaceBMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(DmFaceB record);

    DmFaceB selectByPrimaryKey(String uuid);

    List<DmFaceB> selectAll(String param);

    int updateByPrimaryKey(DmFaceB record);
}