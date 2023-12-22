package edu.project4.services.transformations;

import edu.project4.models.Point;
import java.util.Random;

public class AffineTransformation implements Transformation {
    private static final double MAX_COEFFICIENTS_VALUE = 1.5;

    private static final double MIN_COEFFICIENTS_VALUE = -1.5;
    private static final int MAX_INT_COLOR = 256;

    private double getCoefficientValue() {
        return random.nextDouble(MIN_COEFFICIENTS_VALUE, MAX_COEFFICIENTS_VALUE);
    }

    private double a;

    private double b;

    private double c;

    private double d;

    private double e;

    private double f;

    private final int red;

    private final int green;

    private final int blue;

    private final Random random;

    public AffineTransformation() {
        random = new Random();

        do {
            a = getCoefficientValue();
            b = getCoefficientValue();
            c = getCoefficientValue();
            d = getCoefficientValue();
            e = getCoefficientValue();
            f = getCoefficientValue();
        } while (a * a + d * d >= 1
            || b * b + e * e >= 1
            || a * a + b * b + d * d + e * e >= 1 + (a * e - b * d) * (a * e - b * d));

        red = random.nextInt(MAX_INT_COLOR);
        green = random.nextInt(MAX_INT_COLOR);
        blue = random.nextInt(MAX_INT_COLOR);
    }

    @Override
    public Point apply(Point point) {

        double newX = point.x() * a + point.y() * b + c;
        double newY = point.x() * d + point.y() * e + f;

        return new Point(newX, newY);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
