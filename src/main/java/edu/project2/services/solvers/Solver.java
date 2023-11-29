package edu.project2.services.solvers;

import edu.project2.entities.Maze;
import edu.project2.models.Coordinate;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
