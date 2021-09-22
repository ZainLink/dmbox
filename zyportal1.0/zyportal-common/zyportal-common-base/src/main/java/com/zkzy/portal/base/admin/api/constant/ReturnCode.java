package com.zkzy.portal.base.admin.api.constant;


/**
 * Created by Administrator on 2017/4/20.
 */
public class ReturnCode {

    public static final CodeObject SUCCESS = new CodeObject("0", "成功");

    public static final CodeObject FAILED = new CodeObject("-1", "失败");

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

    //承包返回码110000-120000用于REST API 返回码，http://www.yuntongxun.com/doc/rest/restabout/3_1_1_7.html
    //620215-620224也被承包了

    public static final CodeObject SYSTEM_BUSY = new CodeObject("50000", "系统繁忙");

    public static final CodeObject ADD_ACCOUNT_SUCCESS = new CodeObject("50001", "添加公众号成功");

    public static final CodeObject ADD_ACCOUNT_FAILED = new CodeObject("50002", "添加公众号失败");

    public static final CodeObject DELETE_ACCOUNT_SUCCESS = new CodeObject("50003", "删除公众号成功");

    public static final CodeObject DELETE_ACCOUNT_FAILED = new CodeObject("50004", "删除公众号失败");

    public static final CodeObject UPDATE_ACCOUNT_SUCCESS = new CodeObject("50005", "更新公众号成功");

    public static final CodeObject UPDATE_ACCOUNT_FAILED = new CodeObject("50006", "更新公众号失败");

    public static final CodeObject ACCESS_TOKEN_INVALID = new CodeObject("50010", "无效的access_token，请重试");

    public static final CodeObject TAG_ADD_SUCCESS = new CodeObject("50011", "添加成功");

    public static final CodeObject TAG_REPEAT = new CodeObject("50012", "标签名非法，请注意不能和其他标签重名");

    public static final CodeObject TAG_NAME_OVERFLOW = new CodeObject("50013", "标签名长度超过30个字节");

    public static final CodeObject TAG_ADDFAILED_NUM_OVERFLOW = new CodeObject("50014", "创建的标签数过多，请注意不能超过100个");

    public static final CodeObject TAG_ADD_FAILED = new CodeObject("50015", "添加失败");

    public static final CodeObject DATA_ERROR = new CodeObject("50016", "非法数据");

    public static final CodeObject TAG_DELETE_SUCCESS = new CodeObject("50021", "删除成功");

    public static final CodeObject TAG_NOCHANGE_DEFAULT = new CodeObject("50022", "该标签为系统默认保留的标签，不可修改");

    public static final CodeObject TAG_DELETE_FAILED_DIRECT = new CodeObject("50023", "该标签下粉丝数超过10w，不允许直接删除");

    public static final CodeObject TAG_DELETE_FAILED = new CodeObject("50024", "删除失败");

    public static final CodeObject TAG_UPDATE_SUCCESS = new CodeObject("50031", "更新成功");

    public static final CodeObject TAG_UPDATE_FAILED = new CodeObject("50032", "更新失败");

    public static final CodeObject TAG_SYNCHRONIZE_SUCCESS = new CodeObject("50041", "同步标签信息成功");

    public static final CodeObject TAG_SYNCHRONIZE_FAILED = new CodeObject("50042", "同步标签信息失败，请选择一个公众号进行同步");

    public static final CodeObject User_UPDATEREMARK_SUCCESS = new CodeObject("50051", "设置备注名成功");

    public static final CodeObject User_UPDATEREMARK_FAILED = new CodeObject("50052", "设置备注名失败");

    public static final CodeObject User_UPDATETAG_SUCCCESS = new CodeObject("50053", "添加分组成功");

    public static final CodeObject User_UPDATETAG_ILLEGALTAG = new CodeObject("50054", "非法的分组");

    public static final CodeObject User_UPDATETAG_TAGNUMMAX = new CodeObject("50055", "用户分组数量达到上限（不能超过20个）");

    public static final CodeObject User_UPDATETAG_ILLEGALUSER = new CodeObject("50056", "非法用户");

    public static final CodeObject User_UPDATETAG_FAILED = new CodeObject("50057", "添加分组失败");

    public static final CodeObject User_DELETETAG_SUCCESS = new CodeObject("50058", "删除分组成功");

    public static final CodeObject User_DELETETAG_FAILED = new CodeObject("50059", "删除分组失败");

    public static final CodeObject User_SYNCHRONIZE_SUCCESS = new CodeObject("50060", "同步用户成功");

