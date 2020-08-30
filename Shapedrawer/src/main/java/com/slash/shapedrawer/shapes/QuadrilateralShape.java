package com.slash.shapedrawer.shapes;

import com.slash.shapedrawer.canvas.service.LinePoints;
import com.slash.shapedrawer.commands.CommandNumbers;

import java.util.ArrayList;
import java.util.List;

class QuadrilateralShape extends Shape {

    public QuadrilateralShape() {
    }

    @Override
    protected void setShapeLines(List<LinePoints> shapeLines) {
        super.setShapeLines(shapeLines);
    }

    @Override
    public void createShape(CommandNumbers commandNumbers) {
        List<LinePoints> shapeLines = new ArrayList<>();

        LinePoints firstLine = new LinePoints(commandNumbers.getFirstPoint(), commandNumbers.getSecondPoint(), commandNumbers.getThirdPoint(), commandNumbers.getSecondPoint());
        LinePoints secondLine = new LinePoints(commandNumbers.getFirstPoint(), commandNumbers.getSecondPoint(), commandNumbers.getFirstPoint(), commandNumbers.getFourthPoint());
        LinePoints thirdLine = new LinePoints(commandNumbers.getThirdPoint(), commandNumbers.getSecondPoint(), commandNumbers.getThirdPoint(), commandNumbers.getFourthPoint());
        LinePoints fourthLine = new LinePoints(commandNumbers.getFirstPoint(), commandNumbers.getFourthPoint(), commandNumbers.getThirdPoint(), commandNumbers.getFourthPoint());

        shapeLines.add(firstLine);
        shapeLines.add(secondLine);
        shapeLines.add(thirdLine);
        shapeLines.add(fourthLine);

        setShapeLines(shapeLines);
    }

}
