package io.gimo.zeus.db.plugin.interceptor;

import lombok.Data;

@Data
public abstract class BasePage {

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
    /**
     * 搜索关键字
     */
    private String searchText;

}