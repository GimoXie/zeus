package io.gimo.zeus.service;

import io.gimo.zeus.service.security.ZeusUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public ZeusUser getCurrentUser() {
        return (ZeusUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
