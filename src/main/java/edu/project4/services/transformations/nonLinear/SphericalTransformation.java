package edu.project4.services.transformations.nonLinear;

import edu.project4.models.Point;

public class SphericalTransformation implements NonLinearTransformation {
    @Override
    public Point apply(Point point) {
        double sumSquares = point.x() * point.x() + point.y() * point.y();
        return new Point(point.x() / sumSquares, point.y() / sumSquares);
    }
}
