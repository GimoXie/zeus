package io.gimo.zeus.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zmxie on 2018/12/11.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperationDTO extends BaseDTO {
    private String name;
    private String code;
}
