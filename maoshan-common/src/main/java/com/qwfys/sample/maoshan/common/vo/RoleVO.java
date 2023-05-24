package com.qwfys.sample.maoshan.jurong.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "角色")
@Data
@Accessors(chain = true)
public class RoleVO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(description = "角色ID")
    private Long id;

    @Schema(description = "角色编码")
    private String code;

    @Schema(description = "角色名称")
    private String name;
}
