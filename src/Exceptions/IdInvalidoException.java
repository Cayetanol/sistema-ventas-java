package Exceptions;

public class IdInvalidoException extends Exception{
    
    public IdInvalidoException(){
        super();
    }

    public IdInvalidoException(String message){
        super(message);
    }
}
