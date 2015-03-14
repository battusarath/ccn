package com.example.cnccalculator.model;

/**
 * Created by ricardohdz on 3/13/15.
 */
public class CNCPoints {

    private Point first;
    private Point second;

    public CNCPoints(Point first, Point second) {
        this.first = first;
        this.second = second;
    }

    public Point getFirst() {
        return this.first;
    }

    public Point getSecond() {
        return this.second;
    }
}
