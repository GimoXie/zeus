package io.gimo.zeus.entity.dto;

import io.gimo.zeus.entity.bo.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListUserDTO extends BasePage implements Serializable {
    private static final long serialVersionUID = 2817483500536233896L;
    private String username;
    private String nickName;
    private String email;
    private String telephone;
}
