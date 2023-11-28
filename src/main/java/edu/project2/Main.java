package edu.project2;

import edu.project2.entities.Maze;
import edu.project2.models.Coordinate;
import edu.project2.services.generators.RecursiveBacktrackerGenerator;
import edu.project2.services.renders.MazeRenderer;
import edu.project2.services.renders.Renderer;
import edu.project2.services.solvers.RecursiveBacktrackerSolver;
import edu.project2.services.solvers.Solver;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the height and width of the maze!");

        Scanner scanner = new Scanner(System.in);
        int height;
        int width;

        if (scanner.hasNextInt()) {
            height = scanner.nextInt();
        } else {
            throw new IllegalArgumentException("Invalid height provided");
        }

        if (scanner.hasNextInt()) {
            width = scanner.nextInt();
        } else {
            throw new IllegalArgumentException("Invalid width provided");
        }

        var generator = new RecursiveBacktrackerGenerator();
        Maze maze = generator.generate(height, width);
        Renderer renderer = new MazeRenderer();
        System.out.println(renderer.render(maze));

        int startX;
        int startY;
        System.out.println("Enter starting coordinate of path (x y)");
        if (scanner.hasNextInt()) {
            startX = scanner.nextInt();
        } else {
            throw new IllegalArgumentException("Invalid start x coordinate provided");
        }

        if (scanner.hasNext()) {
            startY = scanner.nextInt();
        }
        else {
            throw new IllegalArgumentException("Invalid start y coordinate provided");
        }

        var startCoordinate = new Coordinate(startX, startY);

        int endX;
        int endY;
        System.out.println("Enter ending coordinate of path! (x y)");
        if (scanner.hasNextInt()) {
            endX = scanner.nextInt();
        } else {
            throw new IllegalArgumentException("Invalid end x coordinate provided");
        }

        if (scanner.hasNext()) {
            endY = scanner.nextInt();
        }
        else {
            throw new IllegalArgumentException("Invalid end y coordinate provided");
        }

        var endCoordinate = new Coordinate(endX, endY);

        Solver solver = new RecursiveBacktrackerSolver();
        var path = solver.solve(maze, startCoordinate, endCoordinate);
        System.out.println(renderer.render(maze, path));
    }
}
