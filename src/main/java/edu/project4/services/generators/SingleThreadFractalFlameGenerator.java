package edu.project4.services.generators;

import edu.project4.entities.FractalFlame;
import edu.project4.models.Pixel;
import edu.project4.services.Correction;
import edu.project4.services.transformations.AffineTransformation;
import edu.project4.services.transformations.nonLinear.NonLinearTransformation;
import java.util.List;

public class SingleThreadFractalFlameGenerator extends FractalFlameGenerator {
    public SingleThreadFractalFlameGenerator(
        List<AffineTransformation> affineTransformation,
        List<NonLinearTransformation> nonLinearTransformations,
        int width,
        int height,
        int iterations,
        int numOfPoints
    ) {
        super(affineTransformation, nonLinearTransformations, width, height, iterations, numOfPoints);
    }

    public FractalFlame generate() {
        Pixel[][] pixels = initPixels();

        for (int i = 0; i < numOfPoints; ++i) {
            generatePixel(pixels);
        }

        Correction.correct(pixels, width, height);

        return new FractalFlame(pixels, width, height);
    }
}
