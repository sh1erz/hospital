package model.exceptions;

public class InvalidSortedType extends IllegalArgumentException{
    public InvalidSortedType(String s) {
        super(s);
    }
}