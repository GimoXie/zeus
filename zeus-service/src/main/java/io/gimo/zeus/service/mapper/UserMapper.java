package io.gimo.zeus.service.mapper;

import com.google.common.collect.Lists;
import io.gimo.zeus.db._do.zeusdb.SysUserDO;
import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.service.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper extends AbstractDtoDoMapper<UserDTO, SysUserDO> {

    public Page<UserDTO> convertPageData(Page<SysUserDO> page) {
        Page<UserDTO> result = new Page<>();
        mapperFactory.getMapperFacade().map(page, result);
        List<UserDTO> userDTOList = Lists.newArrayList();
        page.getRows().forEach(row -> userDTOList.add(convertDoToDto.apply(row)));
        result.setRows(userDTOList);
        return result;
    }
}
