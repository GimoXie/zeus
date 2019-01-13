package io.gimo.zeus.entity.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionVO extends BaseVO{

    private Long roleId;
    private Long permissionId;
}
