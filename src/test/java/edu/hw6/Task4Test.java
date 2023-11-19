package edu.hw6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    public void compositeWriteTest(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("test");
        Task4.compositeWrite(tempFile);

        String content = Files.readString(tempFile);
        assertThat(content).isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");
    }
}
