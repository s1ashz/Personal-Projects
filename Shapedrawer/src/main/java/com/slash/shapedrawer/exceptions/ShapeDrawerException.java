package com.slash.shapedrawer.exceptions;

public abstract class ShapeDrawerException extends Exception {

    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String message;

    public ShapeDrawerException( Exception exception ) {
        super( exception );
    }

    public ShapeDrawerException( String message ) {
        super( message );
        this.message = message;
    }

    public ShapeDrawerException( String errorCode, String message ) {
        super( errorCode );
        this.errorCode = errorCode;
        this.message = message;
    }

    public ShapeDrawerException(String errorCode, Exception exception) {
        super( errorCode, exception );
        this.errorCode = errorCode;
    }

    public ShapeDrawerException(String errorCode, String message, Exception exception) {
        super( errorCode, exception );
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

}
