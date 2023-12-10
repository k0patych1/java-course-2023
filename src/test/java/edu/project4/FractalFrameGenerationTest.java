package edu.project4;

import edu.project4.entities.FractalFlame;
import edu.project4.models.ImageFormat;
import edu.project4.services.ImageUtils;
import edu.project4.services.generators.FractalFlameGenerator;
import edu.project4.services.generators.MultiThreadFractalFlameGenerator;
import edu.project4.services.transformations.AffineTransformation;
import edu.project4.services.transformations.nonLinear.HeartTransformation;
import edu.project4.services.transformations.nonLinear.NonLinearTransformation;
import edu.project4.services.transformations.nonLinear.SphericalTransformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FractalFrameGenerationTest {
    @Test
    public void perfomanceTest(@TempDir Path tempdir) throws ExecutionException, InterruptedException, IOException {
        tempdir = Path.of("/Users/ruslan/dev/java-course-2023/src/test/java/edu/project4/");
        List<AffineTransformation> affineTransformations = new ArrayList<>();
        List<NonLinearTransformation> nonLinearTransformations = new ArrayList<>();
        int numOfAffineTransformations = 3;
        for (int i = 0; i < numOfAffineTransformations; ++i) {
            affineTransformations.add(new AffineTransformation());
        }

        nonLinearTransformations.add(new HeartTransformation());
        nonLinearTransformations.add(new SphericalTransformation());

        int fullHdWidth = 1920;
        int fullHdHeight = 1080;
        int numOfIterations = 10000;
        int numOfPoints = 1000;

        FractalFlameGenerator
            singleThreadGenerator = new MultiThreadFractalFlameGenerator(affineTransformations, nonLinearTransformations,
            fullHdWidth,
            fullHdHeight,
            numOfIterations,
            numOfPoints);

        FractalFlameGenerator
            multiThreadFractalFlameGenerator = new MultiThreadFractalFlameGenerator(affineTransformations, nonLinearTransformations,
            fullHdWidth,
            fullHdHeight,
            numOfIterations,
            numOfPoints);

        long startSingleThreadGenerate = System.nanoTime();

        FractalFlame singleThreadGeneratedImage = singleThreadGenerator.generate();

        long endSingleThreadGenerate = System.nanoTime();

        FractalFlame multiThreadGeneratedImage = multiThreadFractalFlameGenerator.generate();

        long endMultiThreadGenerate = System.nanoTime();

        ImageUtils.save(singleThreadGeneratedImage,
            tempdir.resolve("image1").toString(),
            ImageFormat.PNG);

        ImageUtils.save(multiThreadGeneratedImage,
            tempdir.resolve("image2").toString(),
            ImageFormat.PNG);

        System.out.println(endSingleThreadGenerate - startSingleThreadGenerate);
        System.out.println(endMultiThreadGenerate - endSingleThreadGenerate);

        assertTrue(endSingleThreadGenerate - startSingleThreadGenerate
            > endMultiThreadGenerate - endSingleThreadGenerate);
    }
}
