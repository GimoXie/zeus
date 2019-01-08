package io.gimo.zeus.service.mapper;

import io.gimo.zeus.db.plugin.interceptor.Page;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Bean映射工具
 * @param <T>
 * @param <S>
 */
public abstract class AbstractMapper<T, S> {

    private MapperFactory mapperFactory;
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

    private void afterConvert(T t, S s) {

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

    private void afterReconvert(S s, T t) {

    }

    /**
     * 将第一种类型的分页结果映射为第二种类型的分页结果
     */
    public final Function<Page<T>, Page<S>> pageConvert = t -> {
        if (t == null) {
            return null;
        }
        Page<S> result = new Page<>();
        mapperFactory.getMapperFacade().map(t, result);
        result.setRows(t.getRows().stream().map(convert).collect(Collectors.toList()));
        return result;
    };

    /**
     * 将第二种类型的分页结果映射为第一种类型的分页结果
     */
    public final Function<Page<S>, Page<T>> pageReconvert = s -> {
        if (s == null) {
            return null;
        }
        Page<T> result = new Page<>();
        mapperFactory.getMapperFacade().map(s, result);
        result.setRows(s.getRows().stream().map(reconvert).collect(Collectors.toList()));
        return result;
    };

    @Autowired
    public void setMapperFactory(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }
}
