package io.gimo.zeus.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDTO extends BaseDTO {

    private static final long serialVersionUID = -4196630167388156395L;
    private String name;
    private String code;
    private String description;
    private Integer priority;
}
