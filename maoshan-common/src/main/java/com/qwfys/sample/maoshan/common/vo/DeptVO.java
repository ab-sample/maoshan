package com.qwfys.sample.maoshan.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Schema(description = "部门")
@Data
@Accessors(chain = true)
public class DeptVO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(description = "部门ID")
    private Long id;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "部门路径")
    private String path;
}
