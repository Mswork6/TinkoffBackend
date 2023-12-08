package edu.project4;

import lombok.Getter;

@Getter public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCounter;
    private double normalValue;

    public Pixel(int r, int g, int b, int hitCount, double normal) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCounter = hitCount;
        this.normalValue = normal;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setHitCounter(int hitCounter) {
        this.hitCounter = hitCounter;
    }

    public void setNormalValue(double normalValue) {
        this.normalValue = normalValue;
    }
}
