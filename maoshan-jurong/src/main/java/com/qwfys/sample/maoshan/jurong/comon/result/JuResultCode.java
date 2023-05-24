package com.qwfys.sample.maoshan.jurong.comon.result;

import lombok.ToString;

/**
 * @author liuwenke
 * @since 0.0.1
 */
@ToString
public enum JuResultCode {

    OK("00000", "ok"),

    SHOW_FAIL("A00001", ""),

    REMOTE_CALL_FAIL("REMOTE_CALL_FAIL", "远程接口调用失败"),

    PARSING_JSON_FAIL("PARSING_JSON_FAIL", "JSON解析失败"),

    /**
     * 用于直接显示提示系统的成功，内容由输入内容决定
     */
    SHOW_SUCCESS("A00002", ""),

    /**
     * 未授权
     */
    UNAUTHORIZED("A00004", "Unauthorized"),

    /**
     * 服务器出了点小差
     */
    EXCEPTION("A00005", "服务器出了点小差"),

    /**
     * 方法参数没有校验，内容由输入内容决定
     */
    METHOD_ARGUMENT_NOT_VALID("A00014", "方法参数没有校验");

    private final String code;

    private final String msg;

    public String value() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    JuResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
