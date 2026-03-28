package Exceptions;

public class ProductoNoEncontradoException extends Exception{

    public ProductoNoEncontradoException(){
        super();
    }

    public ProductoNoEncontradoException(String message){
        super(message);
    }
}
