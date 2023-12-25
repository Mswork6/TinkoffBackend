package edu.project4;

import java.util.stream.IntStream;
import lombok.Getter;

public class FractalImage {
    private final Pixel[][] pixels;
    @Getter private final int height;
    @Getter private final int width;

    private FractalImage(Pixel[][] pixels, int width, int height) {
        this.pixels = pixels;
        this.height = height;
        this.width = width;
    }

    public boolean imageContains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Pixel getPixel(int x, int y) {
        if (imageContains(x, y)) {
            return pixels[x][y];
        }
        return null;
    }

    public static FractalImage createImage(int width, int height) {
        Pixel[][] currentPixels = new Pixel[width][height];
        IntStream.range(0, width)
            .forEach(i -> IntStream.range(0, height)
                .forEach(j -> currentPixels[i][j] = new Pixel(0, 0, 0, 0, 0)));
        return new FractalImage(currentPixels, width, height);
    }

}
