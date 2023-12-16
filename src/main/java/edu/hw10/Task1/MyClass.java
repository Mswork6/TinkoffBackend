package edu.hw10.Task1;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;

public class MyClass {
    public final int intValue;

    public final boolean booleanValue;

    @NotNull
    public final String stringValue;

    public MyClass(@Min(10) @Max(100) int intValue, boolean booleanValue, @NotNull String stringValue) {
        this.intValue = intValue;
        this.booleanValue = booleanValue;
        this.stringValue = stringValue;
    }

    public static MyClass create(@Max(10) int intValue) {
        return new MyClass(intValue, true, "default");
    }
}
