package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SearchFilesTask extends RecursiveTask<List<File>> {
    private final File directory;
    private final java.util.function.Predicate<File> predicate;

    public SearchFilesTask(File directory, java.util.function.Predicate<File> predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        File[] files = directory.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }

        List<File> result = new ArrayList<>();

        List<SearchFilesTask> subtasks = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                SearchFilesTask subtask = new SearchFilesTask(file, predicate);
                subtasks.add(subtask);
                subtask.fork();
            } else {
                if (predicate.test(file)) {
                    result.add(file);
                }
            }
        }

        for (SearchFilesTask subtask : subtasks) {
            result.addAll(subtask.join());
        }

        return result;
    }
}
