package io.gimo.zeus.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SaveRolePermissionDTO implements Serializable {
    private static final long serialVersionUID = -5080176523820362392L;
    private Long roleId;
    private List<Long> permissionIdList;
}
