package io.gimo.zeus.entity.dto;

import io.gimo.zeus.entity.bo.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseDTO extends BasePage{

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
