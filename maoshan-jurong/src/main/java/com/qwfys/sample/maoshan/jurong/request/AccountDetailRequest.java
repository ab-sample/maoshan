package com.qwfys.sample.maoshan.jurong.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "账号详情请求参数")
@Data
@Accessors(chain = true)
public class AccountDetailRequest {

    @Schema(description = "用户ID")
    private Long userId;
}
