package io.gimo.zeus.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDTO extends BaseDTO {
    private String name;
    private String code;
    private String description;
}
