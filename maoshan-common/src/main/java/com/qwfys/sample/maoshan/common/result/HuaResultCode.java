package com.qwfys.sample.maoshan.common.result;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author liuwenke
 * @since 0.0.1
 */

@AllArgsConstructor
@NoArgsConstructor
public enum HuaResultCode implements IResultCode {
    /**
     * SpringMVC默认级别的异常封装
     */
    METHOD_NOT_SUPPORT(false, "405", "请求方法类型不支持,请检查请求类型！"),
    MEDIA_TYPE_NOT_SUPPORT(false, "405", "HTTP媒体类型不支持异常!"),
    MEDIA_TYPE_NOT_ACCEPTABLE(false, "406", "客户端请求期望响应的媒体类型与服务器响应的媒体类型不一致!"),
    MISSING_PATH_VARIABLE(false, "500", "缺少可选的路径参数!"),
    MISSING_SERVLET_REQUEST_PARAMETER(false, "400", "缺少请求参数!"),
    REQUEST_BINDING(false, "400", "请求参数不合法，请联系管理员检查前端配置"),
    CONVERSION_NOT_SUPPORTED(false, "500", "参数绑定异常!"),
    TYPE_MIS_MATCH(false, "400", "类型不匹配异常!"),
    MESSAGE_NOT_READABLE(false, "400", "消息不可读异常!"),
    MESSAGE_NOT_WRITABLE(false, "500", "消息不可写异常!"),
    METHOD_ARGUMENT_NOT_VALID(false, "400", "请求参数不合法！"),
    MISSING_SERVLET_REQUEST_PART(false, "400", ""),
    BIND(false, "400", "请求参数不合法！"),
    NO_HANDLER_FOUND(false, "404", "没有找到合适的处理器,处理器可能不存在,请检查路径是否正确！"),
    ASYNC_REQUEST_TIMEOUT(false, "503", "异步请求超时"),


    /**
     * 不可预知系统异常
     */
    SERVER_ERROR(false, "99999", "抱歉，系统繁忙，请稍后重试！"),

    /**
     * 基本的返回
     */
    SUCCESS(true, "00000", "操作成功！"),
    FAILURE(false, "A0001", "操作失败！"),
    PARMERROR(false, "A0002", "请求参数不合法！"),
    NO_DATA(false, "A0003", "暂无数据"),
    NO_FILE(false, "A0004", "没有文件"),
    NO_PERMISSION_FILE(false, "A0005", "没有文件权限"),
    LOGIN_FAIL(false, "A0006", "用户不存在或被禁用"),
    DATA_EXCEPTION(false, "A0007", "数据异常"),

    /**
     * 系统可预知异常
     */
    UN_KNOW(false, "A0100", "未知错误"),
    NULL_POINT(false, "A0101", "空指针异常"),
    HTTP_MESSAGE_NOT_READABLE(false, "A0102", "http请求参数转换异常,参数格式错误"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(false, "A0103", "http请求方式不支持"),


    /**
     * 参数相关
     */
    PARAM_ERROR(false, "A0200", "用户请求参数错误"),
    ENUM_NOT_EXIST(false, "A0201", "枚举名不存在"),
    ENUM_PARSE_EXIST(false, "A0202", "获取所有枚举过程中解析失败，请联系开发人员解决"),
    ADD_NOT_NEED_ID(false, "A0203", "新增的时候主键不需要传递"),

    UPDATE_ID_NOT_NULL(false, "A0204", "编辑的时候不需要传递主键"),
    MISSING_ANNO_PARAM(false, "A0205", "注解缺失必要的参数"),

    ;
    public Boolean isSuccess;
    public String resultCode;
    public String resultMessage;

    @Override
    public Boolean isSuccess() {
        return this.isSuccess;
    }

    @Override
    public String resultCode() {
        return this.resultCode;
    }

    @Override
    public String resultMessage() {
        return this.resultMessage;
    }

    @Override
    public void formateMessage(String... messages) {
        this.resultMessage = StrUtil.format(this.resultMessage, messages);
    }
}
