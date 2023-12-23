package edu.project4.services.generators;

import edu.project4.entities.FractalFlame;
import edu.project4.models.Pixel;
import edu.project4.models.Point;
import edu.project4.services.transformations.AffineTransformation;
import edu.project4.services.transformations.nonLinear.NonLinearTransformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public abstract class FractalFlameGenerator {
    protected static final double X_MAX = 2;

    protected static final double X_MIN = -2;

    protected static final double Y_MAX = 2.3;

    protected static final double Y_MIN = -2.3;
    private static final int NUM_OF_START_ITERATIONS = 20;

    protected final List<AffineTransformation> affineTransformations;

    protected final List<NonLinearTransformation> nonLinearTransformations;

    protected final Random random;

    protected final int width;

    protected final int height;

    protected final int iterations;

    protected final int numOfPoints;

    protected NonLinearTransformation getRandomNonLinearTransformation() {
        int randomIndex = random.nextInt(nonLinearTransformations.size());
        return nonLinearTransformations.get(randomIndex);
    }

    protected AffineTransformation getRandomAffineTransformation() {
        int randomIndex = random.nextInt(affineTransformations.size());
        return affineTransformations.get(randomIndex);
    }

    protected Point getStartPoint() {
        double startX = random.nextDouble(X_MIN, X_MAX);
        double startY = random.nextDouble(Y_MIN, Y_MAX);

        return new Point(startX, startY);
    }

    public FractalFlameGenerator(
        List<AffineTransformation> affineTransformation,
        List<NonLinearTransformation> nonLinearTransformations, int width, int height, int iterations, int numOfPoints
    ) {
        random = new Random();
        this.iterations = iterations;
        this.affineTransformations = affineTransformation;
        this.nonLinearTransformations = nonLinearTransformations;
        this.width = width;
        this.height = height;
        this.numOfPoints = numOfPoints;
    }

    protected boolean isCorrectPoint(Point point) {
        return point.x() <= X_MAX && point.x() >= X_MIN
            && point.y() <= Y_MAX && point.y() >= Y_MIN;
    }

    protected Pixel[][] initPixels() {
        Pixel[][] pixels = new Pixel[width][height];

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                pixels[i][j] = new Pixel(0, 0, 0, 0);
            }
        }

        return pixels;
    }

    public abstract FractalFlame generate() throws ExecutionException, InterruptedException;

    protected void generatePixel(Pixel[][] pixels) {
        Point start = getStartPoint();
        for (int step = -NUM_OF_START_ITERATIONS; step < iterations; ++step) {
            AffineTransformation affineTransformation = getRandomAffineTransformation();
            NonLinearTransformation nonLinearTransformation = getRandomNonLinearTransformation();
            start = affineTransformation.andThen(nonLinearTransformation).apply(start);

            if (step >= 0 && isCorrectPoint(start)) {
                int y = height - (int) (((Y_MAX - start.y()) / (Y_MAX - Y_MIN)) * height);
                int x = width - (int) (((X_MAX - start.x()) / (X_MAX - X_MIN)) * width);

                if (x < width && y < height) {
                    int red;
                    int green;
                    int blue;

                    if (pixels[x][y].hitCount() == 0) {
                        red = affineTransformation.getRed();
                        green = affineTransformation.getGreen();
                        blue = affineTransformation.getBlue();
                    } else {
                        red = (pixels[x][y].r() + affineTransformation.getRed()) / 2;
                        green = (pixels[x][y].g() + affineTransformation.getGreen()) / 2;
                        blue = (pixels[x][y].b() + affineTransformation.getBlue()) / 2;
                    }

                    pixels[x][y] = new Pixel(red, green, blue, pixels[x][y].hitCount() + 1);
                }
            }
        }
    }
}
