package io.gimo.zeus.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVO extends BaseVO {
    private Long id;
    private String name;
    private String type;
    private String description;
    private Boolean isActive;
}