    public static final CodeObject User_SYNCHRONIZE_FAILED = new CodeObject("50061", "同步用户失败");

    public static final CodeObject MESSAGE_SEND_SUCCESS = new CodeObject("50071", "消息发送成功");

    public static final CodeObject MESSAGE_SEND_NOQUOTA = new CodeObject("50072", "群发次数已用完");

    public static final CodeObject MESSAGE_SEND_FAILED = new CodeObject("50073", "消息发送失败");

    public static final CodeObject MESSAGE_ADD_SUCCESS = new CodeObject("50081", "消息记录保存成功");

    public static final CodeObject MESSAGE_ADD_FAILED = new CodeObject("50082", "消息记录保存失败");

    public static final CodeObject MESSAGE_DELETE_SUCCESS = new CodeObject("50083", "消息记录删除成功");

    public static final CodeObject MESSAGE_DELETE_FAILED = new CodeObject("50084", "消息记录删除失败");

    public static final CodeObject DELETEBOOK_HAVEMANS = new CodeObject("70010", "该目录无法删除，下含子目录或人员！");

    public static final CodeObject INSERTSX_FAILED = new CodeObject("90013", "添加失败，该年度该市县数据已存在！");

    public static final CodeObject EDITSX_FAILED = new CodeObject("90014", "修改失败，该年度该市县数据已存在！");

    public static final CodeObject INUSECANNOTDEL = new CodeObject("80001", "使用中,不能删除");
    public static final CodeObject NOTHZ = new CodeObject("80002", "无河长权限");
    public static final CodeObject NOTROLE = new CodeObject("80006", "无相关角色权限");
    public static final CodeObject NOHZINFO = new CodeObject("80003", "无河长信息");
    public static final CodeObject NOINFO = new CodeObject("80007", "无相关角色信息");
    public static final CodeObject NOUSER = new CodeObject("80005", "未关联人员");


    //燃气小程序返回状态
    public static final CodeObject MINPRO_FAILED = new CodeObject("61001", "授权失败，数据解密异常！");

    public static final CodeObject MINPRO_SUCCESS = new CodeObject("61002", "授权成功！");

    public static final CodeObject MINPRO_ADD_FAILED = new CodeObject("61003", "授权失败，数据新增失败！");

    public static final CodeObject MINPRO_AUTHINFO_FAIL = new CodeObject("61004", "查询用户授权信息异常！");

    public static final CodeObject MINPRO_AUTHINFO_FALSE = new CodeObject("61005", "用户还未授权，请授权！");

    public static final CodeObject MINPRO_AUTHINFO_TRUE = new CodeObject("61006", "用户已授权！");

    public static final CodeObject MINPRO_ISBIND_ERRO = new CodeObject("61007", "查询绑定异常！");

    public static final CodeObject MINPRO_ISBIND_TRUE = new CodeObject("61008", "查询成功，该用户还没绑定此商户！");

    public static final CodeObject MINPRO_ISBIND_FALSE = new CodeObject("61009", "绑定失败，该用户已经绑定此商户！");

    public static final CodeObject MINPRO_GUANZHU_FALSE = new CodeObject("61010", "绑定失败，请先关注公众号！");

    public static final CodeObject MINPRO_BIND_SUCCESS = new CodeObject("61011", "绑定商户成功！");

    public static final CodeObject MINPRO_REMOVEBIND_SUCCESS = new CodeObject("61012", "解绑商户成功！");

    public static final CodeObject MINPRO_REMOVEBIND_FAILED = new CodeObject("61013", "解绑商户失败！");

    public static final CodeObject RQ_EWM_FAILED = new CodeObject("61014", "二维码生成失败！");

    public static final CodeObject RQ_CPZ_DEL_FAILED = new CodeObject("61015", "该气配站正在被使用！");

    public static final CodeObject RQ_GYZ_DEL_FAILED = new CodeObject("61021", "该供应站正在被使用！");

    public static final CodeObject RQ_CPZ_EWM_FAILED = new CodeObject("61016", "二维码查询异常！");

    public static final CodeObject RQ_CPZ_EWM_SUCCESS = new CodeObject("61017", "二维码查询成功！");

    public static final CodeObject RQ_CPZ_EWM_NOTFOUND = new CodeObject("61018", "没有查到二维码信息！");

    public static final CodeObject RQ_CPZ_FILE_FOUND = new CodeObject("61019", "文件查询成功！");

    public static final CodeObject RQ_CPZ_FILE_NONE = new CodeObject("61020", "还未上传文件！");

