package io.gimo.zeus.db.plugin.interceptor;

import lombok.Data;

@Data
public abstract class BasePage {

    /**
     * 当前页第一条数据偏移量
     */
    private Integer offset;
    /**
     * 每页数据量
     **/
    private Integer limit;
    /**
     * 排序字段名称
     **/
    private String sort;
    /**
     * 排序方式 asc or desc
     **/
    private String order;
    /**
     * 搜索关键字
     */
    private String search;

}
