package controller;

import java.util.List;
import model.ItemVenta;
import model.Producto;
import model.Venta;
import repository.ProductoRepositorio;
import repository.VentaRepositorio;
import service.TicketService;

public class ControladorVenta {

    private Venta ventaActual;
    private ProductoRepositorio repo;
    private VentaRepositorio ventaRepository;
    private TicketService ticketServicio;

    public ControladorVenta(ProductoRepositorio repo) {
        this.repo = repo;
        this.ventaRepository = new VentaRepositorio();
        this.ticketServicio = new TicketService();
    }
//-----------------------------------------------------------------------------------------------//

    public void nuevaVenta() {
        ventaActual = new Venta();
    }
//-----------------------------------------------------------------------------------------------//

    public void agregarProductoVenta(int id, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("");
        }

        if (ventaActual == null) {
            throw new IllegalArgumentException("");
        }

        Producto producto = repo.buscarProducto(id);
        ItemVenta itemVenta = new ItemVenta(producto, cantidad);

        ventaActual.agregarItem(itemVenta);
    }
//-----------------------------------------------------------------------------------------------//

    public float finalizarVenta() {
        if (ventaActual == null) {
            throw new IllegalArgumentException("");
        }

        if (ventaActual.getItem().isEmpty()) {
            throw new IllegalArgumentException("");
        }

        for (ItemVenta i : ventaActual.getItem()) {
            Producto p = i.getProducto();
            int stock = p.getStock();
            int cantidad = i.getCantidad();

            if (stock < cantidad) {
                throw new IllegalArgumentException("Stock insuficiente para: " + p.getNombre());
            }
        }

        for (ItemVenta i : ventaActual.getItem()) {

            Producto p = i.getProducto();
            int stock = p.getStock();
            int cantidad = i.getCantidad();

            int nuevoStock = stock - cantidad;

            p.setStock(nuevoStock);
        }

        float total = ventaActual.getTotal();
        String ticket = ventaActual.generarTicket();
        ticketServicio.guardar(ticket);
        ventaRepository.guardarVenta(ventaActual);

        ventaActual = null;

        return total;
    }
//-----------------------------------------------------------------------------------------------//
    public void listarVentas(){
        List<Venta> ventas = ventaRepository.listarVentas();
        
        for (Venta venta : ventas) {
            System.out.println("Venta ID: " + venta.getId() + " | Total: $" + venta.getTotal() + " | Items: " + venta.getItem().size());
        }
    }
//-----------------------------------------------------------------------------------------------//
    public Venta buscarVenta(int id){
        if(id <= 0){
            throw new IllegalArgumentException("ID INVALIDO: No puede ingresar un id menor a 1.");
        }
        return ventaRepository.buscarVenta(id);
    }
//-----------------------------------------------------------------------------------------------//
    public boolean eliminarProducto(int id){
        if(id <= 0){
            throw new IllegalArgumentException("ID INVALIDO: No puede ingresar un id menor a 1.");
        }
        boolean resultado = ventaActual.eliminarProducto(id);

        if(ventaActual == null){
            throw new IllegalArgumentException("Error venta nula");
        }
        return resultado;
    }
}
