package edu.hw6;

import edu.hw6.task3.AbstractFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.task3.AbstractFilter.globMatches;
import static edu.hw6.task3.AbstractFilter.largerThan;
import static edu.hw6.task3.AbstractFilter.magicNumber;
import static edu.hw6.task3.AbstractFilter.readable;
import static edu.hw6.task3.AbstractFilter.regexContains;
import static edu.hw6.task3.AbstractFilter.regular;
import static edu.hw6.task3.AbstractFilter.smallerThan;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    public void lengthFilterTest(@TempDir Path tempDir) throws IOException {
        var file1 = tempDir.resolve("file1");
        Files.createFile(file1);
        var file2 = tempDir.resolve("file2");
        Files.createFile(file2);
        var file3 = tempDir.resolve("file3");
        Files.createFile(file3);

        try (RandomAccessFile raf1 = new RandomAccessFile(file1.toFile(), "rw");
            RandomAccessFile raf2 = new RandomAccessFile(file2.toFile(), "rw");
             RandomAccessFile raf3 = new RandomAccessFile(file3.toFile(), "rw"))
        {
            raf1.setLength(10);
            raf2.setLength(100);
            raf3.setLength(1000);
        }

        AbstractFilter filter = readable()
            .and(regular())
            .and(largerThan(10))
            .and(smallerThan(1000));

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(tempDir, filter)) {
            var it = ds.iterator();
            assertThat(it.next().getFileName().toString())
                .isEqualTo("file2");
            assertFalse(it.hasNext());
        }
    }

    @Test
    public void globMatchesFilterTest(@TempDir Path tempDir) throws IOException {
        var file1 = tempDir.resolve("file1.txt");
        Files.createFile(file1);
        var file2 = tempDir.resolve("file2.png");
        Files.createFile(file2);
        var file3 = tempDir.resolve("file3.jpg");
        Files.createFile(file3);

        AbstractFilter filter = readable()
            .and(regular())
            .and(globMatches("*png"));

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(tempDir, filter)) {
            var it = ds.iterator();
            assertTrue(it.hasNext());
            assertThat(it.next().getFileName().toString())
                .isEqualTo("file2.png");
            assertFalse(it.hasNext());
        }
    }

    @Test
    public void regexContainsFilterTest(@TempDir Path tempDir) throws IOException {
        var file1 = tempDir.resolve("file1.txt");
        Files.createFile(file1);
        var file2 = tempDir.resolve("file2.png");
        Files.createFile(file2);
        var file3 = tempDir.resolve("file3.jpg");
        Files.createFile(file3);

        AbstractFilter filter = regular()
            .and(regexContains("[12]"));

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(tempDir, filter)) {
            var it = ds.iterator();
            assertTrue(it.hasNext());
            it.next();
            assertTrue(it.hasNext());
            it.next();
            assertFalse(it.hasNext());
        }
    }

    @Test
    public void multipleFiltersTest(@TempDir Path tempDir) throws IOException {
        var file1 = tempDir.resolve("file1.txt");
        Files.createFile(file1);
        var file2 = tempDir.resolve("file2.png");
        Files.createFile(file2);
        var file3 = tempDir.resolve("file3.jpg");
        Files.createFile(file3);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file2.toFile()))) {
            writer.write("Hello, world!");
        }

        AbstractFilter filter = regular()
            .and(magicNumber((byte) 72))
            .and(globMatches("*.png"))
            .and(regexContains("[23]"));

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(tempDir, filter)) {
            var it = ds.iterator();
            assertTrue(it.hasNext());
            assertThat(it.next().getFileName().toString())
                .isEqualTo("file2.png");
            assertFalse(it.hasNext());
        }
    }
}
