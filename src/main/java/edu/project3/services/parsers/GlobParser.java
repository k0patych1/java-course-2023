package edu.project3.services.parsers;

import edu.project3.services.GlobFileVisitor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class GlobParser {
    private GlobParser() {}

    public static List<Path> getFiles(String path) throws IOException {
        int firstAsteriskIndex = path.indexOf("*");

        if (firstAsteriskIndex == -1) {
            ArrayList<Path> files = new ArrayList<>();
            files.add(Path.of(path));

            return files;
        }

        int lastSlashIndex = path.lastIndexOf("/", firstAsteriskIndex);

        String startDir = path.substring(0, lastSlashIndex);

        var docFileVisitor = new GlobFileVisitor(path);
        Files.walkFileTree(Path.of(startDir), docFileVisitor);

        return docFileVisitor.getMatchedFiles();
    }
}
