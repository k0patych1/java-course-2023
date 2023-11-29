package edu.project2.services.renders;

import edu.project2.entities.Maze;
import edu.project2.models.Cell;
import edu.project2.models.Coordinate;
import java.util.List;

public class MazeRenderer implements Renderer {
    private final String wallSymbol;
    private final String passageSymbol;
    private final String pathSymbol;

    public MazeRenderer() {
        wallSymbol = "🌵";
        passageSymbol = "⬛";
        pathSymbol = "🚶";
    }

    public MazeRenderer(String wallSymbol, String passageSymbol, String pathSymbol) {
        this.wallSymbol = wallSymbol;
        this.passageSymbol = passageSymbol;
        this.pathSymbol = pathSymbol;
    }

    @Override
    public String render(Maze maze) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < maze.getHeight() + 1; ++row) {
            for (int col = 0; col < maze.getWidth() + 1; ++col) {
                Cell cell = maze.getCell(new Coordinate(row, col));
                String symbol = cell.getType() == Cell.Type.WALL ? wallSymbol : passageSymbol;
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
                    sb.append(pathSymbol);
                } else {
                    Cell cell = maze.getCell(currentCoordinate);
                    String symbol = cell.getType() == Cell.Type.WALL ? wallSymbol : passageSymbol;
                    sb.append(symbol);
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
