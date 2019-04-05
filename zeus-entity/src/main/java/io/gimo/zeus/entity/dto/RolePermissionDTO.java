package io.gimo.zeus.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 2392784333647521173L;
    private Long roleId;
    private Long permissionId;
    private List<Long> permissionIdList;
}
