package com.slash.shapedrawer.validators;

import com.slash.shapedrawer.commands.CommandArguments;
import com.slash.shapedrawer.commands.CommandNumbers;
import com.slash.shapedrawer.exceptions.CommandException;

public class CreateShapeValidator extends CommandValidator {

    private final int numberOfPointRequired = 4;
    CommandArguments commandArguments;

    public CreateShapeValidator(CommandArguments commandArguments) {
        super(commandArguments);
        this.commandArguments = commandArguments;
    }

    @Override
    public CommandNumbers getValidatedCommandNumbers() throws CommandException {
        validateCommandRequiredPoints(numberOfPointRequired);
        CommandNumbers commandNumbers = new CommandNumbers();
        commandNumbers.setCommandPoints(commandArguments.getCommandPoints());
        return commandNumbers;
    }
}
