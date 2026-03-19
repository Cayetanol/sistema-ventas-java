package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketService {
    
    
    public void guardar(String ticket){
        String nombre = generarNombre();
        escribirArchivo(ticket, nombre);
    }

    private String generarNombre(){
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formatoTicket = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        String fechaFormateada = fechaHora.format(formatoTicket);

        return "ticket_" + fechaFormateada + ".txt";
    }

    private void escribirArchivo(String ticket, String nombre){

        File carpeta = new File("tickets");

        if(!carpeta.exists()){
            carpeta.mkdir();
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("tickets/" + nombre))) {
            escritor.write(ticket);
        } catch (Exception IOException) {
            System.out.println("Error no se pudo crear archivo: " + IOException.getMessage());
        }
    }
}
