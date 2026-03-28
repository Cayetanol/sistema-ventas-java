package Exceptions;

public class VentaNoIniciadaException extends Exception{
     String message;

     public VentaNoIniciadaException(String message){
        this.message = message;
     }
}
