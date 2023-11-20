package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

public final class Task4 {
    private Task4() {}

    private static final String TEXT = "Programming is learned by writing programs. ― Brian Kernighan";

    public static void compositeWrite(Path path) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(path, CREATE_NEW);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter writer = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(writer)) {
            printWriter.print(TEXT);
        }
    }
}
