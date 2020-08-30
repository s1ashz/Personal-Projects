package com.slash.shapedrawer.shapes;

import com.slash.shapedrawer.commands.AvailableCommands;

public class ShapeFactory {

    public Shape getShape(AvailableCommands commandArguments) {
        Shape shape = null;

        switch(commandArguments) {
            case SHAPE_LINE_COMMAND:
                shape = new LineShape();
                break;
            case SHAPE_QUADRILATERAL_COMMAND:
                shape = new QuadrilateralShape();
                break;
        }
        return shape;
    }

}
