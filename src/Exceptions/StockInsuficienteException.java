package Exceptions;

public class StockInsuficienteException extends Exception{
    String message;

    public StockInsuficienteException(String message){
        this.message = message;
    }   
}
