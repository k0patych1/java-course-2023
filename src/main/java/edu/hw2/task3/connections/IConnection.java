package edu.hw2.task3.connections;

public interface IConnection extends AutoCloseable {
    void execute(String command);

    @Override
    void close();
}
