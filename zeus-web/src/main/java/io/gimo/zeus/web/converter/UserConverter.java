package io.gimo.zeus.web.converter;

import com.google.common.collect.Lists;
import io.gimo.zeus.db.plugin.interceptor.Page;
import io.gimo.zeus.service.dto.UserDTO;
import io.gimo.zeus.web.vo.UserVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zmxie on 2018/12/18.
 */
@Component
public class UserConverter extends AbstractVoToDtoConverter<UserVO, UserDTO> {


    public Page<UserVO> convertToVo(Page<UserDTO> page) {
        Page<UserVO> result = new Page<>();
        mapperFactory.getMapperFacade().map(page, result);
        List<UserVO> userVOList = Lists.newArrayList();
        page.getRows().forEach(row -> userVOList.add(convertDtoToVo.apply(row)));
        result.setRows(userVOList);
        return result;
    }
}
