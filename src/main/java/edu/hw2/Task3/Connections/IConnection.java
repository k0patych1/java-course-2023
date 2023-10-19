package edu.hw2.Task3.Connections;

public interface IConnection extends AutoCloseable {
    void execute(String command);

    void close();

    String getLoggerInformation();
}
