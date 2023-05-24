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
//@Data
//@Accessors(chain = true)
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

    /**
     * 下面是重写构造方法
     *
     * @param resultCode
     */
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

    /**
     * 返回带有数据的情况，带有数据默认成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> data(T data) {
        return data(ResultCode.SUCCESS.resultMessage, data);
    }

    ///**
    // * 分页数据返回
    // *
    // * @param
    // * @param <T>
    // * @return
    // */
    //public static <T> Result<PageVO<T>> data(Page<T> page) {
    //    PageVO<T> pageVO = new PageVO(page);
    //    return data(ResultCode.SUCCESS.resultMessage, pageVO);
    //}

    public static <T> Result<T> data(String message, T data) {
        return data(ResultCode.SUCCESS.isSuccess(), ResultCode.SUCCESS.resultCode(), message, data);
    }

    public static <T> Result<T> data(boolean isSuccess, String code, String message, T data) {
        return new Result<>(isSuccess, code, null == data ? ResultCode.SUCCESS.resultCode : message, data);
    }

    /**
     * 处理成功返回数据情况
     *
     * @param
     * @param <T>
     * @return
     */
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


    /**
     * 处理失败的情况
     *
     * @param <T>
     * @return
     */
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

    /**
     *  flag大于0 则返回成功 小于0 则返回失败
     * @param flag
     * @return
     * @param <T>
     */
    public static <T> Result<T> judge(int flag) {
        return flag > 0 ? success(ResultCode.SUCCESS.resultMessage()) : fail(ResultCode.FAILURE.resultMessage());
    }

}
