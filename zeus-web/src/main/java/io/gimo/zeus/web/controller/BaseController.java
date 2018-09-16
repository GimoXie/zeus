package io.gimo.zeus.web.controller;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected static final int CODE_SUCCESS = 1;
    protected static final int CODE_FAILURE = 0;

    protected Map<String, Object> generateResult(Integer code, String message, Object data) {
        Map<String,Object> result = Maps.newHashMap();
        result.put("code", code);
        result.put("message", message);
        result.put("data", data);
        return result;
    }

}
