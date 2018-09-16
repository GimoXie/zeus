package io.gimo.zeus.db.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页第一条数据偏移量
     */
    private Integer offSet;
    /**
     * 页码大小
     **/
    private Integer pageSize;
    /**
     * 排序字段名称
     **/
    private String sortName;
    /**
     * 排序方式 asc or desc
     **/
    private String sortOrder;
}
