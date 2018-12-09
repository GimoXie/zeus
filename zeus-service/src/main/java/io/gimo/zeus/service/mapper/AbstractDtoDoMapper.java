package io.gimo.zeus.service.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

/**
 * java bean 映射工具
 *
 * @param <DTO>
 * @param <DO>
 */
public abstract class AbstractDtoDoMapper<DTO, DO> {

    private MapperFactory mapperFactory;
    private Class<DTO> dtoClass;
    private Class<DO> doClass;

    @SuppressWarnings("unchecked")
    @PostConstruct
    private void init() {
        ParameterizedType type = (ParameterizedType) (this.getClass().getGenericSuperclass());
        this.dtoClass = (Class<DTO>) type.getActualTypeArguments()[0];
        this.doClass = (Class<DO>) type.getActualTypeArguments()[1];
    }

    public final Function<DTO, DO> convertDtoToDo = request -> {
        if (request == null) {
            return null;
        }
        DO _do = mapperFactory.getMapperFacade().map(request, doClass);
        afterDtoToDo(request, _do);

        return _do;
    };

    /**
     * 属性从 request 拷贝到 _do 以后做一些后续处理
     */
    protected void afterDtoToDo(DTO request, DO _do) {

    }

    public final Function<DO, DTO> convertDoToDto = _do -> {
        if (_do == null) {
            return null;
        }
        DTO response = mapperFactory.getMapperFacade().map(_do, dtoClass);
        afterDoToDto(_do, response);
        return response;
    };

    /**
     * 属性从 _do 拷贝到 response 以后做一些后续处理
     */
    protected void afterDoToDto(DO _do, DTO response) {

    }


    @Autowired
    public void setMapperFactory(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }
}
