package edu.hw9.task3;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class MultiThreadDfs extends RecursiveAction {
    private final List<List<Integer>> graph;

    private final int currentVertex;

    private final boolean[] used;

    public MultiThreadDfs(List<List<Integer>> graph, int startVertex) {
        this.graph = graph;
        this.currentVertex = startVertex;
        used = new boolean[graph.size()];
    }

    @Override
    protected void compute() {
        if (used[currentVertex]) {
            return;
        }

        used[currentVertex] = true;

        for (Integer vertex : graph.get(currentVertex)) {
            if (!used[vertex]) {
                MultiThreadDfs multiThreadDfs = new MultiThreadDfs(graph, vertex);
                multiThreadDfs.fork();
            }
        }
    }

    public boolean[] getUsed() {
        return used;
    }
}
