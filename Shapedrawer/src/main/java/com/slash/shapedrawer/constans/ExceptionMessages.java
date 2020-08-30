package com.slash.shapedrawer.constans;

public interface ExceptionMessages {

    String EXCEPTION_USER_INPUT_NULL = "The command entered was null";
    String EXCEPTION_USER_INPUT_INVALID_FORMAT = "The command entered does not have the correct format. \nExpected Format: Letter number number number number";
    String EXCEPTION_USER_INPUT_NOT_AVAILABLE = "The command entered is not available";

    String EXCEPTION_COMMAND_NEEDS_POINTS = "The command: %s requires %s numbers";
    String EXCEPTION_COMMAND_HAS_INVALID_NUMBER_VALUE = "The command: %s has invalid number value";

    String EXCEPTION_CANVAS_NOT_CREATED = "Please create a canvas first using the command: c width height";
    String EXCEPTION_CANVAS_DIMENSIONS = "Canvas max dimensions are 99 x 99";
    String EXCEPTION_CANVAS_BOUNDARIES = "The Shapes must be inside canvas range";
}
