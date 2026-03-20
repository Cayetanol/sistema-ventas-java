package app;

import controller.ControladorProducto;
import controller.ControladorVenta;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.InformeVentas;
import model.Producto;
import model.Venta;

public class Menu {

    private ControladorProducto controladorProducto;
    private ControladorVenta controladorVenta;
    private Scanner sc = new Scanner(System.in);

    public Menu(ControladorProducto controladorProducto, ControladorVenta controladorVenta) {
        this.controladorProducto = controladorProducto;
        this.controladorVenta = controladorVenta;
    }

    public void opcion() {
        int numeroOpcion;

        do {
            mostrarMenu();
            numeroOpcion = leerOpcion();
            opcionMenu(numeroOpcion);
        } while (numeroOpcion != 4);
    }
//-----------------------------------------------------------------------------------------------//

    private void mostrarMenu() {
        System.out.println("===MENU===");
        System.out.println("1) VENTA");
        System.out.println("2) PRODUCTOS");
        System.out.println("3) INFORME VENTAS");
        System.out.println("4) SALIR");

    }
//-----------------------------------------------------------------------------------------------//

    private int leerOpcion() {
        int opcion;

        try {
            System.out.println("Ingrese opcion");
            opcion = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            sc.nextLine();
            throw new InputMismatchException("AVISO: Elija una opcion valida.");
        }
        return opcion;
    }
//-----------------------------------------------------------------------------------------------//

    private void opcionMenu(int opcion) {

        switch (opcion) {
            case 1:
                opcionesVenta();
                break;
            case 2:
                opcionesProductos();
                break;
            case 3:
                InformeVentas informe = controladorVenta.informeVentas();

                System.out.println("===== INFORME DE VENTAS =====");
                System.out.println("Cantidad de ventas: " + informe.getCantidadVentas());
                System.out.println("Total vendido: " + informe.getTotalVendido());
                System.out.println("Ventas del dia: " + informe.getVentasDelDia());
                break;
            case 4:
                System.out.println("Saliendo...");
                break;
            default:
                throw new AssertionError();
        }
    }
//-----------------------------------------------------------------------------------------------//

    private void opcionesVenta() {
        int opcion;
        do {
            System.out.println("===VENTA===");
            System.out.println("1) Nueva venta");
            System.out.println("2) Eliminar producto");
            System.out.println("3) Finalizar venta");
            System.out.println("4) Buscar venta");
            System.out.println("5) Volver");

            opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    try {
                        int opcionAgregarProducto = 0;
                        controladorVenta.nuevaVenta();
                        System.out.println("Venta iniciada");

                        do {
                            System.out.println("Ingrese id del producto:");
                            int id = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Ingrese cantidad:");
                            int cantidad = sc.nextInt();
                            sc.nextLine();

                            controladorVenta.agregarProductoVenta(id, cantidad);

                            System.out.println("Producto agregado.");

                            System.out.println("Seguir agregando?");
                            System.out.println("1) Si");
                            System.out.println("2) No");
                            opcionAgregarProducto = sc.nextInt();
                            sc.nextLine();
                        } while (opcionAgregarProducto != 2);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {

                        System.out.println("Ingrese id del producto a eliminar");
                        int id = sc.nextInt();
                        sc.nextLine();

                        boolean resultado = controladorVenta.eliminarProducto(id);

                        if (resultado) {
                            System.out.println("Producto eliminado del carrito con exito.");
                        } else {
                            System.out.println("El producto no estaba en el carrito.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        float total = controladorVenta.finalizarVenta();
                        System.out.println("Finalizando venta.");
                        System.out.println("Total: " + total);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Ingrese id");
                        int id = sc.nextInt();
                        sc.nextLine();

                        Venta venta = controladorVenta.buscarVenta(id);

                        if (venta == null) {
                            System.out.println("No existe la venta");
                        } else {
                            System.out.println(venta.generarTicket());
                        }
                    } catch (Exception e) {
                        System.out.println("Ingrese un ID valido");
                    }
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    break;
            }
        } while (opcion != 5);
    }
//-----------------------------------------------------------------------------------------------//

    private void opcionesProductos() {
        int opcion;
        do {
            System.out.println("===PRODUCTOS===");
            System.out.println("1) Agregar producto");
            System.out.println("2) Listar producto");
            System.out.println("3) Eliminar producto");
            System.out.println("4) Actualizar stock");
            System.out.println("5) VOLVER");

            opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    try {
                        System.out.println("Ingrese nombre del producto");
                        String nombre = sc.nextLine();

                        System.out.println("Ingrese precio");
                        float precio = sc.nextFloat();
                        sc.nextLine();

                        System.out.println("Ingrese stock");
                        int stock = sc.nextInt();
                        sc.nextLine();

                        controladorProducto.crearProducto(nombre, precio, stock);

                        System.out.println("Producto creado correctamente.");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        List<Producto> lista = controladorProducto.listarProductos();

                        for (Producto producto : lista) {
                            System.out.println(producto);
                        }
                    } catch (Exception e) {
                        System.out.println("No hay productos cargados");
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Ingrese id del producto:");
                        int id = sc.nextInt();
                        sc.nextLine();

                        controladorProducto.eliminarProducto(id);
                        System.out.println("Producto eliminado.");
                    } catch (Exception e) {
                        System.out.println("No existe un producto con ese id");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Ingrese id del producto:");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.println("Ingrese nuevo stock:");
                        int nuevoStock = sc.nextInt();
                        sc.nextLine();

                        controladorProducto.actualizarStock(id, nuevoStock);
                        System.out.println("Stock actualizado.");
                    } catch (Exception e) {
                        System.out.println("No existe el producto");
                    }
                    break;
                case 5:
                    System.out.println("Saliendo...");
            }
        } while (opcion != 5);
    }
}
