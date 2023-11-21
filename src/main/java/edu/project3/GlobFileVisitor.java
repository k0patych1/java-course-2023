package edu.project3;

import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class GlobFileVisitor extends SimpleFileVisitor<Path> {

    private final PathMatcher pathMatcher;
    private final List<Path> matchedFiles = new ArrayList<>();

    public GlobFileVisitor(final String glob) {
        this.pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) {
        if (pathMatcher.matches(path.getFileName())) {
            matchedFiles.add(path);
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getMatchedFiles() {
        return matchedFiles;
    }
}
