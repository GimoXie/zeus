package io.gimo.zeus.web.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

/**
 * Created by zmxie on 2018/12/10.
 */
public abstract class AbstractVoToDtoMapper<VO, DTO> {

    private MapperFactory mapperFactory;
    private Class<VO> voClass;
    private Class<DTO> dtoClass;

    @SuppressWarnings("unchecked")
    @PostConstruct
    private void init() {
        ParameterizedType type = (ParameterizedType) (this.getClass().getGenericSuperclass());
        this.voClass = (Class<VO>) type.getActualTypeArguments()[0];
        this.dtoClass = (Class<DTO>) type.getActualTypeArguments()[1];
    }

    public final Function<VO, DTO> convertVoToDto = vo -> {
        if (vo == null) {
            return null;
        }
        DTO dto = mapperFactory.getMapperFacade().map(vo, dtoClass);
        afterVoToDto(vo, dto);
        return dto;
    };

    private void afterVoToDto(VO vo, DTO dto) {

    }

    public final Function<DTO, VO> convertDtoToVo = dto -> {
        if (dto == null) {
            return null;
        }
        VO vo = mapperFactory.getMapperFacade().map(dto, voClass);
        afterDtoToVo(dto, vo);
        return vo;
    };

    private void afterDtoToVo(DTO dto, VO vo) {

    }

    @Autowired
    public void setMapperFactory(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }
}
