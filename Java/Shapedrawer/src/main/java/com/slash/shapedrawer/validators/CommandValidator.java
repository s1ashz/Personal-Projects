package com.slash.shapedrawer.validators;

import com.slash.shapedrawer.commands.CommandArguments;
import com.slash.shapedrawer.commands.CommandNumbers;
import com.slash.shapedrawer.constans.ConstantManager;
import com.slash.shapedrawer.exceptions.CommandException;
import com.slash.shapedrawer.exceptions.ShapeDrawerException;

public abstract class CommandValidator implements ConstantManager {

    private CommandArguments commandArguments;

    public CommandValidator(CommandArguments commandArguments) {
        this.commandArguments = commandArguments;
    }

    public abstract CommandNumbers getValidatedCommandNumbers() throws ShapeDrawerException;

    protected void validateCommandRequiredPoints(int numberOfPointRequired) throws CommandException {
        checkCommandHasRequiredNumberOfPoints(numberOfPointRequired);
        checkPointsHasValue();
    }

    private void checkPointsHasValue() throws CommandException {
        for ( String points : commandArguments.getCommandPoints()) {
            checkNull(points);
        }
    }

    private void checkNull(String points) throws CommandException {
        if (null == points) {
            throw new CommandException( String.format( EXCEPTION_COMMAND_HAS_INVALID_NUMBER_VALUE, commandArguments.getCommandType().getValue()) );
        }
    }

    private void checkCommandHasRequiredNumberOfPoints(int numberOfPointRequired) throws CommandException {
        if (commandDoesNotHaveRequiredAmountOfPoints(numberOfPointRequired)) {
            throw new CommandException( String.format( EXCEPTION_COMMAND_NEEDS_POINTS, commandArguments.getCommandType().getValue(), numberOfPointRequired) );
        }
    }

    private boolean commandDoesNotHaveRequiredAmountOfPoints(int numberOfPointRequired) {
        return commandArguments.getCommandPoints().size() != numberOfPointRequired;
    }


}
