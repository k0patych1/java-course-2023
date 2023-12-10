package edu.project4.services.generators;

import edu.project4.entities.FractalFlame;
import edu.project4.models.Pixel;
import edu.project4.services.Correction;
import edu.project4.services.transformations.AffineTransformation;
import edu.project4.services.transformations.nonLinear.NonLinearTransformation;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class MultiThreadFractalFlameGenerator extends FractalFlameGenerator {
    public MultiThreadFractalFlameGenerator(
        List<AffineTransformation> affineTransformation,
        List<NonLinearTransformation> nonLinearTransformations,
        int width,
        int height,
        int iterations,
        int numOfPoints
    ) {
        super(affineTransformation, nonLinearTransformations, width, height, iterations, numOfPoints);
    }

    @Override
    public FractalFlame generate() throws RejectedExecutionException {
        Pixel[][] pixels = initPixels();

        CountDownLatch latch = new CountDownLatch(numOfPoints);
        int numOfThreads = Runtime.getRuntime().availableProcessors();
        try (ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads)) {
            for (int i = 0; i < numOfPoints; ++i) {
                executorService.submit(() -> {
                    generatePixel(pixels);
                    latch.countDown();
                });
            }
        }

        latch.countDown();

        Correction.correct(pixels, width, height);

        return new FractalFlame(pixels, width, height);
    }
}
