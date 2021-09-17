package com.zkzy.portal.dumu.server.system.provider.mapper.mq;

import com.zkzy.zyportal.system.api.entity.mq.DmStrangerH;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DmStrangerHMapper {
    int deleteByPrimaryKey(String id);

    int insert(DmStrangerH record);

    DmStrangerH selectByPrimaryKey(String id);

    List<DmStrangerH> selectAll(String param);

    int updateByPrimaryKey(DmStrangerH record);
}