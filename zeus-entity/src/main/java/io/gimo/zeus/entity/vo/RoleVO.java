package io.gimo.zeus.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVO extends BaseVO {
    private static final long serialVersionUID = 3729279185682368617L;
    private Long id;
    private String name;
    private String code;
    private String description;
    private String priority;
    private Boolean active;
}
