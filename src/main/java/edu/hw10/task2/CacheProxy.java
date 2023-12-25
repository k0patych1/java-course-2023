package edu.hw10.task2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class CacheProxy implements InvocationHandler {
    private final Object target;

    private final Path cachePath;

    private final Map<String, Object> memoryCache;

    private CacheProxy(Object target, Path cachePath) throws IOException {
        this.target = target;
        this.cachePath = cachePath;
        this.memoryCache = new HashMap<>();
        if (cachePath.toFile().exists()) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(cachePath.toFile());
            for (Iterator<String> it = jsonNode.fieldNames(); it.hasNext();) {
                String fieldName = it.next();
                memoryCache.put(fieldName, jsonNode.get(fieldName).asLong());
            }
        }
    }

    public static <T> T create(T target, Class<?> targetClass, Path cachePath) throws IOException {
        return (T) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            targetClass.getInterfaces(),
            new CacheProxy(target, cachePath));
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
