package edu.hw6;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.io.TempDir;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.io.CleanupMode.NEVER;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Task2Test {
    @TempDir(cleanup = NEVER) Path tempDir;

    Path cloningPath = null;

    @Test
    @Order(1)
    public void singleCloneFileTest() throws IOException {
        cloningPath = tempDir.resolve("Tinkoff Bank Biggest Secret.txt");
        var file = Files.createFile(cloningPath);

        FileWriter fileWriter = new FileWriter(file.toFile());
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Hello, world!");
        printWriter.println("Goodbye, world!");
        assertTrue(Files.exists(file));

        var firstCopy = Task2.cloneFile(cloningPath);
        assertTrue(Files.exists(file.resolveSibling("Tinkoff Bank Biggest Secret — копия.txt")));

        try {
            byte[] fileContent = Files.readAllBytes(file);
            byte[] copyFileContent = Files.readAllBytes(firstCopy);

            assertArrayEquals(fileContent, copyFileContent);
        } catch (IOException ignored) {
        }
    }

    @Test
    @Order(2)
    public void secondCloneFileTest() {
        Task2.cloneFile(cloningPath);
        assertTrue(Files.exists(cloningPath.resolveSibling("Tinkoff Bank Biggest Secret — копия (2).txt")));
    }

    @Test
    @Order(3)
    public void thirdCloneFileTest() {
        Task2.cloneFile(cloningPath);
        assertTrue(Files.exists(cloningPath.resolveSibling("Tinkoff Bank Biggest Secret — копия (3).txt")));
    }
}

