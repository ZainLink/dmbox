package com.zkzy.zyportal.system.api.constant;


import org.apache.poi.ss.formula.functions.Code;

/**
 * Created by Administrator on 2017/4/20.
 */
public class ReturnCode {

    public static final CodeObject SUCCESS = new CodeObject("0", "成功");

    public static final CodeObject FAILED = new CodeObject("-1", "失败");

    public static final CodeObject FAILED2 = new CodeObject("-1", "失败,请从站点白名单中移除");

    public static final CodeObject FAILEDST = new CodeObject("-1", "请解绑盒子后再删除站点");

    public static final CodeObject FFCS = new CodeObject("-1", "非法参数");

    public static final CodeObject ERROR = new CodeObject("-2", "参数未定义");

    public static final CodeObject TB001 = new CodeObject("0", "开始同步请等待");

    public static final CodeObject TB002 = new CodeObject("0", "开始导入人脸信息，请稍后。。。");

    public static final CodeObject TB003 = new CodeObject("0", "开始删除人脸信息，请稍后。。。");

    public static final CodeObject TB004 = new CodeObject("0", "开始关联站点信息，请稍后。。。");

    public static final CodeObject TB005 = new CodeObject("0", "站点解绑成功");

    public static final CodeObject AREA_SUCCESS = new CodeObject("60001", "提交成功！");

    public static final CodeObject AREA_FAILED = new CodeObject("60002", "提交失败！");

    public static final CodeObject INSERT_FAILED = new CodeObject("60007", "添加失败，信息已存在！");

    public static final CodeObject SELECT_NULL = new CodeObject("60020", "查询成功，无数据！");

    public static final CodeObject INSERTBM_FAILED = new CodeObject("60013", "添加失败，参数编码已存在！");

    public static final CodeObject CHANGEBM_FAILED = new CodeObject("60014", "修改失败，参数编码已存在！");

    public static final CodeObject add_FAILED = new CodeObject("60008", "添加失败，节点名称已存在！");

    public static final CodeObject edit_FAILED = new CodeObject("60009", "修改失败，节点名称已存在！");

    public static final CodeObject DEL_SUCCESS = new CodeObject("60003", "删除成功！");

    public static final CodeObject DEL_FAILED = new CodeObject("60004", "删除失败！");

    public static final CodeObject RL_FAILED = new CodeObject("-1", "默认的人脸库不能删除！");

    public static final CodeObject RL_FAILED2 = new CodeObject("-1", "默认的人脸库不能编辑！");

    public static final CodeObject DELNode_FAILED = new CodeObject("60010", "删除失败,该目录下含有文件或节点！");

    public static final CodeObject TOPDF_FAILED = new CodeObject("60012", "PDF文件转换失败！");

    public static final CodeObject TOPDF_SUCESS = new CodeObject("60011", "PDF文件转换成功！");

    public static final CodeObject TOPDF_LOADING = new CodeObject("60013", "PDF文件正在转换！");

    public static final CodeObject TOPDF_NONEFILE = new CodeObject("60014", "转换失败,转换文件不存在！");

    public static final CodeObject TOPDF_DISCONECT = new CodeObject("60015", "转换失败,OpenOffice服务未启动！");

    public static final CodeObject TOPDF_BACKGROUNDTOPDF = new CodeObject("60016", "手动转换失败,后台调度正在转换该文件！");

    public static final CodeObject HASBLACKINFOS = new CodeObject("60020", "该河道还有未整改完成的数据，请整改完成后再新增！");

    public static final CodeObject UPDATE_SUCCESS = new CodeObject("60005", "更新成功！");

    public static final CodeObject UPDATE_FAILED = new CodeObject("60006", "更新失败！");

    public static final CodeObject ORGAREA_ERROR = new CodeObject("69000", "区域权限异常！");

    public static final CodeObject DEP_ERROR = new CodeObject("69001", "关联权限异常！");

    public static final CodeObject BAD_REQUEST = new CodeObject("10001", "400 (错误请求) 服务器不理解请求的语法。");

    public static final CodeObject NOT_FOUND = new CodeObject("10002", "404 (未找到) 服务器找不到请求的资源。");

    public static final CodeObject METHOD_NOT_ALLOWED = new CodeObject("10003", "405 (方法禁用) 禁用请求中指定的方法。");

    public static final CodeObject NOT_ACCEPTABLE = new CodeObject("10004", "406 (不接受) 无法使用请求的内容特性响应请求的网页。");

    public static final CodeObject UNSUPPORTED_MEDIA_TYPE = new CodeObject("10005", "415 (不支持的媒体类型) 请求的格式不受请求页面的支持。");

    public static final CodeObject INTERNAL_SERVER_ERROR = new CodeObject("10006", "500 (服务器内部错误) 服务器遇到错误，无法完成请求。");

    public static final CodeObject UNAUTHORIZED = new CodeObject("10007", "401 (未授权) 请求要求身份验证。 (Basic 认证错误或无权限头)");

    public static final CodeObject FORBIDDEN = new CodeObject("10008", "403 (禁止) 服务器拒绝请求。");

    public static final CodeObject INVALID_FIELD = new CodeObject("20002", "400 字段校验错误");

    public static final CodeObject INVALID_GRANT = new CodeObject("30001", "400 用户名,密码错误");

    public static final CodeObject DISABLED_USER = new CodeObject("30002", "403 用户被冻结");

    public static final CodeObject USER_EXIST = new CodeObject("30101", "400 用户已存在");

    public static final CodeObject USER_NOT_EXIST = new CodeObject("30102", "400 用户不存在");

    public static final CodeObject SMS_TOO_MUCH = new CodeObject("30103", "403 短信发送太频繁");

    public static final CodeObject SMS_TRUE = new CodeObject("30188", "短信验证成功");

    public static final CodeObject SMS_FAIL = new CodeObject("30187", "短信验证失败");

    public static final CodeObject INVALID_CAPTCHA = new CodeObject("30201", "400 无效验证码");

    public static final CodeObject ROLE_EXIST = new CodeObject("30301", "400 角色已存在");

    public static final CodeObject ROLE_NOT_EXIST = new CodeObject("30302", "400 角色不存在");

    public static final CodeObject NULL_OBJECT = new CodeObject("40001", "对象不存在");

    public static final CodeObject EXIST_OBJECT = new CodeObject("40002", "对象存在");

    public static final CodeObject UNKNOWN_ERROR = new CodeObject("99999", "未知错误");

    public static final String PERSISTENCE_STATUS = "A";

    public static final CodeObject NO_AUTHORITY = new CodeObject("40401", "权限不足");

    public static final String PERSISTENCE_DELETE_STATUS = "I";

    public static final String TREE_STATUS_OPEN = "open";

    public static final String TREE_STATUS_CLOSED = "closed";

    public static final CodeObject FIELD_CONNECTION = new CodeObject("40402", "有未删除的关联字段，请检查后重试");


}
