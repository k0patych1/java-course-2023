package edu.project2;

import edu.project2.entities.Maze;
import edu.project2.models.Cell;
import edu.project2.models.Coordinate;
import edu.project2.services.generators.Generator;
import edu.project2.services.generators.RecursiveBacktrackerGenerator;
import edu.project2.services.renders.MazeRenderer;
import edu.project2.services.renders.Renderer;
import edu.project2.services.solvers.RecursiveBacktrackerSolver;
import edu.project2.services.solvers.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MazeTests {
    private final int[][] cells = { {1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,1,0,1,0,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,1,0,1,1,1,0,1},
        {1,0,0,0,1,1,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,0,0,1},
        {1,0,1,0,1,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,1,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private Maze getMaze() {
        Cell[][] maze = new Cell[10][13];

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 13; col++) {
                if (cells[row][col] == 1) {
                    maze[row][col] = new Cell(row, col, Cell.Type.WALL);
                } else {
                    maze[row][col] = new Cell(row, col, Cell.Type.PASSAGE);
                }
            }
        }

        return new Maze(9, 12, maze);
    }

    @Test
    public void startFromWallTest() {
        Solver solver = new RecursiveBacktrackerSolver();

        assertThrows(IllegalArgumentException.class, () ->
            solver.solve(getMaze(),
                new Coordinate(0, 0),
                new Coordinate(1, 1)));
    }

    @Test
    public void findPathNoExistTest() {
        Solver solver = new RecursiveBacktrackerSolver();

        assertThrows(RuntimeException.class, () ->
            solver.solve(getMaze(),
                new Coordinate(1, 1),
                new Coordinate(10, 10)));
    }

    @Test
    public void findPathTest() {
        Solver solver = new RecursiveBacktrackerSolver();

        List<Coordinate> resultPath = solver.solve(getMaze(),
            new Coordinate(1, 1),
            new Coordinate(8, 2));

        List<Coordinate> expectedPath = new ArrayList<>();
        expectedPath.add(new Coordinate(8, 2));
        expectedPath.add(new Coordinate(8, 1));
        expectedPath.add(new Coordinate(7, 1));
        expectedPath.add(new Coordinate(6, 1));
        expectedPath.add(new Coordinate(5, 1));
        expectedPath.add(new Coordinate(4, 1));
        expectedPath.add(new Coordinate(3, 1));
        expectedPath.add(new Coordinate(2, 1));
        expectedPath.add(new Coordinate(1, 1));


        assertThat(resultPath).isEqualTo(expectedPath);
    }

    @Test
    public void renderMazeTest() {
        Renderer mazeRenderer = new MazeRenderer();
        Solver solver = new RecursiveBacktrackerSolver();

        String output = mazeRenderer.render(getMaze(),
            solver.solve(getMaze(),
                new Coordinate(1, 1), new Coordinate(8, 11)));


        String expectedOutput = new StringBuilder()
            .append("🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵\n")
            .append("🌵🚶🌵⬛🌵⬛🌵🚶🚶🚶🚶🚶🌵\n")
            .append("🌵🚶🌵⬛⬛⬛🌵🚶🌵🌵🌵🚶🌵\n")
            .append("🌵🚶⬛⬛🌵🌵🌵🚶⬛⬛⬛🚶🌵\n")
            .append("🌵🚶🌵🚶🚶🚶🚶🚶🌵🌵🌵🚶🌵\n")
            .append("🌵🚶🌵🚶🌵🌵🌵⬛🌵⬛⬛🚶🌵\n")
            .append("🌵🚶🌵🚶🌵⬛⬛⬛🌵🌵🌵🚶🌵\n")
            .append("🌵🚶🌵🚶🌵🌵🌵⬛🌵⬛🌵🚶🌵\n")
            .append("🌵🚶🚶🚶⬛⬛⬛⬛⬛⬛🌵🚶🌵\n")
            .append("🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵\n")
            .toString();

        assertThat(output).isEqualTo(expectedOutput);
    }

    public class MyRandom extends Random {
        @Override
        public int nextInt(int bound) {
            return bound - 1;
        }
    }

    @Test
    public void generateMazeTest() {
        Generator mazeGenerator = new RecursiveBacktrackerGenerator(new MyRandom());
        Maze maze = mazeGenerator.generate(4, 20);

        Solver solver = new RecursiveBacktrackerSolver();
        var path = solver.solve(maze, new Coordinate(1, 1), new Coordinate(3, 19));

        Renderer mazeRenderer = new MazeRenderer();
        String output = mazeRenderer.render(maze, path);

        String expectedOutput = new StringBuilder()
            .append("🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵\n")
            .append("🌵🚶🚶🚶🌵🚶🚶🚶🌵🚶🚶🚶🌵🚶🚶🚶🌵🚶🚶🚶🌵\n")
            .append("🌵⬛🌵🚶🌵🚶🌵🚶🌵🚶🌵🚶🌵🚶🌵🚶🌵🚶🌵🚶🌵\n")
            .append("🌵⬛🌵🚶🚶🚶🌵🚶🚶🚶🌵🚶🚶🚶🌵🚶🚶🚶🌵🚶🌵\n")
            .append("🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵🌵\n")
            .toString();

        assertThat(output).isEqualTo(expectedOutput);
    }
}
