package com.slash.shapedrawer.validators;

import com.slash.shapedrawer.constans.ConstantManager;
import com.slash.shapedrawer.exceptions.InvalidUserInputException;
import com.slash.shapedrawer.exceptions.ShapeDrawerException;
import com.slash.shapedrawer.commands.AvailableCommands;

import java.util.List;

public class UserInputValidator implements ConstantManager {

    private final String USER_INPUT_REGEX_MATCHER = "^(?:[a-zA-Z]+([' '][\\d]+){4})|(?:[a-zA-Z]+)|(?:[a-zA-Z]+([' '][\\d]+){2})$";

    private final List<String> AVAILABLE_COMMANDS_TYPE = AvailableCommands.getAllAvailableCommands();

    public void validateUserInput(String inputString) throws ShapeDrawerException {
        validate(inputString.trim());
    }

    private void validate(String stringToValidate) throws ShapeDrawerException {
        checkNull(stringToValidate);
        checkFormat(stringToValidate);
        checkValidCommandType(stringToValidate);
    }

    private void checkNull(String stringToValidate) throws InvalidUserInputException {
        if (null == stringToValidate) {
            throw new InvalidUserInputException(EXCEPTION_USER_INPUT_NULL);
        }
    }

    private void checkFormat(String stringToValidate) throws InvalidUserInputException {
        if ( userInputDoesNotMatchFormat(stringToValidate) ) {
            throw new InvalidUserInputException(EXCEPTION_USER_INPUT_INVALID_FORMAT);
        }
    }

    private boolean userInputDoesNotMatchFormat(String stringToValidate) {
        return !stringToValidate.matches(USER_INPUT_REGEX_MATCHER);
    }

    private void checkValidCommandType(String stringToValidate) throws InvalidUserInputException {
        String commandType = getCommandTypeFromUserInput(stringToValidate);
        checkCommandTypeIsAvailable(commandType);
    }

    private String getCommandTypeFromUserInput(String stringToValidate) {
        String[] commandType = stringToValidate.split(USER_INPUT_SEPARATOR);
        return commandType[0];
    }

    private void checkCommandTypeIsAvailable(String commandType) throws InvalidUserInputException {
        if ( commandNotInAvailableCommands(commandType) ) {
            throw new InvalidUserInputException(EXCEPTION_USER_INPUT_NOT_AVAILABLE);
        }
    }

    private boolean commandNotInAvailableCommands(String commandType) {
        return !AVAILABLE_COMMANDS_TYPE.contains(commandType.toLowerCase());
    }

}
