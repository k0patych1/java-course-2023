package edu.project2.services.renders;

import edu.project2.entities.Maze;
import edu.project2.models.Coordinate;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
