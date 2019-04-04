package io.gimo.zeus.entity.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionVO extends BaseVO{
    private static final long serialVersionUID = 647101431092360073L;
    private Long roleId;
    private Long permissionId;
}
