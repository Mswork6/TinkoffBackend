package edu.hw10.Task1;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;

public record MyRecord(@Max(3) @Min(2) int val, @NotNull String str) {}
