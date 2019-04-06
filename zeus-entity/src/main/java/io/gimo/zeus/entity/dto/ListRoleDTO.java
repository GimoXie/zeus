package io.gimo.zeus.entity.dto;

import io.gimo.zeus.entity.bo.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListRoleDTO extends BasePage implements Serializable {
    private static final long serialVersionUID = 4842175296676187705L;
    private String name;
    private String code;
}
