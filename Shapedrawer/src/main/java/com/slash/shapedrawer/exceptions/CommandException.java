package com.slash.shapedrawer.exceptions;

public class CommandException extends ShapeDrawerException {

    private static final long serialVersionUID = 1L;

    public CommandException(Exception exception ) {
        super( exception );
    }

    public CommandException(String message ) {
        super( message );
    }

    public CommandException(String errorCode, String message ) {
        super( errorCode, message );
    }

}
