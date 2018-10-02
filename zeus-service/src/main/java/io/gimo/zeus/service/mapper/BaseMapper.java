package io.gimo.zeus.service.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

/**
 * java bean 映射工具
 * @param <REG>
 * @param <REP>
 * @param <M>
 */
public abstract class BaseMapper<REG extends RequestEntity, REP extends ResponseEntity, M> {
    @Autowired
    private MapperFactory mapperFactory;
    private Class<REG> entityRequestClazz;
    private Class<REP> entityResponseClazz;
    private Class<M> modelClazz;

    @SuppressWarnings("unchecked")
    @PostConstruct
    private void init() {
        ParameterizedType type = (ParameterizedType) (this.getClass().getGenericSuperclass());
        this.entityRequestClazz = (Class<REG>) type.getActualTypeArguments()[0];
        this.entityResponseClazz = (Class<REP>) type.getActualTypeArguments()[1];
        this.modelClazz = (Class<M>) type.getActualTypeArguments()[2];
    }

    public final Function<REG, M> TO_DB_MODEL = request -> {
        if (request == null) {
            return null;
        }
        M model = mapperFactory.getMapperFacade().map(request, modelClazz);
        afterToDBModel(request, model);

        return model;
    };

    /**
     * 属性从 request 拷贝到 model 以后做一些后续处理
     */
    protected void afterToDBModel(REG request, M model) {

    }

    public final Function<M, REP> TO_WEB_ENTITY = model -> {
        if (model == null) {
            return null;
        }
        REP response = mapperFactory.getMapperFacade().map(model, entityResponseClazz);
        afterToWebEntity(model, response);
        return response;
    };

    /**
     * 属性从 model 拷贝到 response 以后做一些后续处理
     */
    protected void afterToWebEntity(M model, REP response) {

    }
}
