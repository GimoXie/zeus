package io.gimo.zeus.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolePermissionDTO extends BaseDTO {

    private Long roleId;
    private Long permissionId;
    private List<Long> permissionIdList;
}