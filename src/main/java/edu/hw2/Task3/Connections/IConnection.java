package edu.hw2.Task3.Connections;

public interface IConnection extends AutoCloseable {
    void execute(String command);

    @Override
    void close();

    String getLoggerInformation();
}
