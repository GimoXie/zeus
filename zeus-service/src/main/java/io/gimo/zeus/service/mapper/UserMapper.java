package io.gimo.zeus.service.mapper;

import io.gimo.zeus.db._do.zeusdb.SysUserDO;
import io.gimo.zeus.service.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractDtoDoMapper<UserDTO, SysUserDO> {
}
