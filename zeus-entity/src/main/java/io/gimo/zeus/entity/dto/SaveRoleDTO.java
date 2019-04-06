package io.gimo.zeus.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SaveRoleDTO implements Serializable {
    private static final long serialVersionUID = -4302221770294703183L;
    private String name;
    private String code;
    private String description;
    private Integer priority;
}
