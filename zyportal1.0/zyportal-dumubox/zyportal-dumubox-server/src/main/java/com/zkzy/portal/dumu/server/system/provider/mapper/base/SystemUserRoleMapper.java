//package com.zkzy.portal.dumu.server.system.provider.mapper.base;
//
//import com.zkzy.zyportal.system.api.entity.base.SystemRole;
//import com.zkzy.zyportal.system.api.entity.base.SystemUserRole;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.List;
//
//@Mapper
//public interface SystemUserRoleMapper {
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table SYSTEM_USER_ROLE
//     *
//     * @mbggenerated Thu Apr 27 14:32:34 CST 2017
//     */
//    int insert(SystemUserRole record);
//
//    /**
//     * This method was generated by MyBatis Generator.
//     * This method corresponds to the database table SYSTEM_USER_ROLE
//     *
//     * @mbggenerated Thu Apr 27 14:32:34 CST 2017
//     */
//    List<SystemUserRole> selectAll();
//
//    List<SystemRole> getIdsByUserId(String userIf);
//
//    void deleteByUserId(String userId);
//
//    void deleteByRoleId(String roleId);
//
//    List<SystemUserRole> findListByUserId(SystemUserRole systemUserRole);
//    int insertByList(List<SystemUserRole> list);
//}