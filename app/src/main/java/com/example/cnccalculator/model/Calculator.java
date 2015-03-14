package com.example.cnccalculator.model;

import android.util.Log;

/**
 * Created by ricardohdz on 3/6/15.
 */
public class Calculator {

    public static final double default_xoffset = 0.0;
    public static final double default_yoffset = 0.0;
    public static final double default_radio = 0.0;
    public static final double default_alpha = 0.0;

    /* Parameters */
    private CornerSelection cornerTypeSelected;
    private PositionSelection positionSelected;
    private double alpha;
    private double radio;
    private double origX;
    private double origY;

    /* Distances */
    private double one;
    private double two;
    private double three;
    private double four;

    public Calculator(CornerSelection selection, PositionSelection position) {
        this.cornerTypeSelected = selection;
        this.positionSelected = position;
        this.alpha = default_alpha;
        this.radio = default_radio;
        this.origX = default_xoffset;
        this.origY = default_yoffset;
    }
    public CNCPoints calculatePoint() {
        one = radio * Math.cos(alpha);
        two = radio - one;
        three = two / Math.tan(alpha);
        four = radio * Math.sin(alpha) - three;

        // First point corresponds to the one lying in the selected corner type (Horizontal/Vertical)
        Point firstPoint = new Point();
        Point secondPoint = new Point();
        Log.d("Calculator",String.format("Corner: %s, Position: %s, Radio: %f, Alpha: %f, Offset: (%f,%f)",cornerTypeSelected.toString(),positionSelected.toString(),radio,alpha,origX,origY));
        switch(this.cornerTypeSelected) {
            case Horizontal:
                switch (this.positionSelected) {
                    case First:
                        firstPoint.setCoordinates(origX - four, origY);
                        secondPoint.setCoordinates(origX + three, origY - two);
                        break;
                    case Second:
                        firstPoint.setCoordinates(origX + four, origY);
                        secondPoint.setCoordinates(origX - three, origY - two);
                        break;
                    case Third:
                        firstPoint.setCoordinates(origX + four, origY);
                        secondPoint.setCoordinates(origX - three, origY + two);
                        break;
                    case Fourth:
                        firstPoint.setCoordinates(origX - four, origY);
                        secondPoint.setCoordinates(origX + three, origY + two);
                        break;
                }
                break;
            case Vertical:
                switch (this.positionSelected) {
                    case First:
                        firstPoint.setCoordinates(origX, origY - four);
                        secondPoint.setCoordinates(origX - two, origY + three);
                        break;
                    case Second:
                        firstPoint.setCoordinates(origX, origY - four);
                        secondPoint.setCoordinates(origX + two, origY + three);
                        break;
                    case Third:
                        firstPoint.setCoordinates(origX, origY + four);
                        secondPoint.setCoordinates(origX + two, origY - three);
                        break;
                    case Fourth:
                        firstPoint.setCoordinates(origX, origY + four);
                        secondPoint.setCoordinates(origX - two, origY - three);
                        break;
                }
                break;
        }

        return new CNCPoints(firstPoint,secondPoint);
    }

    public void setAlphaDegrees(double alpha) {
        this.alpha = Math.toRadians(alpha);
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public void setOrigin(double origX, double origY) {
        this.origX = origX;
        this.origY = origY;
    }
}
