package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.entity.SystemAttachmentR;
import com.zkzy.portal.base.admin.api.entity.SystemNodefile;
import com.zkzy.portal.base.admin.api.viewModel.FileRel;

import java.util.List;

/**
 * Created by WH on 2018/11/26.
 */
public interface SystemAttachMentService {

    int insertAttachMents(String mainId, List<FileRel> rels);

    int deleteAttachMentsByMainId(String mainId);

    int deletaAttachMentsByMainIdAndType(String mainId, String type);

    int deleteAttachMentsByFileId(String fileId);

    int deleteAttachMentsByType(String type);

    int deleteAttachMentByRecord(SystemAttachmentR systemAttachmentR);

    int updateAttachMent(String mainId, List<FileRel> rels);

    int updateAttachMent(String mainId, List<FileRel> rels, String type);

    List<SystemNodefile> selectAll(SystemAttachmentR systemAttachmentR);

    Boolean QRCodeByHref(String text, Integer width, Integer height, String QRCodeName);

    Boolean QRCodeByHref2(String text, Integer width, Integer height);

}
