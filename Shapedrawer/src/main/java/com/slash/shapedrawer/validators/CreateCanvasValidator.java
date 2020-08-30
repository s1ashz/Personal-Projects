package com.slash.shapedrawer.validators;

import com.slash.shapedrawer.commands.CommandArguments;
import com.slash.shapedrawer.commands.CommandNumbers;
import com.slash.shapedrawer.exceptions.CanvasException;
import com.slash.shapedrawer.exceptions.ShapeDrawerException;

public class CreateCanvasValidator extends CommandValidator {

    private final int numberOfPointRequired = 2;
    private final int maxWidth = 99;
    private final int maxHeight = 99;

    CommandArguments commandArguments;

    public CreateCanvasValidator(CommandArguments commandArguments) {
        super(commandArguments);
        this.commandArguments = commandArguments;
    }

    @Override
    public CommandNumbers getValidatedCommandNumbers() throws ShapeDrawerException {
        validateCommandRequiredPoints(numberOfPointRequired);
        CommandNumbers commandNumbers = new CommandNumbers();
        commandNumbers.setCommandPoints(commandArguments.getCommandPoints());
        validateMaxDimensions(commandNumbers);
        return commandNumbers;
    }

    private void validateMaxDimensions(CommandNumbers commandNumbers) throws ShapeDrawerException {
        if ( commandNumbers.getFirstPoint() > 99 || commandNumbers.getSecondPoint() > 99 ) {
            throw new CanvasException(EXCEPTION_CANVAS_DIMENSIONS);
        }
    }

}
