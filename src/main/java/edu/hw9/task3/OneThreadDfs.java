package edu.hw9.task3;

import java.util.List;

public class OneThreadDfs {
    private final List<List<Integer>> graph;

    private final boolean[] used;

    public OneThreadDfs(List<List<Integer>> graph) {
        this.graph = graph;
        used = new boolean[graph.size()];
    }

    public void run(int currentVertex) {
        used[currentVertex] = true;

        for (Integer vertex : graph.get(currentVertex)) {
            if (!used[vertex]) {
                run(vertex);
            }
        }
    }

    public boolean[] getUsed() {
        return used;
    }
}
