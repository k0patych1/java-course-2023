package edu.project4.services;

import edu.project4.entities.FractalFlame;
import edu.project4.models.ImageFormat;
import edu.project4.models.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class ImageUtils {

    private static final int RED_SHIFT = 16;
    private static final int GREEN_SHIFT = 8;

    private ImageUtils() {}

    public static void save(FractalFlame fractalFlame, String filename, ImageFormat format) throws IOException {
        int width = fractalFlame.width();
        int height = fractalFlame.height();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Pixel[][] data = fractalFlame.data();
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                Pixel pixel = data[i][j];
                int r = pixel.r();
                int g = pixel.g();
                int b = pixel.b();
                int rgb = (r << RED_SHIFT) | (g << GREEN_SHIFT) | b;

                image.setRGB(i, j, rgb);
            }
        }

        File output = new File(filename + "." + format.toString().toLowerCase());
        ImageIO.write(image, format.toString(), output);
    }
}
