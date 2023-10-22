package edu.hw2;

public class Task4 {

    private Task4() {
    }

    public static CallingInfo whoCalled() {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        return new CallingInfo(stack[1].getClassName(), stack[1].getMethodName());
    }

    public record CallingInfo(String className, String methodName) {
    }
}
