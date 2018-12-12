<#assign principal = Session.SPRING_SECURITY_CONTEXT.authentication.principal>

<#macro generateMenu menuList>
    <#if menuList?? && menuList?size gt 0>
        <#list menuList as menu>
            <#if menu.submenuList?? && menu.submenuList?size gt 0>
            <li>
                <a href="${menu.url}">
                    <i class="${menu.icon}"></i>
                    <span class="nav-label">${menu.name}</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="<@level menu.level/>">
                    <@generateMenu menu.submenuList/>
                </ul>
            </li>
            <#else>
            <li>
                <a class="J_menuItem" href="${menu.url}">
                    <i class="${menu.icon}"></i>
                    <span class="nav-label">${menu.name}</span>
                </a>
            </li>
            </#if>
        </#list>
    </#if>
</#macro>

<#macro level menuLevel>
    <#switch menuLevel>
        <#case 1>nav nav-second-level<#break>
        <#case 2>nav nav-third-level<#break>
        <#default>nav nav-third-level
    </#switch>
</#macro>