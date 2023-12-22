package edu.project4.services.transformations.nonLinear;

import edu.project4.models.Point;

public class PolarTransformation implements NonLinearTransformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double newX = Math.atan2(y, x) / Math.PI;
        double newY = Math.hypot(x, y) - 1;

        return new Point(newX, newY);
    }
}
