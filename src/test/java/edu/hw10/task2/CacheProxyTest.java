package edu.hw10.task2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CacheProxyTest {
    @Test
    public void cacheProxyInMemoryTest(@TempDir Path tempDir) {
        Path cachePath = tempDir.resolve("persist.json");

        Map<String, Object> memoryCache = new HashMap<>();
        IFibCalculator fibCalculator = CacheProxy.create(new FibCalculator(), FibCalculator.class, cachePath, memoryCache);

        long value = fibCalculator.fib(40);
        long cachedValue = fibCalculator.fib(40);

        assertThat(value).isEqualTo(cachedValue);
    }

    @Test
    public void cacheProxyPersistTest(@TempDir Path tempDir) throws IOException {
        Path cachePath = tempDir.resolve("persist.json");
        Files.createFile(cachePath);

        Map<String, Object> memoryCache = new HashMap<>();
        IFibCalculator fibCalculator = CacheProxy.create(new FibCalculator(), FibCalculator.class, cachePath, memoryCache);

        long firstValue = fibCalculator.fib(40);
        long secondValue = fibCalculator.fib(10);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(cachePath.toFile());
        assertThat(jsonNode.get("fib[40]").asLong()).isEqualTo(firstValue);
        assertThat(jsonNode.get("fib[10]").asLong()).isEqualTo(secondValue);
    }
}
