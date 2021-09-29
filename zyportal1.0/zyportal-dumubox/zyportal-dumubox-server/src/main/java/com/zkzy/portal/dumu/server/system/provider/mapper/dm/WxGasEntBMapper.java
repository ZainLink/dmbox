package com.zkzy.portal.dumu.server.system.provider.mapper.dm;

import com.zkzy.zyportal.system.api.entity.dm.WxGasEntB;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface WxGasEntBMapper {
    int deleteByPrimaryKey(String qid);

    int insert(WxGasEntB record);

    WxGasEntB selectByPrimaryKey(String qid);

    List<WxGasEntB> selectAll();

    int updateByPrimaryKey(WxGasEntB record);
}