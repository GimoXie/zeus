package io.gimo.zeus.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateRoleDTO implements Serializable {
    private static final long serialVersionUID = -4729251091271334682L;
    private String name;
    private String code;
    private String description;
    private Integer priority;
}
