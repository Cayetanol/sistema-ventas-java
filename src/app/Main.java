package app;

import controller.ControladorProducto;
import controller.ControladorVenta;
import repository.ProductoRepositorySQLite;
import repository.VentaRepositorySQLite;
import service.InformeService;
import service.ProductoService;

public class Main {

    public static void main(String[] args) {

        // Repositorios
        ProductoRepositorySQLite productoRepo = new ProductoRepositorySQLite();
        VentaRepositorySQLite ventaRepo = new VentaRepositorySQLite();

        // Servicios
        ProductoService productoService = new ProductoService(productoRepo);
        InformeService informeService = new InformeService(ventaRepo);

        // Controladores
        ControladorProducto controladorProducto = new ControladorProducto(productoService);
        ControladorVenta controladorVenta = new ControladorVenta(productoService, informeService, ventaRepo);

        //  MENU
        Menu menu = new Menu(controladorProducto, controladorVenta);
        menu.opcion();
    }
}