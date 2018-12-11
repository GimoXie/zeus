package io.gimo.zeus.web.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVO extends BaseVO{
    private Long id;
    private Long parentId;
    private String name;
    private String icon;
    private String url;
    private String description;
    private Integer priority;
    private Integer level;
    private List<MenuVO> submenuList;
    private List<String> operationCode;
}
