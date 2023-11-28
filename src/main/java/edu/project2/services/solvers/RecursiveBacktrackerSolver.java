package edu.project2.services.solvers;

import edu.project2.entities.Maze;
import edu.project2.entities.NeighboursHandler;
import edu.project2.models.Cell;
import edu.project2.models.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class RecursiveBacktrackerSolver implements Solver {
    private final NeighboursHandler neighboursHandler = new NeighboursHandler();
    public static final String NO_PATH_EXCEPTION = "There is no path";
    public static final String WALL_START_EXCEPTION = "You have selected a wall as the starting coordinate";

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (maze.getCell(start).getType() == Cell.Type.WALL) {
            throw new IllegalArgumentException(WALL_START_EXCEPTION);
        }

        List<Coordinate> path = new ArrayList<>();
        Stack<Cell> stack = new Stack<>();
        Set<Cell> visited = new HashSet<>();
        Cell currentCell = maze.getCell(start);
        Map<Cell, Cell> parents = new HashMap<>();

        stack.add(currentCell);

        while (!stack.isEmpty()) {
            currentCell = stack.pop();

            if (visited.contains(currentCell)) {
                continue;
            }

            visited.add(currentCell);

            if (currentCell.getCol() == end.col() && currentCell.getRow() == end.row()) {
                while (currentCell != null) {
                    path.add(new Coordinate(currentCell.getRow(), currentCell.getCol()));
                    currentCell = parents.get(currentCell);
                }

                return path;
            }

            List<Cell> neighbours = neighboursHandler.getNeighboursInDistance(currentCell, maze, 1);
            for (Cell neighbour : neighbours) {
                if (visited.contains(neighbour) || neighbour.getType() == Cell.Type.WALL) {
                    continue;
                }

                parents.put(neighbour, currentCell);
                stack.push(neighbour);
            }
        }

        throw new RuntimeException(NO_PATH_EXCEPTION);
    }
}
