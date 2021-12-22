package model.exceptions;

public class InvalidUserTypeException extends IllegalArgumentException{
    public InvalidUserTypeException(String s) {
        super(s);
    }
}
