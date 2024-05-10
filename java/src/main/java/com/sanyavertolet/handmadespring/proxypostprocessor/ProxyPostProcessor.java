package com.sanyavertolet.handmadespring.proxypostprocessor;

public interface ProxyPostProcessor {
    @SuppressWarnings("rawtypes")
    Object replaceWithProxyIfNeeded(Object obj, Class implClass);
}
