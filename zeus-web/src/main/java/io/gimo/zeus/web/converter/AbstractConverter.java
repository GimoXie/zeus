package io.gimo.zeus.web.converter;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

/**
 * Created by zmxie on 2018/12/10.
 */
public abstract class AbstractConverter<VO, DTO> {

    protected MapperFactory mapperFactory;
    private Class<VO> voClass;

    @SuppressWarnings("unchecked")
    @PostConstruct
    private void init() {
        ParameterizedType type = (ParameterizedType) (this.getClass().getGenericSuperclass());
        this.voClass = (Class<VO>) type.getActualTypeArguments()[0];
    }

    public final Function<DTO, VO> convert = dto -> {
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
