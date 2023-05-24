package com.qwfys.sample.maoshan.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liuwenke
 * @since 0.0.1
 */
@Schema(description = "账号详情请求参数")
@Data
@Accessors(chain = true)
public class AccountDetailDTO {

    @Schema(description = "用户ID")
    private Long userId;
}
