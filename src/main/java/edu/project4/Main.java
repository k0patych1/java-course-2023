package edu.project4;

import edu.project4.models.ImageFormat;
import edu.project4.services.ImageUtils;
import edu.project4.services.generators.FractalFlameGenerator;
import edu.project4.services.generators.MultiThreadFractalFlameGenerator;
import edu.project4.services.transformations.AffineTransformation;
import edu.project4.services.transformations.nonLinear.HeartTransformation;
import edu.project4.services.transformations.nonLinear.NonLinearTransformation;
import edu.project4.services.transformations.nonLinear.SphericalTransformation;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public final class Main {
    private Main() {}

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Properties properties = new Properties();
        String configFileName = "/Users/ruslan/dev/java-course-2023/src/main/java/edu/project4/config.properties";
        FileInputStream fis = new FileInputStream(configFileName);
        properties.load(fis);
        fis.close();

        List<AffineTransformation> affineTransformations = new ArrayList<>();
        List<NonLinearTransformation> nonLinearTransformations = new ArrayList<>();
        int numOfAffineTransformations = Integer.parseInt(properties.getProperty("numOfAffineTransformations"));
        for (int i = 0; i < numOfAffineTransformations; ++i) {
            affineTransformations.add(new AffineTransformation());
        }

        nonLinearTransformations.add(new HeartTransformation());
        nonLinearTransformations.add(new SphericalTransformation());

        int fullHdWidth = Integer.parseInt(properties.getProperty("fullHdWidth"));
        int fullHdHeight = Integer.parseInt(properties.getProperty("fullHdHeight"));
        int numOfIterations = Integer.parseInt(properties.getProperty("numOfIterations"));
        int numOfPoints = Integer.parseInt(properties.getProperty("numOfPoints"));

        FractalFlameGenerator generator = new MultiThreadFractalFlameGenerator(
            affineTransformations,
            nonLinearTransformations,
            fullHdWidth,
            fullHdHeight,
            numOfIterations,
            numOfPoints);

        ImageUtils.save(
            generator.generate(),
            properties.getProperty("imageSavePath"),
            ImageFormat.valueOf(properties.getProperty("imageFormat")));
    }
}
