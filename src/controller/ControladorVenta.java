package controller;

import Exceptions.IdInvalidoException;
import Exceptions.ProductoNullException;
import java.sql.SQLException;
import java.util.List;
import model.InformeVentas;
import model.ItemVenta;
import model.Producto;
import model.Venta;
import repository.VentaRepositorio;
import repository.VentaRepositorySQLite;
import service.InformeService;
import service.ProductoService;
import service.TicketService;

public class ControladorVenta {

    private Venta ventaActual;
    private ProductoService service;
    private VentaRepositorySQLite ventaRepository;
    private TicketService ticketServicio;
    private InformeService informeServicio;

    public ControladorVenta(ProductoService service, InformeService informeServicio, VentaRepositorio ventaRepository) {
        this.service = service;
        this.ventaRepository = new VentaRepositorySQLite();
        this.ticketServicio = new TicketService();
        this.informeServicio = informeServicio;
    }
//-----------------------------------------------------------------------------------------------//

    public void nuevaVenta() {
        ventaActual = new Venta();
    }
//-----------------------------------------------------------------------------------------------//

    public void agregarProductoVenta(int id, int cantidad) throws IdInvalidoException, ProductoNullException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Error: La cantidad no puede ser menor o igual a cero.");
        }

        if (ventaActual == null) {
            throw new IllegalArgumentException("Error: No hay venta activa.");
        }

        Producto producto = service.buscarProducto(id);
        ItemVenta itemVenta = new ItemVenta(producto, cantidad);
        itemVenta.setPrecioUnitario(producto.getPrecio());
        ventaActual.agregarItem(itemVenta);
    }
//-----------------------------------------------------------------------------------------------//

    public double finalizarVenta() throws IdInvalidoException, SQLException {

        if (ventaActual == null) {
            throw new IllegalArgumentException("Error: No hay venta activa.");
        }

        if (ventaActual.getItem().isEmpty()) {
            throw new IllegalArgumentException("Error: La venta no tiene productos.");
        }

        double total = 0;
        for (ItemVenta item : ventaActual.getItem()) {
            total += item.getCantidad() * item.getPrecioUnitario();
        }
        ventaActual.setTotal(total);

        for (ItemVenta item : ventaActual.getItem()) {

            System.out.println("Producto: " + item.getProducto().getNombre());
            System.out.println("Stock actual: " + item.getProducto().getStock());
            System.out.println("Cantidad pedida: " + item.getCantidad());

            if (item.getCantidad() > item.getProducto().getStock()) {

                throw new IllegalArgumentException(
                        "Stock insuficiente para: " + item.getProducto().getNombre()
                );
            }
        }

        for (ItemVenta item : ventaActual.getItem()) {

            Producto p = item.getProducto();
            int nuevoStock = p.getStock() - item.getCantidad();

            service.actualizarStock(p.getId(), nuevoStock);
        }

        String ticket = ventaActual.generarTicket();
        ticketServicio.guardar(ticket);
        ventaRepository.guardarVenta(ventaActual);

        ventaActual = null;

        return total;
    }
//-----------------------------------------------------------------------------------------------//

    public void listarVentas() throws SQLException {
        List<Venta> ventas = ventaRepository.listarVentas();

        for (Venta venta : ventas) {
            System.out.println("Venta ID: " + venta.getId() + " | Total: $" + venta.getTotal() + " | Items: " + venta.getItem().size());
        }
    }
//-----------------------------------------------------------------------------------------------//

    public Venta buscarVenta(int id) throws SQLException {
        List<Venta> ventas = ventaRepository.listarVentas();

        for (Venta v : ventas) {
            if (v.getId() == id) {
                return v;
            }
        }

        return null;
    }
//-----------------------------------------------------------------------------------------------//

    public boolean eliminarProducto(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID INVALIDO: No puede ingresar un id menor a 1.");
        }
        if (ventaActual == null) {
            throw new IllegalArgumentException("Error venta nula");
        }
        boolean resultado = ventaActual.eliminarProducto(id);

        return resultado;
    }
//-----------------------------------------------------------------------------------------------//

    public InformeVentas informeVentas() {
        return informeServicio.informeVentas();
    }
}
