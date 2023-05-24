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
public class JuResult<T> {

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

    public JuResult() {
    }

    public boolean isSuccess() {
        return Objects.equals(JuResultCode.OK.value(), this.code);
    }
    public boolean isFail() {
        return !Objects.equals(JuResultCode.OK.value(), this.code);
    }

    public static <T> JuResult<T> success(T data) {
        JuResult<T> juResult = new JuResult<>();
        juResult.setData(data);
        juResult.setCode(JuResultCode.OK.value());
        return juResult;
    }

    public static <T> JuResult<T> success() {
        JuResult<T> juResult = new JuResult<>();
        juResult.setCode(JuResultCode.OK.value());
        juResult.setMsg(JuResultCode.OK.getMsg());
        return juResult;
    }

    public static <T> JuResult<T> success(Integer code, T data) {
        return success(String.valueOf(code), data);
    }

    public static <T> JuResult<T> success(String code, T data) {
        JuResult<T> juResult = new JuResult<>();
        juResult.setCode(code);
        juResult.setData(data);
        return juResult;
    }

    public static <T> JuResult<T> showFailMsg(String msg) {
        log.error(msg);
        JuResult<T> juResult = new JuResult<>();
        juResult.setMsg(msg);
        juResult.setCode(JuResultCode.SHOW_FAIL.value());
        return juResult;
    }

    public static <T> JuResult<T> fail(JuResultCode juResultCode) {
        log.error(juResultCode.toString());
        JuResult<T> juResult = new JuResult<>();
        juResult.setMsg(juResultCode.getMsg());
        juResult.setCode(juResultCode.value());
        return juResult;
    }

    public static <T> JuResult<T> fail(JuResultCode juResultCode, T data) {
        log.error(juResultCode.toString());
        JuResult<T> juResult = new JuResult<>();
        juResult.setMsg(juResultCode.getMsg());
        juResult.setCode(juResultCode.value());
        juResult.setData(data);
        return juResult;
    }

    public static <T> JuResult<T> fail(String code, String msg, T data) {
        log.error(msg);
        JuResult<T> juResult = new JuResult<>();
        juResult.setMsg(msg);
        juResult.setCode(code);
        juResult.setData(data);
        return juResult;
    }

    public static <T> JuResult<T> fail(String code, String msg) {
        return fail(code, msg, null);
    }

    public static <T> JuResult<T> fail(Integer code, T data) {
        JuResult<T> juResult = new JuResult<>();
        juResult.setCode(String.valueOf(code));
        juResult.setData(data);
        return juResult;
    }
}
