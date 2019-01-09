package io.gimo.zeus.web.controller;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

abstract class BaseController {
    Logger logger = LoggerFactory.getLogger(getClass());

    private static final int CODE_SUCCESS = 1;
    private static final int CODE_FAILURE = 0;

    Map<String, Object> success(Object object) {
        return result(CODE_SUCCESS, "success", object);
    }

    Map<String, Object> failure(String message) {
        return result(CODE_FAILURE, message, null);
    }

    private Map<String, Object> result(Integer code, String message, Object object) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("code", code);
        result.put("message", message);
        result.put("data", object);
        return result;
    }

}
