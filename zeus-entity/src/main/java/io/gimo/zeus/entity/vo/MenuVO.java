package io.gimo.zeus.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MenuVO extends BaseVO{
    private static final long serialVersionUID = 2219186025805275953L;
    private Long id;
    private Long parentId;
    private String name;
    private String icon;
    private String uri;
    private Integer priority;
    private Integer level;
    private List<MenuVO> submenuList;
    private List<String> operationCode;
}
