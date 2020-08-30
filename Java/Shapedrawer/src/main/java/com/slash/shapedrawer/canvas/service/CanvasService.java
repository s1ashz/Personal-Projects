package com.slash.shapedrawer.canvas.service;

import com.slash.shapedrawer.exceptions.ShapeDrawerException;
import com.slash.shapedrawer.shapes.Shape;

public interface CanvasService {

    void createCanvas(Integer with, Integer height);
    void clearCanvas() throws ShapeDrawerException;
    void drawShape(Shape shape) throws ShapeDrawerException;

}
