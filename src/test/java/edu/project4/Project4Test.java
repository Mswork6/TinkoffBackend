package edu.project4;

import edu.project4.Renderers.MultiThreadRenderer;
import edu.project4.Renderers.SingleThreadRenderer;
import edu.project4.Transformations.HeartTransformation;
import edu.project4.Transformations.SwirlTransformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Project4Test {
    @Test
    @DisplayName("Проверка работоспособности однопоточного рендера")
    public void testSingleRender() {
        // given

        //when

        //then
        Assertions.assertDoesNotThrow(() ->
            new SingleThreadRenderer().render(
                FractalImage.createImage(1920, 1080),
                new Rect(-8, -5, 16, 12),
                List.of(
                    new HeartTransformation()
                ),
                20, 100000, 8
            )
        );
    }

    @Test
    @DisplayName("Проверка работоспособности многопоточного рендера")
    public void testMultiThreadRender() {
        // given

        //when

        //then
        Assertions.assertDoesNotThrow(() ->
            new MultiThreadRenderer().render(
                FractalImage.createImage(1920, 1080),
                new Rect(-8, -5, 16, 12),
                List.of(
                    new HeartTransformation()
                ),
                20, 100000, 8
            )
        );
    }

    @Test
    @DisplayName("Проверка работоспособности модуля сохранения")
    public void testSaver() throws IOException {
        // given
        Files.deleteIfExists(Path.of("test.jpeg"));

        //when
        ImageSaver.saveImage(Path.of("test.jpeg"), ImageFormat.JPEG,
            FractalImage.createImage(1920, 1080));

        //then
        assertThat(Path.of("test.jpeg")).exists();
    }

    @Test
    @DisplayName("Проверка работоспособности лаунчера")
    public void testGamma() {
        Assertions.assertDoesNotThrow(() ->
            Main.generateImage(1920,
                1080,
                new MultiThreadRenderer(),
                new Rect(-8, -5, 16, 12),
                List.of(new SwirlTransformation()),
                ImageFormat.JPEG,
                Path.of("output.jpeg")
            ));
    }
}
