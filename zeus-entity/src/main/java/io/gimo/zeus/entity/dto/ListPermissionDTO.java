package io.gimo.zeus.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListPermissionDTO implements Serializable {
    private static final long serialVersionUID = -6047846001593007683L;
    private String name;
    private String uri;
    private String code;
}
