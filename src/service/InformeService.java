package service;

import java.time.LocalDate;
import java.util.List;
import model.InformeVentas;
import model.Venta;
import repository.VentaRepositorio;


public class InformeService {
    
private VentaRepositorio ventaRepositorio;

    public InformeService(VentaRepositorio ventaRepositorio){
        this.ventaRepositorio = ventaRepositorio;
    }

    public InformeVentas informeVentas(){
        int cantidadVentas = 0;
        double totalVendido = 0;
        int ventasDelDia = 0;
        LocalDate hoy = LocalDate.now();

        List<Venta> listaVentas = ventaRepositorio.listarVentas();

        for(Venta venta : listaVentas){

            cantidadVentas++;
            totalVendido += venta.getTotal();

            if(venta.getFecha().equals(hoy)){
                ventasDelDia++;
            }
        }
        return new InformeVentas(cantidadVentas, totalVendido, ventasDelDia);
    }
}
