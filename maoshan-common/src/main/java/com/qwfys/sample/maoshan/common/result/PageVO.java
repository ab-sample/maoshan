package com.qwfys.sample.maoshan.common.result;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * @author liuwenke
 * @since 0.0.1
 */
@Data
//@ApiModel(value = "分页实体对象")
public class PageVO<T> {

    //@ApiModelProperty(value = "当前页")
    private Integer pageNum;
    //@ApiModelProperty(value = "每页显示条数")
    private Integer pageSize;
    //@ApiModelProperty(value = "总条数")
    private Long pageTotal;
    //@ApiModelProperty(value = "兼容")
    private Long count;
    //@ApiModelProperty(value = "是否存在上一页")
    private boolean hasPrevious;
    //@ApiModelProperty(value = "是否存在下一页")
    private boolean hasNext;
    //@ApiModelProperty(value = "数据实体")
    private List<T> data;

    public PageVO(Page<T> page) {
        this.pageNum = Convert.convert(Integer.class, page.getCurrent());
        this.pageSize = Convert.convert(Integer.class, page.getSize());
        this.pageTotal = page.getTotal();
        this.hasPrevious = page.hasPrevious();
        this.hasNext = page.hasNext();
        this.data = page.getRecords();
        this.count = this.pageTotal;
    }
}
