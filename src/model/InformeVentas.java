package model;

public class InformeVentas {
    
    private int cantidadVentas;
    private double totalVendido;
    private int ventasDelDia;


    public InformeVentas(int cantidadVentas, double totalVendido, int ventasDelDia){
        this.cantidadVentas = cantidadVentas;
        this.totalVendido = totalVendido;
        this.ventasDelDia = ventasDelDia;
    }



    public int getCantidadVentas(){
        return cantidadVentas;
    }

    public double getTotalVendido(){
        return totalVendido;
    }

    public int getVentasDelDia(){
        return ventasDelDia;
    }
}
