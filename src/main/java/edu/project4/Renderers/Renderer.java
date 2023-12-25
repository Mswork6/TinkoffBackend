package edu.project4.Renderers;

import edu.project4.FractalImage;
import edu.project4.Rect;
import edu.project4.Transformations.Transformation;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage image,
        Rect field,
        List<Transformation> transformations,
        int coeffsAmount,
        int iterationAmount,
        int symmetryValue
    );
}
