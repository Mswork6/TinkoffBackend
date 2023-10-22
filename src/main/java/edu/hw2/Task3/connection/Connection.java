package edu.hw2.Task3.connection;

public interface Connection extends AutoCloseable {
    void execute(String command);
}
