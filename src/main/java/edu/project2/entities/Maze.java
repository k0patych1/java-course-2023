package edu.project2.entities;

import edu.project2.models.Cell;
import edu.project2.models.Coordinate;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Cell[height + 1][width + 1];
        initializeGrid();
    }

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell getCell(Coordinate coordinate) {
        return grid[coordinate.row()][coordinate.col()];
    }

    public void setCell(Coordinate coordinate, Cell.Type type) {
        grid[coordinate.row()][coordinate.col()].setType(type);
    }

    private void initializeGrid() {
        for (int row = 0; row < height + 1; ++row) {
            for (int col = 0; col < width + 1; ++col) {
                if ((row % 2 != 0 && col % 2 != 0) && (row < height && col < width)) {
                    grid[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
                } else {
                    grid[row][col] = new Cell(row, col, Cell.Type.WALL);
                }
            }
        }
    }
}
