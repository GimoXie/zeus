package io.gimo.zeus.entity._do;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseDO {

    /**
     * 自增长id
     **/
    private Long id;
    /**
     * 是否有效
     **/
    private Boolean active;
    /**
     * 数据创建时间
     **/
    private LocalDateTime createTime;
    /**
     * 数据创建用户id
     **/
    private Long createUserId;
    /**
     * 数据最后修改时间
     **/
    private LocalDateTime changeTime;
    /**
     * 数据最后修改用户id
     **/
    private Long changeUserId;
}
