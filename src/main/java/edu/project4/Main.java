package edu.project4;

import edu.project4.Corrections.GammaCorrection;
import edu.project4.Corrections.ImageProcessor;
import edu.project4.Renderers.MultiThreadRenderer;
import edu.project4.Renderers.Renderer;
import edu.project4.Renderers.SingleThreadRenderer;
import edu.project4.Transformations.PowerTransformation;
import edu.project4.Transformations.Transformation;
import java.nio.file.Path;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class Main {
    private final static int NANOSECONDS_TO_SECONDS = 1000000000;

    public static void generateImage(
        int width,
        int height,
        Renderer renderer,
        Rect world,
        List<Transformation> transformations,
        ImageFormat imageFormat,
        Path path
    ) {
        FractalImage fractalImage = FractalImage.createImage(width, height);
        renderer.render(fractalImage, world, transformations, 20, 1000000, 8);
        ImageProcessor imageProcessor = new GammaCorrection();
        imageProcessor.process(fractalImage);
        ImageSaver.saveImage(path, imageFormat, fractalImage);
    }

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();
        double startTimeSingle = System.nanoTime();
        generateImage(1920,
            1080,
            new SingleThreadRenderer(),
            new Rect(-8, -5, 16, 12),
            List.of(new PowerTransformation()),
            ImageFormat.JPEG,
            Path.of("output.jpeg")
        );
        double finishTimeSingle = System.nanoTime() - startTimeSingle;
        logger.info("Время работы в однопоточном режиме: " + finishTimeSingle / NANOSECONDS_TO_SECONDS + " секунд");

        double startTimeMulti = System.nanoTime();
        generateImage(1920,
            1080,
            new MultiThreadRenderer(),
            new Rect(-8, -5, 16, 12),
            List.of(new PowerTransformation()),
            ImageFormat.JPEG,
            Path.of("output.jpeg")
        );
        double finishTimeMulti = System.nanoTime() - startTimeMulti;
        logger.info("Время работы в однопоточном режиме: " + finishTimeMulti / NANOSECONDS_TO_SECONDS + " секунд");

        logger.info("среднее время ускорения: " + finishTimeSingle / finishTimeMulti + " раз");



    }
}
