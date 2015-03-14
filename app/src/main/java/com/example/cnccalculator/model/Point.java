package com.example.cnccalculator.model;

/**
 * Created by ricardohdz on 3/6/15.
 */
public class Point {

    private double X;
    private double Y;

    public Point() {
        this.X = 0;
        this.Y = 0;
    }

    public Point(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public double getX() {
        return this.X;
    }

    public double getY() {
        return this.Y;
    }

    public void setCoordinates(double x, double y) {
        this.X = x;
        this.Y = y;
    }
}
