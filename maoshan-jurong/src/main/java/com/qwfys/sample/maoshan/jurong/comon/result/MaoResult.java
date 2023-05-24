package com.qwfys.sample.maoshan.jurong.comon.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Builder
@Data
@AllArgsConstructor
@Schema(description = "统一返回实体")
public class MaoResult<T> {

    /**
     * 状态码
     */
    private String code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public MaoResult() {
    }

    public boolean isSuccess() {
        return Objects.equals(MaoResultCode.OK.value(), this.code);
    }
    public boolean isFail() {
        return !Objects.equals(MaoResultCode.OK.value(), this.code);
    }

    public static <T> MaoResult<T> success(T data) {
        MaoResult<T> maoResult = new MaoResult<>();
        maoResult.setData(data);
        maoResult.setCode(MaoResultCode.OK.value());
        return maoResult;
    }

    public static <T> MaoResult<T> success() {
        MaoResult<T> maoResult = new MaoResult<>();
        maoResult.setCode(MaoResultCode.OK.value());
        maoResult.setMsg(MaoResultCode.OK.getMsg());
        return maoResult;
    }

    public static <T> MaoResult<T> success(Integer code, T data) {
        return success(String.valueOf(code), data);
    }

    public static <T> MaoResult<T> success(String code, T data) {
        MaoResult<T> maoResult = new MaoResult<>();
        maoResult.setCode(code);
        maoResult.setData(data);
        return maoResult;
    }

    public static <T> MaoResult<T> showFailMsg(String msg) {
        log.error(msg);
        MaoResult<T> maoResult = new MaoResult<>();
        maoResult.setMsg(msg);
        maoResult.setCode(MaoResultCode.SHOW_FAIL.value());
        return maoResult;
    }

    public static <T> MaoResult<T> fail(MaoResultCode maoResultCode) {
        log.error(maoResultCode.toString());
        MaoResult<T> maoResult = new MaoResult<>();
        maoResult.setMsg(maoResultCode.getMsg());
        maoResult.setCode(maoResultCode.value());
        return maoResult;
    }

    public static <T> MaoResult<T> fail(MaoResultCode maoResultCode, T data) {
        log.error(maoResultCode.toString());
        MaoResult<T> maoResult = new MaoResult<>();
        maoResult.setMsg(maoResultCode.getMsg());
        maoResult.setCode(maoResultCode.value());
        maoResult.setData(data);
        return maoResult;
    }

    public static <T> MaoResult<T> fail(String code, String msg, T data) {
        log.error(msg);
        MaoResult<T> maoResult = new MaoResult<>();
        maoResult.setMsg(msg);
        maoResult.setCode(code);
        maoResult.setData(data);
        return maoResult;
    }

    public static <T> MaoResult<T> fail(String code, String msg) {
        return fail(code, msg, null);
    }

    public static <T> MaoResult<T> fail(Integer code, T data) {
        MaoResult<T> maoResult = new MaoResult<>();
        maoResult.setCode(String.valueOf(code));
        maoResult.setData(data);
        return maoResult;
    }
}
