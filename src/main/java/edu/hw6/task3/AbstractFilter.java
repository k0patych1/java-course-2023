package edu.hw6.task3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter other) {
        return path -> this.accept(path) && other.accept(path);
    }

    static AbstractFilter regular() {
        return Files::isRegularFile;
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static AbstractFilter executable() {
        return Files::isExecutable;
    }

    static AbstractFilter largerThan(long size) {
        return path -> Files.size(path) > size;
    }

    static AbstractFilter smallerThan(long size) {
        return path -> Files.size(path) < size;
    }

    static AbstractFilter globMatches(String glob) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return path -> pathMatcher.matches(path.getFileName());
    }

    static AbstractFilter regexContains(String regex) {
        Pattern pattern = Pattern.compile(regex);
        return path -> Files.isRegularFile(path) && pattern.matcher(path.getFileName().toString()).find();
    }

    static AbstractFilter magicNumber(byte... magicBytes) {
        return path -> {
            try (FileChannel fileChannel = FileChannel.open(path)) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(magicBytes.length);
                int bytesRead = fileChannel.read(byteBuffer);
                return bytesRead == magicBytes.length && Arrays.equals(byteBuffer.array(), magicBytes);
            } catch (IOException e) {
                return false;
            }
        };
    }
}
