package com.slash.shapedrawer.canvas.service;

import com.slash.shapedrawer.constans.ConstantManager;
import com.slash.shapedrawer.exceptions.CanvasException;
import com.slash.shapedrawer.exceptions.ShapeDrawerException;
import com.slash.shapedrawer.shapes.Shape;

public class CanvasServiceImpl implements ConstantManager, CanvasService {

    private static CanvasServiceImpl instance;

    private Canvas canvas;

    private CanvasServiceImpl() {
    }

    public static CanvasService getInstance() {
        if ( null == instance ) {
            instance = new CanvasServiceImpl();
        }
        return instance;
    }

    @Override
    public void createCanvas(Integer width, Integer height) {
        canvas = new Canvas(width, height);
    }

    @Override
    public void clearCanvas() throws ShapeDrawerException {
        checkCanvasIsCreated();
        canvas.clearCanvas();
    }

    @Override
    public void drawShape(Shape shape) throws ShapeDrawerException {
        checkCanvasIsCreated();
        canvas.drawShape(shape.getShapeLines());
    }

    private void checkCanvasIsCreated() throws ShapeDrawerException {
        if ( null == canvas ) {
            throw new CanvasException(EXCEPTION_CANVAS_NOT_CREATED);
        }
    }
}
