package Exceptions;

public class ProductoNullException extends Exception{
    
    public ProductoNullException(){
        super();
    }

    public ProductoNullException(String message){
        super(message);
    }
}