    public static final CodeObject USERCONFIRMEDOREVALUATEDCANNOTEDIT = new CodeObject("70000", "用户已确认或已评价,不能进行编辑操作！");
    public static final CodeObject USERNOTCONFIRMED = new CodeObject("70001", "用户未确认！");
    public static final CodeObject USERCONFIRMEDOREVALUATEDCANNOTDEL = new CodeObject("70002", "用户已确认或已评价,不能进行删除操作！");
    public static final CodeObject EVALUATEDCANNOTEVALUATE = new CodeObject("70003", "用户已评价,不能重复评价！");
    public static final CodeObject NOTCONFIRMEDSTATUS = new CodeObject("70004", "不是待确认状态！");
    public static final CodeObject CANTTOBECONFIRMED = new CodeObject("70005", "不能提交待确认！");
    public static final CodeObject NOPROBLEMS = new CodeObject("70006", "无问题需要整改！");


    //二维码
    public static final CodeObject NON_EXISTENT_RESTAURANT = new CodeObject("20001", "商户不存在！");

    public static final CodeObject BIND_FAILED_1 = new CodeObject("20002", "绑定二维码失败！：1");

    public static final CodeObject BIND_FAILED_2 = new CodeObject("20005", "绑定二维码失败！：无效的二维码");

    public static final CodeObject BIND_FAILED_3 = new CodeObject("20006", "绑定二维码失败！：该二维码已被绑定！");

    public static final CodeObject BIND_FAILED_4 = new CodeObject("20007", "绑定二维码失败！：不存在待使用的二维码,请先导出！");

    public static final CodeObject INVALID_FAILED_1 = new CodeObject("20003", "解绑二维码失败！：1");

    public static final CodeObject INVALID_FAILED_2 = new CodeObject("20004", "该商户已绑定二维码，请先解绑二维码后删除！");

    public static final CodeObject INVALID_FAILED_3 = new CodeObject("20008", "解绑二维码失败！：无效的二维码");

    public static final CodeObject BIND_FAILED_5 = new CodeObject("20009", "绑定二维码失败！：该商户以绑定二维码！");



    /*
    泄露报警
     */

    //设备注册
    public static final CodeObject SIGNUP_SUCCESS = new CodeObject("66001", "设备注册成功");

    public static final CodeObject SIGNUP_FAILED = new CodeObject("66002", "设备注册失败");

    public static final CodeObject DELMAC_SUCCESS = new CodeObject("66003", "设备删除成功");

    public static final CodeObject DELMAC_FAILED = new CodeObject("66004", "设备删除失败");

    public static final CodeObject CHANGEMAC_SUCCESS = new CodeObject("66005", "设备修改成功");

    public static final CodeObject CHANGEMACECODE_SUCCESS = new CodeObject("66605", "设备修改成功,二维码已生成");

    public static final CodeObject CHANGEMAC_FAILED = new CodeObject("66006", "设备修改失败");

    public static final CodeObject ALARM_RULE_NON_EXIST = new CodeObject("alarmRule_10008", "规则不存在");
    public static final CodeObject ALARM_RULE_IDEXCEPTION = new CodeObject("alarmRule_10009", "规则id异常");
    public static final CodeObject COMPANY_SUCCESS = new CodeObject("66007", "单位新增成功");

    public static final CodeObject COMPANY_FAILED = new CodeObject("66008", "单位新增失败");

    public static final CodeObject COMPANY_ERROR = new CodeObject("66078", "单位新增异常");

    public static final CodeObject COMPANY_TYPE_ERROR = new CodeObject("66079", "无此类型");

    public static final CodeObject COMPANY_EDIT_SUCCESS = new CodeObject("66009", "单位修改成功");

    public static final CodeObject COMPANY_EDIT_FAILED = new CodeObject("66010", "单位修改失败");

    public static final CodeObject COMPANY_DEL_SUCCESS = new CodeObject("66099", "单位删除成功");

    public static final CodeObject COMPANY_DEL_FAILED = new CodeObject("66019", "单位删除失败");

    public static final CodeObject WECHATACCOUNT_ERROR = new CodeObject("66011", "微信绑定异常");

    public static final CodeObject COMPANY_HASBIND = new CodeObject("66012", "单位已绑定");

    public static final CodeObject COMPANY_BIND_SUCCESS = new CodeObject("66013", "单位绑定成功");

    public static final CodeObject COMPANY_BIND_FAILED = new CodeObject("66014", "单位绑定失败");

    public static final CodeObject COMPANY_UNTYING_ERROR = new CodeObject("66015", "解绑异常");

