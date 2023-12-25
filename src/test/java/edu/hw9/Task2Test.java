package edu.hw9;

import edu.hw9.task2.LargeDirectoriesFilter;
import edu.hw9.task2.Predicate;
import edu.hw9.task2.PredicateFileFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    @Test
    public void largeDirectoriesFilterTest(@TempDir Path root) throws IOException {
        Path childDir1 = root.resolve("dir1");
        Path childDir2 = root.resolve("dir2");
        Path childDir3 = root.resolve("dir3");
        Files.createDirectory(childDir1);
        Files.createDirectory(childDir2);
        Files.createDirectory(childDir3);
        Path childChildDir = childDir1.resolve("dir4");
        Files.createDirectory(childChildDir);
        Path goodFile1 = childChildDir.resolve("goodFile1.pages");
        Path goodFile2 = childDir1.resolve("goodFile2.pages");
        Path goodFile3 = childDir2.resolve("goodFile3.pages");
        Path goodFile4 = root.resolve("goodFile4.pages");
        Files.createFile(goodFile1);
        Files.createFile(goodFile2);
        Files.createFile(goodFile3);
        Files.createFile(goodFile4);
        Path badFile1 = childChildDir.resolve("badFile1.txt");
        Path badFile2 = root.resolve("badFile2.png");
        Files.createFile(badFile1);
        Files.createFile(badFile2);

        LargeDirectoriesFilter filter = new LargeDirectoriesFilter(root.toFile(), 1);

        List<File> answer = filter.getAnswer();
        assertThat(answer.size()).isEqualTo(3);
        assertTrue(answer.contains(root.toFile()));
        assertTrue(answer.contains(childDir1.toFile()));
        assertTrue(answer.contains(childChildDir.toFile()));
    }

    @Test
    public void predicateFileFilterTest(@TempDir Path root) throws IOException {
        Path childDir1 = root.resolve("dir1");
        Path childDir2 = root.resolve("dir2");
        Path childDir3 = root.resolve("dir3");
        Files.createDirectory(childDir1);
        Files.createDirectory(childDir2);
        Files.createDirectory(childDir3);
        Path childChildDir = childDir1.resolve("dir4");
        Files.createDirectory(childChildDir);
        Path goodFile1 = childChildDir.resolve("goodFile1.pages");
        Path goodFile2 = childDir1.resolve("goodFile2.pages");
        Path goodFile3 = childDir2.resolve("goodFile3.pages");
        Path goodFile4 = root.resolve("goodFile4.pages");
        Files.createFile(goodFile1);
        Files.createFile(goodFile2);
        Files.createFile(goodFile3);
        Files.createFile(goodFile4);
        Path badFile1 = childChildDir.resolve("badFile1.txt");
        Path badFile2 = root.resolve("badFile2.png");
        Files.createFile(badFile1);
        Files.createFile(badFile2);

        var predicate = new Predicate("pages", 0);
        PredicateFileFilter filter = new PredicateFileFilter(root.toFile(), predicate);
        List<File> answer = filter.getAnswer();
        assertThat(answer.size()).isEqualTo(4);
        assertTrue(answer.contains(goodFile1.toFile()));
        assertTrue(answer.contains(goodFile2.toFile()));
        assertTrue(answer.contains(goodFile3.toFile()));
        assertTrue(answer.contains(goodFile4.toFile()));
    }
}
