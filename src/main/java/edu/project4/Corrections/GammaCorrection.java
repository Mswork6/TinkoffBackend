package edu.project4.Corrections;

import edu.project4.FractalImage;
import edu.project4.Pixel;

public class GammaCorrection implements ImageProcessor {

    private static final double GAMMA = 2.3;

    @Override
    public void process(FractalImage image) {
        double maxNormal = calculateMaxNormal(image);

        for (int row = 0; row < image.getWidth(); row++) {
            for (int col = 0; col < image.getHeight(); col++) {
                Pixel pixel = image.getPixel(row, col);
                if (pixel.getHitCounter() > 0) {
                    processPixel(pixel, maxNormal);
                }
            }
        }
    }

    private double calculateMaxNormal(FractalImage image) {
        double maxNormal = 0;

        for (int row = 0; row < image.getWidth(); row++) {
            for (int col = 0; col < image.getHeight(); col++) {
                Pixel pixel = image.getPixel(row, col);
                if (pixel.getHitCounter() > 0) {
                    pixel.setNormalValue(Math.log10(pixel.getHitCounter()));
                    maxNormal = Math.max(maxNormal, pixel.getNormalValue());
                }
            }
        }

        return maxNormal;
    }

    private void processPixel(Pixel pixel, double maxNormal) {
        pixel.setNormalValue(pixel.getNormalValue() / maxNormal);
        pixel.setR((int) (pixel.getR() * Math.pow(pixel.getNormalValue(), (1.0 / GAMMA))));
        pixel.setG((int) (pixel.getG() * Math.pow(pixel.getNormalValue(), (1.0 / GAMMA))));
        pixel.setB((int) (pixel.getB() * Math.pow(pixel.getNormalValue(), (1.0 / GAMMA))));
    }
}
