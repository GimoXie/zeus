package io.gimo.zeus.service.mapper;

import io.gimo.zeus.db.dto.request.BaseRequest;
import io.gimo.zeus.db.dto.response.BaseResponse;

public abstract class BaseMapper<REG extends BaseRequest, REP extends BaseResponse, M> {
    /*//深度拷贝工具
    private final static DozerBeanMapper DOZER_BEAN_MAPPER = new DozerBeanMapper();
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

    public final Function<REG, M> TO_DB_PO = request -> {
        if (request == null) {
            return null;
        }
        M model = DOZER_BEAN_MAPPER.map(request, modelClazz);
        afterToDBModel(request, model);

        return model;
    };

    *//**
     * 属性从 request 拷贝到 model 以后做一些后续处理
     *//*
    protected void afterToDBModel(REG request, M model) {

    }

    public final Function<M, REP> TO_WEB_ENTITY = model -> {
        if (model == null) {
            return null;
        }
        REP response = DOZER_BEAN_MAPPER.map(model, entityResponseClazz);
        afterToWebEntity(model, response);
        return response;
    };

    *//**
     * 属性从 model 拷贝到 response 以后做一些后续处理
     *//*
    protected void afterToWebEntity(M model, REP response) {

    }*/
}
