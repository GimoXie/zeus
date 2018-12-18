package io.gimo.zeus.service.dto;

import io.gimo.zeus.db.plugin.interceptor.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zmxie on 2018/12/18.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDTO extends BasePage{

    private Long id;
    private String username;
    private String email;
    private String telephone;
    private Boolean isActive;
}