    public static final CodeObject COMPANY_BIND_NONE = new CodeObject("66016", "用户没有绑定此单位");

    public static final CodeObject COMPANY_UNTYING_SUCCESS = new CodeObject("66017", "用户解绑成功");

    public static final CodeObject COMPANY_UNTYING_FAILED = new CodeObject("66018", "用户解绑失败");

    public static final CodeObject PRO_ADD_SUCCESS = new CodeObject("66019", "项目新增成功");

    public static final CodeObject PRO_ADD_FAILED = new CodeObject("66020", "项目新增失败");

    public static final CodeObject PRO_ADD_ERROR = new CodeObject("66021", "项目新增异常");

    public static final CodeObject PRO_EIDT_SUCCESS = new CodeObject("66022", "项目编辑成功");

    public static final CodeObject PRO_EIDT_FAILED = new CodeObject("66023", "项目编辑失败");

    public static final CodeObject PRO_EIDT_ERROR = new CodeObject("66024", "项目编辑异常");

    public static final CodeObject PRO_DEL_SUCCESS = new CodeObject("66025", "项目删除成功");

    public static final CodeObject PRO_DEL_FAILED = new CodeObject("66026", "项目删除失败,没有此项目");

    public static final CodeObject PRO_DEL_FAILED2 = new CodeObject("66027", "项目删除失败");

    public static final CodeObject TAG_ADD_ERROR = new CodeObject("90001", "标签新增异常");

    public static final CodeObject TAG_EDIT_ERROR = new CodeObject("90002", "标签编辑异常");

    public static final CodeObject TAG_DEL_ERROR = new CodeObject("90003", "标签删除异常");

    public static final CodeObject TAG_TB_ERROR = new CodeObject("90004", "标签同步异常");

    public static final CodeObject TAG_TB_SUCCESS = new CodeObject("90005", "标签同步成功");

    public static final CodeObject TAG_DA_ERROR = new CodeObject("90008", "打标签异常");

    public static final CodeObject TAG_DA_SUCCESS = new CodeObject("90009", "用户打标签成功");

    public static final CodeObject TAG_QX_ERROR = new CodeObject("90010", "标签取消异常");

    public static final CodeObject TAG_QX_SUCCESS = new CodeObject("90011", "标签取消成功");


    public static final CodeObject ORDER_SUCCESS = new CodeObject("67001", "订单新增成功");
    public static final CodeObject ORDER_FAILED = new CodeObject("67002", "订单新增失败");
    public static final CodeObject ORDER_ERROR = new CodeObject("67003", "订单新增异常");
    public static final CodeObject ORDER_EDIT_SUCCESS = new CodeObject("67004", "订单编辑成功");
    public static final CodeObject ORDER_EDIT_FAILED = new CodeObject("67005", "订单编辑失败");
    public static final CodeObject ORDER_EDIT_ERROR = new CodeObject("67006", "订单编辑异常");
    public static final CodeObject ORDER_DEL_SUCCESS = new CodeObject("67007", "订单删除成功");
    public static final CodeObject ORDER_DEL_FAILED = new CodeObject("67008", "订单删除失败");
    public static final CodeObject ORDER_DEL_ERROR = new CodeObject("67009", "订单删除异常");


    //
    public static final CodeObject R_C_400 = new CodeObject("10400", "报修单已关闭，无法修改");
    public static final CodeObject R_C_404 = new CodeObject("10404", "无效的设备编号");
    public static final CodeObject R_C_405 = new CodeObject("10405", "无效的报修编号");

    //用户体系
    public static final CodeObject U_SYS_NOFOLLOW = new CodeObject("2002261", "没有关注公众号，请先关注公众号");

    public static final CodeObject U_SYS_ZCSB = new CodeObject("2002271", "帐号自动注册失败，请手动注册");

    public static final CodeObject U_SYS_CODE_ERROR = new CodeObject("2003041", "code异常");

    public static final CodeObject U_SYS_TEL_BIND = new CodeObject("2003042", "该号码已绑定帐号");

    public static final CodeObject U_SYS_WX_BIND = new CodeObject("2003043", "该微信已绑定帐号");

    public static final CodeObject U_SYS_BIND_ERROR = new CodeObject("2003044", "帐号绑定异常");

    public static final CodeObject U_SYS_YZ_ERROR = new CodeObject("2003045", "验证码失效");

    public static final CodeObject U_SYS_ZC_ERROR = new CodeObject("2003046", "帐号还未注册，请使用手机验证码登录");

    public static final CodeObject add_Max = new CodeObject("2004281", "超出提交最大限制50");
}
