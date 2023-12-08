package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class PredicateFileFilter extends RecursiveTask<List<File>> {
    private final Predicate predicate;

    private final File directory;

    public PredicateFileFilter(File directory, Predicate predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        List<PredicateFileFilter> subTasks = new ArrayList<>();
        List<File> filesWithRequiredPredicate = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files == null) {
            return new ArrayList<>();
        }

        for (File file : files) {
            if (file.isDirectory()) {
                var subTask = new PredicateFileFilter(file, predicate);
                subTask.fork();
                subTasks.add(subTask);
            } else if (fileExtension(file).equals(predicate.extension()) && file.length() == predicate.size()) {
                filesWithRequiredPredicate.add(file);
            }
        }

        for (PredicateFileFilter subTask : subTasks) {
            filesWithRequiredPredicate.addAll(subTask.join());
        }

        return filesWithRequiredPredicate;
    }

    private static String fileExtension(File file) {
        String name = file.getName();
        int lastDotIndex = name.lastIndexOf('.');

        if (lastDotIndex != -1) {
            return name.substring(lastDotIndex + 1);
        }

        return "";
    }
}
