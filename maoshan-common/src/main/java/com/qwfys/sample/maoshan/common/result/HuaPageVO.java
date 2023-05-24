package com.qwfys.sample.maoshan.common.result;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author liuwenke
 * @since 0.0.1
 */
@Data
@Schema(description = "分页实体对象")
public class HuaPageVO<T> {

    @Schema(description = "当前页")
    private Integer pageNum;

    @Schema(description = "每页显示条数")
    private Integer pageSize;

    @Schema(description = "总条数")
    private Long pageTotal;

    @Schema(description = "兼容")
    private Long count;

    @Schema(description = "是否存在上一页")
    private boolean hasPrevious;

    @Schema(description = "是否存在下一页")
    private boolean hasNext;

    @Schema(description = "数据实体")
    private List<T> data;

    public HuaPageVO(Page<T> page) {
        this.pageNum = Convert.convert(Integer.class, page.getCurrent());
        this.pageSize = Convert.convert(Integer.class, page.getSize());
        this.pageTotal = page.getTotal();
        this.hasPrevious = page.hasPrevious();
        this.hasNext = page.hasNext();
        this.data = page.getRecords();
        this.count = this.pageTotal;
    }
}
