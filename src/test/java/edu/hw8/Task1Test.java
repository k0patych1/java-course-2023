package edu.hw8;

import edu.hw8.task1.server.Server;
import edu.hw8.task1.client.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    public void sendMessageTest() throws IOException, InterruptedException, ExecutionException {
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);

        executorService.submit(new Server());

        Thread.sleep(2000);

        List<CompletableFuture<String>> futures = new ArrayList<>();
        List<Client> clients = new ArrayList<>();


        for (int i = 0; i < numOfThreads; ++i) {
            Client client = new Client();
            clients.add(client);
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return client.sendMessage("интеллект");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, executorService);
            futures.add(future);
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allFutures.join();
        Server.close();

        for (CompletableFuture<String> future : futures) {
            assertThat(future.get()).isEqualTo("Чем ниже интеллект, тем громче оскорбления");
        }

        for (Client client : clients) {
            client.stop();
        }
    }
}
