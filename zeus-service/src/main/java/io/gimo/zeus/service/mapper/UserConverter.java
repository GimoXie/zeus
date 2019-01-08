package io.gimo.zeus.service.mapper;

import io.gimo.zeus.entity._do.zeusdb.SysUserDO;
import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.entity.vo.UserVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by zmxie on 2019/1/8.
 */
@Component
public class UserConverter {

    @Service
    public class UserMapper extends AbstractMapper<UserDTO, SysUserDO> {

    }

    @Service
    public class UserViewMapper extends AbstractMapper<UserDTO, UserVO> {

    }
}
