package com.slash.shapedrawer.commands.impl;

import com.slash.shapedrawer.commands.AbstractCommand;
import com.slash.shapedrawer.commands.CommandArguments;
import com.slash.shapedrawer.commands.CommandNumbers;
import com.slash.shapedrawer.exceptions.ShapeDrawerException;
import com.slash.shapedrawer.shapes.Shape;
import com.slash.shapedrawer.shapes.ShapeFactory;
import com.slash.shapedrawer.validators.CreateShapeValidator;

public class CreateShapeCommand extends AbstractCommand {

    public CreateShapeCommand(CommandArguments commandArguments) {
        super(commandArguments);
    }

    @Override
    public void execute() throws ShapeDrawerException {
        CreateShapeValidator createCanvasValidator = new CreateShapeValidator(getCommandArguments());
        CommandNumbers commandNumbers = createCanvasValidator.getValidatedCommandNumbers();

        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape(getCommandArguments().getCommandType());

        shape.createShape(commandNumbers);

        getCanvasService().drawShape(shape);

    }

}
