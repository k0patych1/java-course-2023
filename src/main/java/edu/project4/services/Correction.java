package edu.project4.services;

import edu.project4.models.Pixel;
import static java.lang.Math.log10;

public final class Correction {
    private static final double GAMMA = 1;

    private Correction() {}

    public static void correct(Pixel[][] pixels, int width, int height) {
        double max = 0.0;
        for (int row = 0; row < width; ++row) {
            for (int col = 0; col < height; ++col) {
                if (pixels[row][col].hitCount() != 0) {
                    double normal = log10(pixels[row][col].hitCount());
                    if (normal > max) {
                        max = normal;
                    }
                }
            }
        }

        for (int row = 0; row < width; ++row) {
            for (int col = 0; col < height; ++col) {
                double normal = Math.log10(pixels[row][col].hitCount());
                normal /= max;
                int red = (int) (pixels[row][col].r() * Math.pow(normal, (1.0 / GAMMA)));
                int green = (int) (pixels[row][col].g() * Math.pow(normal, (1.0 / GAMMA)));
                int blue = (int) (pixels[row][col].b() * Math.pow(normal, (1.0 / GAMMA)));
                pixels[row][col] = new Pixel(red, green, blue, pixels[row][col].hitCount());
            }
        }
    }
}
