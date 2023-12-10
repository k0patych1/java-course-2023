package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class LargeDirectoriesFilter extends RecursiveTask<List<File>> {
    private final File directory;

    private final int requiredNumberOfFiles;

    private int totalNumOfFiles = 0;

    public LargeDirectoriesFilter(File root, int requiredNumberFiles) {
        this.directory = root;
        requiredNumberOfFiles = requiredNumberFiles;
    }

    @Override
    protected List<File> compute() {
        List<File> directoriesWithMoreThanRequired = new ArrayList<>();
        List<LargeDirectoriesFilter> subTasks = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files == null) {
            return new ArrayList<>();
        }

        for (File file : files) {
            if (file.isDirectory()) {
                var subTask = new LargeDirectoriesFilter(file, requiredNumberOfFiles);
                subTask.fork();
                subTasks.add(subTask);
            } else {
                ++totalNumOfFiles;
            }
        }

        for (LargeDirectoriesFilter subTask : subTasks) {
            directoriesWithMoreThanRequired.addAll(subTask.join());
            totalNumOfFiles += subTask.totalNumOfFiles;
        }

        if (totalNumOfFiles > requiredNumberOfFiles) {
            directoriesWithMoreThanRequired.add(directory);
        }

        return directoriesWithMoreThanRequired;
    }

    public List<File> getAnswer() {
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(this);
        }
    }
}
