package io.gimo.zeus.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -431351213982336175L;
    private Long parentId;
    private String name;
    private String icon;
    private String uri;
    private String code;
    private Integer type;
    private String description;
    private Integer priority;
}
