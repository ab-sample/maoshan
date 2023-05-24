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
public class Result<T> {
    @Schema(description = "是否成功")
    private Boolean isSuccess = false;

    @Schema(description = "响应码")
    private String resultCode;

    @Schema(description = "消息")
    private String resultMessage;

    @Schema(description = "数据")
    private T data;

    public Result() {
        this.resultCode = StrUtil.toString(HttpStatus.HTTP_OK);
        this.resultMessage = "";
        this.data = null;
    }

    public Result(Boolean isSuccess,int resultCode, T data, String resultMessage) {
        this.isSuccess=isSuccess;
        this.resultCode = StrUtil.toString(resultCode);
        this.data = data;
        this.resultMessage = resultMessage;
    }

    // 错误时的构造器
    public Result(int resultCode, T data, String resultMessage) {
        this.resultCode = StrUtil.toString(resultCode);
        this.data = data;
        this.resultMessage = resultMessage;
    }

    private Result(IResultCode resultCode) {
        this(resultCode.isSuccess(), resultCode.resultCode(), resultCode.resultMessage(), null);
    }

    private Result(IResultCode resultCode, String message) {
        this(resultCode.isSuccess(), resultCode.resultCode(), message, null);
    }

    private Result(IResultCode resultCode, T data) {
        this(resultCode.isSuccess(), resultCode.resultCode(), resultCode.resultMessage(), data);
    }

    private Result(IResultCode resultCode, String message, T data) {
        this(resultCode.isSuccess(), resultCode.resultCode(), message, data);
    }

    public static <T> Result<T> data(T data) {
        return data(ResultCode.SUCCESS.resultMessage, data);
    }

    public static <T> Result<T> data(String message, T data) {
        return data(ResultCode.SUCCESS.isSuccess(), ResultCode.SUCCESS.resultCode(), message, data);
    }

    public static <T> Result<T> data(boolean isSuccess, String code, String message, T data) {
        return new Result<>(isSuccess, code, null == data ? ResultCode.SUCCESS.resultCode : message, data);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(IResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(ResultCode.SUCCESS, message);
    }

    public static <T> Result<T> success(IResultCode resultCode, String message) {
        return new Result<>(resultCode, message);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.FAILURE);
    }

    public static <T> Result<T> fail(IResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> fail(IResultCode resultCode, String message) {
        return new Result<>(resultCode, message);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(ResultCode.FAILURE, message);
    }

    public static <T> Result<T> judge(boolean flag) {
        return flag ? success(ResultCode.SUCCESS.resultMessage()) : fail(ResultCode.FAILURE.resultMessage());
    }

    public static <T> Result<T> judge(int flag) {
        return flag > 0 ? success(ResultCode.SUCCESS.resultMessage()) : fail(ResultCode.FAILURE.resultMessage());
    }
}
