package app;

import controller.ControladorProducto;
import controller.ControladorVenta;
import repository.ProductoRepositorio;
import repository.VentaRepositorio;
import service.InformeService;

public class Main {
    public static void main(String[] args) {
        
        // Repositorios
        VentaRepositorio ventaRepository = new VentaRepositorio();
        ProductoRepositorio productoRepositorio = new ProductoRepositorio();

        // Services
        InformeService informeService = new InformeService(ventaRepository);

        // Controladores
        ControladorProducto controladorProducto = new ControladorProducto(productoRepositorio);
        ControladorVenta controladorVenta = new ControladorVenta(productoRepositorio, informeService, ventaRepository);

        // Menu (UI)
        Menu menu = new Menu(controladorProducto, controladorVenta);

        // Ejecutar sistema
        menu.opcion();
    }
}