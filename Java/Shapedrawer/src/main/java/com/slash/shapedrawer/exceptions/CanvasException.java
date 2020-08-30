package com.slash.shapedrawer.exceptions;

public class CanvasException extends ShapeDrawerException {

    private static final long serialVersionUID = 1L;

    public CanvasException(Exception exception ) {
        super( exception );
    }

    public CanvasException(String message ) {
        super( message );
    }

    public CanvasException(String errorCode, String message ) {
        super( errorCode, message );
    }

}
