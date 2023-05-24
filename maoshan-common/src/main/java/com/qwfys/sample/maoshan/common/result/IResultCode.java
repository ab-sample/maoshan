package com.qwfys.sample.maoshan.common.result;

/**
 * @author liuwenke
 * @since 0.0.1
 */
public interface IResultCode {
    /**
     * 是否成功
     *
     * @return
     */
    Boolean isSuccess();

    /**
     * 返回状态码
     *
     * @return
     */
    String resultCode();

    /**
     * 状态消息描述
     *
     * @return
     */
    String resultMessage();

    /**
     * 重写message
     *
     * @param messages
     * @return
     */
    void formateMessage(String... messages);
}
