package com.slash.shapedrawer.commands.impl;

import com.slash.shapedrawer.commands.AbstractCommand;
import com.slash.shapedrawer.commands.CommandArguments;
import com.slash.shapedrawer.commands.CommandNumbers;
import com.slash.shapedrawer.exceptions.ShapeDrawerException;
import com.slash.shapedrawer.validators.CreateCanvasValidator;

class CreateCanvasCommand extends AbstractCommand {

    public CreateCanvasCommand(CommandArguments commandArguments) {
        super(commandArguments);
    }

    @Override
    public void execute() throws ShapeDrawerException {
        CreateCanvasValidator createCanvasValidator = new CreateCanvasValidator(getCommandArguments());
        CommandNumbers commandNumbers = createCanvasValidator.getValidatedCommandNumbers();

        getCanvasService().createCanvas(commandNumbers.getFirstPoint(), commandNumbers.getSecondPoint());
    }

}
