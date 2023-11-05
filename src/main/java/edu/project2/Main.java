package edu.project2;

public class Main {
    public static void main(String[] args) {
        Generator generator = new RecursiveBacktrackerGenerator();

        Maze maze = generator.generate(10, 10);

        Solver solver = new RecursiveBacktrackerSolver();

        var list = solver.solve(maze, new Coordinate(1, 1), new Coordinate(9, 9));

        Renderer renderer = new MazeRenderer();

        System.out.println(renderer.render(maze));

        System.out.println(renderer.render(maze, list));
    }
}
