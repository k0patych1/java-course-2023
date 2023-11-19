package edu.hw6;

import edu.hw6.task1.DiskMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {
    Path tempFile;
    DiskMap diskMap;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("test", "txt");
        diskMap = new DiskMap(tempFile);
    }

    @Test
    public void DiskMapCreateTest() throws IOException {
        assertTrue(diskMap.isEmpty());
        assertThat(tempFile.toFile().length()).isEqualTo(0);
    }

    @Test
    public void DiskMapPutTest() throws IOException {
        diskMap.put("key1", "value1");
        assertThat(diskMap.get("key1")).isEqualTo("value1");
        String content = Files.readString(tempFile);

        assertThat(content).isEqualTo("key1:value1\n");
    }

    @Test
    public void DiskMapPutDuplicateKeyTest() throws IOException {
        diskMap.put("key1", "value1");
        diskMap.put("key1", "value2");
        assertThat(diskMap.get("key1")).isEqualTo("value2");
        String content = Files.readString(tempFile);
        assertThat(content).isEqualTo("key1:value2\n");
    }

    @Test
    public void DiskMapRemoveTest() throws IOException {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.remove("key1");
        String content = Files.readString(tempFile);
        assertThat(content).isEqualTo("key2:value2\n");
    }

    @Test
    public void DiskMapClearTest() throws IOException {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.clear();
        String content = Files.readString(tempFile);
        assertTrue(diskMap.isEmpty());
        assertTrue(content.isEmpty());
    }
}
