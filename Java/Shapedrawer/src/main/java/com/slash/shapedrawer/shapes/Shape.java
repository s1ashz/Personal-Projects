package com.slash.shapedrawer.shapes;

import com.slash.shapedrawer.canvas.service.LinePoints;
import com.slash.shapedrawer.commands.CommandNumbers;

import java.util.List;

public abstract class Shape {

    private List<LinePoints> shapeLines;

    public List<LinePoints> getShapeLines() {
        return shapeLines;
    }

    protected void setShapeLines(List<LinePoints> shapeLines) {
        this.shapeLines = shapeLines;
    }

    public abstract void createShape(CommandNumbers commandNumbers);
}
