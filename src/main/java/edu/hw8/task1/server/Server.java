package edu.hw8.task1.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private static final int PORT = 1357;

    private static final int MAX_SIMULTANEOUS_CONNECTIONS = 1000;

    private static final int BUFFER_CAPACITY = 1024;

    private static String getAnswer(String question) {
        return switch (question) {
            case "личности" -> "Не переходи на личности там, где их нет";
            case "оскорбления" ->
                    "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами";
            case "глупый" ->
                    "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма";
            case "интеллект" -> "Чем ниже интеллект, тем громче оскорбления";
            default -> throw new IllegalStateException("Unexpected value: " + question);
        };
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket)
        throws IOException {

        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    private static void sendAnswer(SelectionKey key)
        throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);

        SocketChannel client = (SocketChannel) key.channel();
        int r = client.read(buffer);
        if (r == -1) {
            client.close();
        } else {
            buffer.flip();
            String question = new String(buffer.array()).trim();
            String answer = getAnswer(question);
            byte[] expectedBytes = answer.getBytes(StandardCharsets.UTF_8);
            buffer = ByteBuffer.wrap(expectedBytes);
            client.write(buffer);
            buffer.flip();
            buffer.clear();
        }

        client.close();
    }

    @Override
    public void run() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(MAX_SIMULTANEOUS_CONNECTIONS);
             Selector selector = Selector.open();
             ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress("localhost", PORT));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {

                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        register(selector, serverSocket);
                    }

                    if (key.isReadable()) {
                        executorService.submit(() -> {
                            try {
                                sendAnswer(key);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
