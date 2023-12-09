package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class CountFilesTask extends RecursiveTask<Integer> {
    private final File directory;
    private final int threshold;

    public CountFilesTask(File directory, int threshold) {
        this.directory = directory;
        this.threshold = threshold;
    }

    @Override
    protected Integer compute() {
        File[] files = directory.listFiles();
        if (files == null) {
            return 0;
        }

        int count = 0;

        List<CountFilesTask> subtasks = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                CountFilesTask subtask = new CountFilesTask(file, threshold);
                subtasks.add(subtask);
                subtask.fork();
            } else {
                count++;
            }
        }

        for (CountFilesTask subtask : subtasks) {
            count += subtask.join();
        }

        return count > threshold ? 1 : 0;
    }
}
