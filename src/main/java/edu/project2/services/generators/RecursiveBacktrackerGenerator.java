package edu.project2.services.generators;

import edu.project2.entities.Maze;
import edu.project2.entities.NeighboursHandler;
import edu.project2.models.Cell;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class RecursiveBacktrackerGenerator implements Generator {
    private static final int START_ROW = 1;
    private static final int START_COL = 1;
    public static final int DISTANCE_BETWEEN_PASSAGES = 2;
    private final NeighboursHandler neighboursHandler;
    private final Cell startCell = new Cell(START_ROW, START_COL, Cell.Type.PASSAGE);

    public RecursiveBacktrackerGenerator() {
        neighboursHandler = new NeighboursHandler();
    }

    public RecursiveBacktrackerGenerator(Random random) {
        neighboursHandler = new NeighboursHandler(random);
    }

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        Stack<Cell> stack = new Stack<>();
        Cell currentCell = startCell;
        Set<Cell> visited = new HashSet<>();
        visited.add(currentCell);
        stack.push(currentCell);

        while (!stack.isEmpty()) {
            List<Cell> neighbours = neighboursHandler.getNeighboursInDistance(currentCell, maze,
                DISTANCE_BETWEEN_PASSAGES
            );

            neighbours.removeAll(visited);

            if (neighbours.isEmpty()) {
                currentCell = stack.pop();
                continue;
            }

            Cell randomNeighbor = neighboursHandler.getRandomNeighbour(neighbours);

            neighboursHandler.removeWallBetweenNeighbours(maze, currentCell, randomNeighbor);

            currentCell = randomNeighbor;
            visited.add(randomNeighbor);
            stack.push(randomNeighbor);
        }

        return maze;
    }
}
