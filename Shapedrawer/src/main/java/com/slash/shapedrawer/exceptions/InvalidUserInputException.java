package com.slash.shapedrawer.exceptions;

public class InvalidUserInputException extends ShapeDrawerException {

    private static final long serialVersionUID = 1L;

    public InvalidUserInputException(Exception exception ) {
        super( exception );
    }

    public InvalidUserInputException(String message ) {
        super( message );
    }

    public InvalidUserInputException(String errorCode, String message ) {
        super( errorCode, message );
    }
}
