package io.gimo.zeus.service.mapper;

import io.gimo.zeus.entity._do.zeusdb.SysOperationDO;
import io.gimo.zeus.entity.dto.OperationDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by gimo on 2019/1/8.
 */
@Component
public class OperationConverter {

    @Service
    public class OperationMapper extends AbstractMapper<OperationDTO, SysOperationDO> {

    }

}
