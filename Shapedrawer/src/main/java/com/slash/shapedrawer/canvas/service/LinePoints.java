package com.slash.shapedrawer.canvas.service;

public class LinePoints {

    private int pointOneX;
    private int pointOneY;
    private int pointTwoX;
    private int pointTwoY;

    public LinePoints(int pointOneX, int pointOneY, int pointTwoX, int pointTwoY) {
        this.pointOneX = pointOneX;
        this.pointOneY = pointOneY;
        this.pointTwoX = pointTwoX;
        this.pointTwoY = pointTwoY;
    }

    public int getPointOneX() {
        return pointOneX;
    }

    public int getPointOneY() {
        return pointOneY;
    }

    public int getPointTwoX() {
        return pointTwoX;
    }

    public int getPointTwoY() {
        return pointTwoY;
    }
}
