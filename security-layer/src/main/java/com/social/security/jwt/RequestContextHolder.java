package com.social.security.jwt;

public interface RequestContextHolder {
    void setContext(RequestContext customContext);

    RequestContext getContext();

    void removeContext();
}
