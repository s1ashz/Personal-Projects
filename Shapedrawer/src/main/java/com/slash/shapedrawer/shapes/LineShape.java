package com.slash.shapedrawer.shapes;

import com.slash.shapedrawer.canvas.service.LinePoints;
import com.slash.shapedrawer.commands.CommandNumbers;

import java.util.ArrayList;
import java.util.List;

class LineShape extends Shape {

    public LineShape() {
    }

    @Override
    protected void setShapeLines(List<LinePoints> shapeLines) {
        super.setShapeLines(shapeLines);
    }

    @Override
    public void createShape(CommandNumbers commandNumbers) {
        List<LinePoints> shapeLines = new ArrayList<>();
        LinePoints firstLine = new LinePoints(commandNumbers.getFirstPoint(), commandNumbers.getSecondPoint(), commandNumbers.getThirdPoint(), commandNumbers.getFourthPoint());
        shapeLines.add(firstLine);
        setShapeLines(shapeLines);
    }

}
