package edu.hw6;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class Task2 {

    public static final String COPY_LITERAL = " — копия";

    private Task2() {}

    public static Path cloneFile(Path path) throws IOException {
        if (!path.toFile().exists()) {
            throw new FileNotFoundException("Couldn't find the file to copy");
        }

        String fileName = path.getFileName().toString();
        String extension = "";

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            extension = fileName.substring(dotIndex);
            fileName = fileName.substring(0, dotIndex);
        }

        String newFileName = fileName + COPY_LITERAL;
        int copyNumber = 2;
        Path newPath = path.resolveSibling(newFileName + extension);

        while (Files.exists(newPath)) {
            newFileName = fileName + COPY_LITERAL + " (" + copyNumber + ")";
            newPath = path.resolveSibling(newFileName + extension);
            ++copyNumber;
        }

        Files.copy(path, newPath, StandardCopyOption.COPY_ATTRIBUTES);

        return newPath;
    }
}
