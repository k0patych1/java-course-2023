package edu.hw10.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;

public final class CacheProxy implements InvocationHandler {
    private final Object target;

    private final Path cachePath;

    private final Map<String, Object> memoryCache;

    private CacheProxy(Object target, Path cachePath, Map<String, Object> memoryCache) {
        this.target = target;
        this.cachePath = cachePath;
        this.memoryCache = memoryCache;
    }

    public static <T> T create(T target, Class<?> targetClass, Path cachePath, Map<String, Object> memoryCache) {
        return (T) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            targetClass.getInterfaces(),
            new CacheProxy(target, cachePath, memoryCache));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation == null) {
            return method.invoke(target, args);
        }

        String cacheKey = method.getName() + Arrays.toString(args);
        if (memoryCache.containsKey(cacheKey)) {
            return memoryCache.get(cacheKey);
        }

        Object result = method.invoke(target, args);
        memoryCache.put(cacheKey, result);

        if (cacheAnnotation.persist()) {
            persistResult();
        }

        return result;
    }

    private void persistResult()
        throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(cachePath.toString()), memoryCache);
    }
}
