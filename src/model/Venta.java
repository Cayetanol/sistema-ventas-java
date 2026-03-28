package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {

    private int id;
    private LocalDate fecha;
    private double total;
    private List<ItemVenta> itemList;

    public Venta(int id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
        this.itemList = new ArrayList<>();
    }

    public Venta() {
        this.itemList = new ArrayList<>();
        this.fecha = LocalDate.now();
    }

//-----------------------------------------------------------------------------------------------//
//Agregar items
    public void agregarItem(ItemVenta item) {

        boolean flag = false;

        if (item == null) {
            throw new IllegalArgumentException("");
        }

        if (item.getCantidad() <= 0) {
            throw new IllegalArgumentException("");
        }

        for (ItemVenta i : itemList) {

            if (i.getProducto().getId() == item.getProducto().getId()) {
                i.aumentarCantidad(item.getCantidad());
                flag = true;
                break;
            }
        }

        if (!flag) {
            itemList.add(item);
        }
    }
//-----------------------------------------------------------------------------------------------//
//Calcular total

    public double getTotal() {
        return this.total; // Usar el total almacenado en lugar de recalcularlo
    }
//-----------------------------------------------------------------------------------------------//

    public String generarTicket() {

    StringBuilder ticket = new StringBuilder();

    ticket.append("===== TICKET =====\n");
    ticket.append("Fecha: ").append(fecha).append("\n\n");

    for (ItemVenta itemVenta : itemList) {

        String nombre = itemVenta.getProducto().getNombre();
        int cantidad = itemVenta.getCantidad();
        double subtotal = itemVenta.getProducto().getPrecio() * itemVenta.getCantidad();

        ticket.append(String.format("%-15s x%-3d $%8.2f\n", nombre, cantidad, subtotal));
    }

    ticket.append("\n-------------------------\n");
    ticket.append(String.format("TOTAL:          $%8.2f\n", getTotal()));

    return ticket.toString();
}
//-----------------------------------------------------------------------------------------------//
    public boolean eliminarProducto(int id){
        for (ItemVenta itemVenta : itemList) {
            if(itemVenta.getProducto().getId() == id){
                itemList.remove(itemVenta);
                return true;
            }
        }
        return false;
    }
//-----------------------------------------------------------------------------------------------//

//-----------------------------------------------------------------------------------------------//
    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public List<ItemVenta> getItem() {
        return itemList;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }

    public void setTotal(double total){
        this.total = total;
    }
}
