package edu.project4.services.transformations.nonLinear;

import edu.project4.models.Point;

public class HeartTransformation implements NonLinearTransformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double intermediate = Math.hypot(x, y) * Math.atan2(y, x);
        double newX = Math.hypot(x, y) * Math.sin(intermediate);
        double newY = -Math.hypot(x, y) * Math.cos(intermediate);

        return new Point(newX, newY);
    }
}
