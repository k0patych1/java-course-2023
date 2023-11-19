package edu.hw6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    @TempDir Path tempDir;

    Path cloningFile;

    @BeforeEach
    public void setUp() throws IOException {
        var cloningPath = tempDir.resolve("Tinkoff Bank Biggest Secret.txt");
        cloningFile = Files.createFile(cloningPath);

        FileWriter fileWriter = new FileWriter(cloningFile.toFile());
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Hello, world!");
        printWriter.println("Goodbye, world!");
    }

    @Test
    public void singleCloneFileTest() throws FileNotFoundException {
        assertTrue(Files.exists(cloningFile));

        var firstCopy = Task2.cloneFile(cloningFile);
        assertTrue(Files.exists(cloningFile.resolveSibling("Tinkoff Bank Biggest Secret — копия.txt")));

        try {
            byte[] fileContent = Files.readAllBytes(cloningFile);
            byte[] copyFileContent = Files.readAllBytes(firstCopy);

            assertArrayEquals(fileContent, copyFileContent);
        } catch (IOException ignored) {}
    }

    @Test
    public void severalClonesFileTest() throws FileNotFoundException {
        Task2.cloneFile(cloningFile);
        assertTrue(Files.exists(cloningFile.resolveSibling("Tinkoff Bank Biggest Secret — копия.txt")));
        Task2.cloneFile(cloningFile);
        assertTrue(Files.exists(cloningFile.resolveSibling("Tinkoff Bank Biggest Secret — копия (2).txt")));
        Task2.cloneFile(cloningFile);
        assertTrue(Files.exists(cloningFile.resolveSibling("Tinkoff Bank Biggest Secret — копия (3).txt")));
    }

    @Test
    public void noCopyingFileTest() {
        assertThrows(FileNotFoundException.class, () -> Task2.cloneFile(Path.of("noFile.txt")));
    }
}

