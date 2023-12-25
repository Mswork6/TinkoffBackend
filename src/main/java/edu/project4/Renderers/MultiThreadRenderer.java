package edu.project4.Renderers;

import edu.project4.AffineCoefficients;
import edu.project4.FractalImage;
import edu.project4.Pixel;
import edu.project4.Point;
import edu.project4.Rect;
import edu.project4.Transformations.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static edu.project4.Renderers.RendererProcessor.getCoefficients;
import static edu.project4.Renderers.RendererProcessor.getRandomCoefficient;
import static edu.project4.Renderers.RendererProcessor.newPoint;
import static edu.project4.Renderers.RendererProcessor.randomTransformation;
import static edu.project4.Renderers.RendererProcessor.rotatePoint;

public class MultiThreadRenderer extends SingleThreadRenderer {
    private static final int ITERATION_START = -20;
    private static final double TWO_PI = 2 * Math.PI;
    private final ExecutorService executorService = Executors.newFixedThreadPool(12);

    @SuppressWarnings("MagicNumber")
    private void doIterations(
        FractalImage image, Rect field, List<Transformation> transformations,
        AffineCoefficients[] coefficientsArray, int iterationAmount, int symmetryValue
    ) {
        Point point = field.getPoint();
        for (int step = ITERATION_START; step < iterationAmount; step++) {
            AffineCoefficients randomCoefficients = getRandomCoefficient(coefficientsArray);
            point = newPoint(randomCoefficients, point);
            Transformation transform = randomTransformation(transformations);
            point = transform.apply(point);
            double angle = 0.0;
            for (int i = 0; i < symmetryValue; angle += TWO_PI / symmetryValue, i++) {
                Point rotatedPoint = rotatePoint(point, angle);
                if (field.contains(rotatedPoint)) {
                    int pixelX = (int) ((rotatedPoint.x() - field.x()) * image.getWidth() / field.width());
                    int pixelY = (int) ((rotatedPoint.y() - field.y()) * image.getHeight() / field.height());

                    Pixel pixel = image.getPixel(pixelX, pixelY);
                    if (pixel != null) {
                        synchronized (pixel) {
                            if (pixel.getHitCounter() == 0) {
                                Color color = randomCoefficients.color();
                                pixel.setR(color.getRed());
                                pixel.setG(color.getGreen());
                                pixel.setB(color.getBlue());
                            } else {
                                Color color = randomCoefficients.color();
                                pixel.setR((pixel.getR() + color.getRed()) / 2);
                                pixel.setG((pixel.getG() + color.getGreen()) / 2);
                                pixel.setB((pixel.getB() + color.getBlue()) / 2);
                            }
                            pixel.setHitCounter(pixel.getHitCounter() + 1);
                        }
                    }
                }
            }
        }
    }

    @Override
    public FractalImage render(
        FractalImage image, Rect field, List<Transformation> transformations,
        int coeffsAmount, int iterationAmount, int symmetryValue
    ) {
        AffineCoefficients[] coefficientsArray = getCoefficients(coeffsAmount);
        for (int num = 0; num < coeffsAmount; num++) {
            executorService.execute(() ->
                doIterations(image, field, transformations, coefficientsArray, iterationAmount, symmetryValue));
        }
        executorService.shutdown();
        executorService.close();
        return image;
    }
}
