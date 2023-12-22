package edu.project4.services.transformations.nonLinear;

import edu.project4.models.Point;

public class SinTransformation implements NonLinearTransformation {
    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
