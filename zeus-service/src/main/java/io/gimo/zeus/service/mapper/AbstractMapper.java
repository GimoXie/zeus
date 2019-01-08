package io.gimo.zeus.service.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

public abstract class AbstractMapper<T, S> {

    protected MapperFactory mapperFactory;
    private Class<T> firstClass;
    private Class<S> secondClass;

    @SuppressWarnings("unchecked")
    @PostConstruct
    private void init() {
        ParameterizedType type = (ParameterizedType) (this.getClass().getGenericSuperclass());
        this.firstClass = (Class<T>) type.getActualTypeArguments()[0];
        this.secondClass = (Class<S>) type.getActualTypeArguments()[1];
    }

    /**
     * 将第一种类型的实体映射为第二种类型的实体
     */
    public final Function<T, S> convert = t -> {
        if (t == null) {
            return null;
        }
        S s = mapperFactory.getMapperFacade().map(t, secondClass);
        afterConvert(t, s);
        return s;
    };

    protected void afterConvert(T t, S s) {

    }

    /**
     * 将第二种类型的实体映射为第一种类型的实体
     */
    public final Function<S, T> reconvert = s -> {
        if (s == null) {
            return null;
        }
        T t = mapperFactory.getMapperFacade().map(s, firstClass);
        afterReconvert(s, t);
        return t;
    };

    protected void afterReconvert(S s, T t) {

    }


    @Autowired
    public void setMapperFactory(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }
}
