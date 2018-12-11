package io.gimo.zeus.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.gimo.zeus.db._do.zeusdb.SysOperationDO;
import io.gimo.zeus.db._do.zeusdb.SysOperationExample;
import io.gimo.zeus.db._do.zeusdb.SysPermissionOperationDO;
import io.gimo.zeus.db._do.zeusdb.SysPermissionOperationExample;
import io.gimo.zeus.db.dao.zeusdb.SysOperationDAO;
import io.gimo.zeus.db.dao.zeusdb.SysPermissionOperationDAO;
import io.gimo.zeus.service.OperationService;
import io.gimo.zeus.service.dto.OperationDTO;
import io.gimo.zeus.service.mapper.OperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zmxie on 2018/12/11.
 */
@Service
public class OperationServiceImpl implements OperationService {

    private SysPermissionOperationDAO sysPermissionOperationDAO;
    private SysOperationDAO sysOperationDAO;
    private OperationMapper operationMapper;

    @Override
    public Map<Long, List<OperationDTO>> getPermissionOperationMapByPermissionId(List<Long> permissionIdList) {
        SysPermissionOperationExample permissionOperationExample = new SysPermissionOperationExample();
        permissionOperationExample.createCriteria().andPermissionIdIn(permissionIdList).andIsActiveEqualTo(true);
        List<SysPermissionOperationDO> sysPermissionOperationList = sysPermissionOperationDAO.selectByExample(permissionOperationExample);
        SysOperationExample operationExample = new SysOperationExample();
        operationExample.createCriteria().andIsActiveEqualTo(true);
        List<SysOperationDO> sysOperationList = sysOperationDAO.selectByExample(operationExample);
        Map<Long, SysOperationDO> operationMap = sysOperationList.stream().collect(Collectors.toMap(SysOperationDO::getId, operation -> operation));
        Map<Long, List<OperationDTO>> permissionOperationMap = Maps.newHashMap();
        sysPermissionOperationList.forEach(permissionOperation -> {
            OperationDTO operation = operationMapper.convertDoToDto.apply(operationMap.get(permissionOperation.getOperationId()));
            if (!permissionOperationMap.containsKey(permissionOperation.getPermissionId())) {
                List<OperationDTO> operationList = Lists.newArrayList();
                operationList.add(operation);
                permissionOperationMap.put(permissionOperation.getPermissionId(), operationList);
            } else {
                List<OperationDTO> operationList = permissionOperationMap.get(permissionOperation.getPermissionId());
                operationList.add(operation);
                permissionOperationMap.put(permissionOperation.getPermissionId(), operationList);
            }
        });
        return permissionOperationMap;
    }

    @Autowired
    public void setSysPermissionOperationDAO(SysPermissionOperationDAO sysPermissionOperationDAO) {
        this.sysPermissionOperationDAO = sysPermissionOperationDAO;
    }

    @Autowired
    public void setSysOperationDAO(SysOperationDAO sysOperationDAO) {
        this.sysOperationDAO = sysOperationDAO;
    }

    @Autowired
    public void setOperationMapper(OperationMapper operationMapper) {
        this.operationMapper = operationMapper;
    }
}
