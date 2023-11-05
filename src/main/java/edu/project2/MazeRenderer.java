package edu.project2;

import java.util.List;

public class MazeRenderer implements Renderer {
    private static final String WALL_SYMBOL = "█";
    private static final String PASSAGE_SYMBOL = " ";
    private static final String PATH_SYMBOL = "•";

    @Override
    public String render(Maze maze) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < maze.getHeight() + 1; ++row) {
            for (int col = 0; col < maze.getWidth() + 1; ++col) {
                Cell cell = maze.getCell(new Coordinate(row, col));
                String symbol = cell.getType() == Cell.Type.WALL ? WALL_SYMBOL : PASSAGE_SYMBOL;
                sb.append(symbol);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < maze.getHeight() + 1; ++row) {
            for (int col = 0; col < maze.getWidth() + 1; ++col) {
                Coordinate currentCoordinate = new Coordinate(row, col);
                if (path.contains(currentCoordinate)) {
                    sb.append(PATH_SYMBOL);
                } else {
                    Cell cell = maze.getCell(currentCoordinate);
                    String symbol = cell.getType() == Cell.Type.WALL ? WALL_SYMBOL : PASSAGE_SYMBOL;
                    sb.append(symbol);
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
