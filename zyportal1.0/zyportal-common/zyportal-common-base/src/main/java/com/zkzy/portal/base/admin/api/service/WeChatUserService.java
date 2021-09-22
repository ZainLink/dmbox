package com.zkzy.portal.base.admin.api.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.WeChatTag;
import com.zkzy.portal.base.admin.api.entity.WeChatUser;

public interface WeChatUserService {

	//根据openid获取多个用户基本信息列表
//	public List<WeChatUser> getUserInfosByOpenids(String[] openids,String weChatId);

	//根据openid给用户设置备注名
	public CodeObject setRemarkByOpenid(String openid, String remark, String weChatId);

	public PageInfo selectAll(int currentPage, int pageSize, String param);

	//添加标签
	public CodeObject addTag(int tagid, String openid, String weChatId);

	//删除标签
	public CodeObject deleteTag(int tagid, String openid, String weChatId);

	//同步
	public CodeObject synchronizeFake(String weChatId);

	public List<WeChatUser> selectUsersSubscribed(String param);

	//获取用户标签列表
	public List<WeChatTag> queryTagsByOpenid(String openid, String weChatId);
}
