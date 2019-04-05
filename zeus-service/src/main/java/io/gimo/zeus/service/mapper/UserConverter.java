package io.gimo.zeus.service.mapper;

import io.gimo.zeus.entity.model.zeusdb.SysUserDO;
import io.gimo.zeus.entity.dto.ListUserDTO;
import io.gimo.zeus.entity.dto.SaveUserDTO;
import io.gimo.zeus.entity.dto.UpdateUserDTO;
import io.gimo.zeus.entity.dto.UserDTO;
import io.gimo.zeus.entity.vo.UserVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by gimo on 2019/1/8.
 */
@Component
public class UserConverter {

    @Service
    public class QueryUserMapper extends AbstractMapper<ListUserDTO, SysUserDO> {

    }

    @Service
    public class SaveUserMapper extends AbstractMapper<SaveUserDTO, SysUserDO> {

    }

    @Service
    public class UpdateUserMapper extends AbstractMapper<UpdateUserDTO, SysUserDO> {

    }

    @Service
    public class UserMapper extends AbstractMapper<UserDTO, SysUserDO> {

    }

    @Service
    public class UserViewMapper extends AbstractMapper<UserDTO, UserVO> {

    }
}
