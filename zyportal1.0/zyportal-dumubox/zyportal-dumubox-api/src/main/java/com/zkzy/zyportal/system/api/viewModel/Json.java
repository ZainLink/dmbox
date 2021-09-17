package com.zkzy.zyportal.system.api.viewModel;



import com.zkzy.zyportal.system.api.entity.base.SystemCode;
import com.zkzy.zyportal.system.api.entity.base.SystemOrganization;
import com.zkzy.zyportal.system.api.entity.base.SystemParameter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public class Json {
    private String id;
    private String title="提示";
    private String message;
    private boolean status=false;
    private List<SystemCode> systemCodeList;
    private List<SystemOrganization> organizationList;
    private SystemParameter parameter;




    //转化格式后的新的文件名称
    private String filename;
    //保存路径
    private String filepath;
    //文件源名称
    private String filebasename;
    //文件类型  比如：doc,xls
    private String filetype;
    //文件相对路径
    private String relativepath;
    //文件大小
    private String filelen;
    //文件MIMETYPE类型，与文件类型的对应关系请参考http://www.iana.org/assignments/media-types/media-types.xhtml
    private String mimeType;
    //PDF路径
    private String pdfpath;
    //所属模块
    private String module;

    private String type;

    private Object object;

    private int returnCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilebasename() {
        return filebasename;
    }

    public void setFilebasename(String filebasename) {
        this.filebasename = filebasename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getRelativepath() {
        return relativepath;
    }

    public void setRelativepath(String relativepath) {
        this.relativepath = relativepath;
    }

    public String getFilelen() {
        return filelen;
    }

    public void setFilelen(String filelen) {
        this.filelen = filelen;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public List<SystemCode> getSystemCodeList() {
        return systemCodeList;
    }

    public void setSystemCodeList(List<SystemCode> systemCodeList) {
        this.systemCodeList = systemCodeList;
    }

    public String getPdfpath() {
        return pdfpath;
    }

    public void setPdfpath(String pdfpath) {
        this.pdfpath = pdfpath;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public List<SystemOrganization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<SystemOrganization> organizationList) {
        this.organizationList = organizationList;
    }

    public SystemParameter getParameter() {
        return parameter;
    }

    public void setParameter(SystemParameter parameter) {
        this.parameter = parameter;
    }
}

