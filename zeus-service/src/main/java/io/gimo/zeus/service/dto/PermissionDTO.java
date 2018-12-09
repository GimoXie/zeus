package io.gimo.zeus.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionDTO extends BaseDTO {
    private Long parentId;
    private String name;
    private String icon;
    private String url;
    private String description;
    private Integer priority;
}
