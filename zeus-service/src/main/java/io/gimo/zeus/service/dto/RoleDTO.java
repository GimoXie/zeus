package io.gimo.zeus.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDTO extends BaseDTO {
    private String name;
    private String type;
    private String description;
}
