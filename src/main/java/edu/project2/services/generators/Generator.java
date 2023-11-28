package edu.project2.services.generators;

import edu.project2.entities.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
