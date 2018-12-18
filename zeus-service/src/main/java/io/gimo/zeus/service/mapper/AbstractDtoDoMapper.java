package io.gimo.zeus.service.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

public abstract class AbstractDtoDoMapper<DTO, DO> {

    protected MapperFactory mapperFactory;
    private Class<DTO> dtoClass;
    private Class<DO> doClass;

    @SuppressWarnings("unchecked")
    @PostConstruct
    private void init() {
        ParameterizedType type = (ParameterizedType) (this.getClass().getGenericSuperclass());
        this.dtoClass = (Class<DTO>) type.getActualTypeArguments()[0];
        this.doClass = (Class<DO>) type.getActualTypeArguments()[1];
    }

    public final Function<DTO, DO> convertDtoToDo = dto -> {
        if (dto == null) {
            return null;
        }
        DO _do = mapperFactory.getMapperFacade().map(dto, doClass);
        afterDtoToDo(dto, _do);

        return _do;
    };

    protected void afterDtoToDo(DTO dto, DO _do) {

    }

    public final Function<DO, DTO> convertDoToDto = _do -> {
        if (_do == null) {
            return null;
        }
        DTO dto = mapperFactory.getMapperFacade().map(_do, dtoClass);
        afterDoToDto(_do, dto);
        return dto;
    };

    protected void afterDoToDto(DO _do, DTO dto) {

    }


    @Autowired
    public void setMapperFactory(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }
}
