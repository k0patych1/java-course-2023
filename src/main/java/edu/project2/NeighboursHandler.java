package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeighboursHandler {
    private final Random random;

    public NeighboursHandler() {
        random = new Random();
    }

    public NeighboursHandler(Random random) {
        this.random = random;
    }

    public Cell getRandomNeighbour(List<Cell> neighbours) {
        int randomIndex = random.nextInt(neighbours.size());
        return neighbours.get(randomIndex);
    }

    public List<Cell> getNeighboursInDistance(Cell currentCell, Maze maze, int inDistance) {
        int row = currentCell.getRow();
        int col = currentCell.getCol();
        List<Cell> neighbors = new ArrayList<>();
        int[][] offsets = {{0, -inDistance}, {0, inDistance}, {-inDistance, 0}, {inDistance, 0}};
        for (int[] offset : offsets) {
            int neighborRow = row + offset[0];
            int neighborCol = col + offset[1];
            var neighbor = new Coordinate(neighborRow, neighborCol);

            if (isValidCoordinate(neighbor, maze)) {
                neighbors.add(maze.getCell(neighbor));
            }
        }

        return neighbors;
    }

    private boolean isValidCoordinate(Coordinate coordinate, Maze maze) {
        int row = coordinate.row();
        int col = coordinate.col();
        return row >= 0 && row < maze.getHeight() && col >= 0 && col < maze.getWidth();
    }

    public void removeWallBetweenNeighbours(Maze maze, Cell cell1, Cell cell2) {
        int xDiff = cell2.getRow() - cell1.getRow();
        int yDiff = cell2.getCol() - cell1.getCol();

        int addX = (xDiff != 0) ? (xDiff / Math.abs(xDiff)) : 0;
        int addY = (yDiff != 0) ? (yDiff / Math.abs(yDiff)) : 0;

        Coordinate wallCoordinate = new Coordinate(cell1.getCol() + addY, cell1.getRow() + addX);

        maze.setCell(wallCoordinate, Cell.Type.PASSAGE);
    }
}
