package com.qwfys.sample.maoshan.common.result;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author liuwenke
 * @since 0.0.1
 */
@Builder
@Data
@AllArgsConstructor
@Schema(description = "统一返回实体")
public class HuaResult<T> {
    @Schema(description = "是否成功")
    private Boolean isSuccess = false;

    @Schema(description = "响应码")
    private String resultCode;

    @Schema(description = "消息")
    private String resultMessage;

    @Schema(description = "数据")
    private T data;

    public HuaResult() {
        this.resultCode = StrUtil.toString(HttpStatus.HTTP_OK);
        this.resultMessage = "";
        this.data = null;
    }

    public HuaResult(Boolean isSuccess, int resultCode, T data, String resultMessage) {
        this.isSuccess=isSuccess;
        this.resultCode = StrUtil.toString(resultCode);
        this.data = data;
        this.resultMessage = resultMessage;
    }

    // 错误时的构造器
    public HuaResult(int resultCode, T data, String resultMessage) {
        this.resultCode = StrUtil.toString(resultCode);
        this.data = data;
        this.resultMessage = resultMessage;
    }

    private HuaResult(IResultCode resultCode) {
        this(resultCode.isSuccess(), resultCode.resultCode(), resultCode.resultMessage(), null);
    }

    private HuaResult(IResultCode resultCode, String message) {
        this(resultCode.isSuccess(), resultCode.resultCode(), message, null);
    }

    private HuaResult(IResultCode resultCode, T data) {
        this(resultCode.isSuccess(), resultCode.resultCode(), resultCode.resultMessage(), data);
    }

    private HuaResult(IResultCode resultCode, String message, T data) {
        this(resultCode.isSuccess(), resultCode.resultCode(), message, data);
    }

    public static <T> HuaResult<T> data(T data) {
        return data(HuaResultCode.SUCCESS.resultMessage, data);
    }

    public static <T> HuaResult<T> data(String message, T data) {
        return data(HuaResultCode.SUCCESS.isSuccess(), HuaResultCode.SUCCESS.resultCode(), message, data);
    }

    public static <T> HuaResult<T> data(boolean isSuccess, String code, String message, T data) {
        return new HuaResult<>(isSuccess, code, null == data ? HuaResultCode.SUCCESS.resultCode : message, data);
    }

    public static <T> HuaResult<T> success() {
        return new HuaResult<>(HuaResultCode.SUCCESS);
    }

    public static <T> HuaResult<T> success(IResultCode resultCode) {
        return new HuaResult<>(resultCode);
    }

    public static <T> HuaResult<T> success(String message) {
        return new HuaResult<>(HuaResultCode.SUCCESS, message);
    }

    public static <T> HuaResult<T> success(IResultCode resultCode, String message) {
        return new HuaResult<>(resultCode, message);
    }

    public static <T> HuaResult<T> fail() {
        return new HuaResult<>(HuaResultCode.FAILURE);
    }

    public static <T> HuaResult<T> fail(IResultCode resultCode) {
        return new HuaResult<>(resultCode);
    }

    public static <T> HuaResult<T> fail(IResultCode resultCode, String message) {
        return new HuaResult<>(resultCode, message);
    }

    public static <T> HuaResult<T> fail(String message) {
        return new HuaResult<>(HuaResultCode.FAILURE, message);
    }

    public static <T> HuaResult<T> judge(boolean flag) {
        return flag ? success(HuaResultCode.SUCCESS.resultMessage()) : fail(HuaResultCode.FAILURE.resultMessage());
    }

    public static <T> HuaResult<T> judge(int flag) {
        return flag > 0 ? success(HuaResultCode.SUCCESS.resultMessage()) : fail(HuaResultCode.FAILURE.resultMessage());
    }
}
