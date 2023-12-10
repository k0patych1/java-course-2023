package edu.hw9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import edu.hw9.task3.MultiThreadDfs;
import edu.hw9.task3.OneThreadDfs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    public List<List<Integer>> generateFullGraph(int numOfVertices) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numOfVertices; ++i) {
            List<Integer> vertices = new ArrayList<>();
            for (int j = 0; j < numOfVertices; ++j) {
                if (i != j) {
                    vertices.add(j);
                }
            }
            graph.add(vertices);
        }

        return graph;
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 10, 100, 1000})
    public void singleThreadDfsTest(int numOfVertices) {
        List<List<Integer>> graph = generateFullGraph(numOfVertices);
        OneThreadDfs oneThreadDfs = new OneThreadDfs(graph);

        int startVertex = new Random().nextInt(numOfVertices);

        oneThreadDfs.run(startVertex);

        boolean[] used = oneThreadDfs.getUsed();

        for (boolean isUsed : used) {
            assertTrue(isUsed);
        }
    }
}
