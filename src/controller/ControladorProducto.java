package controller;

import Exceptions.IdInvalidoException;
import Exceptions.ProductoNullException;
import java.util.List;
import model.Producto;
import service.ProductoService;

public class ControladorProducto {
    
    private ProductoService productoService;

    public ControladorProducto(ProductoService productoService){
        this.productoService = productoService;
    }
//-----------------------------------------------------------------------------------------------//
    public Producto crearProducto(String nombre, float precio, int stock) throws IdInvalidoException, ProductoNullException{
        if(precio <= 0 || stock < 0){
            throw new IllegalArgumentException("Error: El precio no puede ser menor o igual a 0.");
        }

        if(nombre == null){
            throw new IllegalArgumentException("Error: Nombre no puede ser nulo.");
        }

        if(nombre.isBlank()){
            throw new IllegalArgumentException("Error: Nombre no puede ser vacio.");
        }

        Producto producto = new Producto(nombre, precio, stock);
        productoService.guardarProducto(producto);

        return producto;
    }
//-----------------------------------------------------------------------------------------------//
    public Producto buscarProducto(int id) throws IdInvalidoException, ProductoNullException{
        if(id <= 0){
            throw new IdInvalidoException("Error: Producto no encontrado");
        }

        return productoService.buscarProducto(id);
    }
//-----------------------------------------------------------------------------------------------//
    public List<Producto> listarProductos(){
        return productoService.listarProductos();
    }
//-----------------------------------------------------------------------------------------------//
    public void eliminarProducto(int id) throws IdInvalidoException{
        if(id <= 0){
            throw new IdInvalidoException("Error: Producto no encontrado");
        }

        productoService.eliminarProducto(id);
    }
//-----------------------------------------------------------------------------------------------//
    public void actualizarStock(int id, int nuevoStock) throws IdInvalidoException{

        if(id <= 0 || nuevoStock < 0){
            throw new IllegalArgumentException("Error: La cantidad no puede ser menor o igual a cero.");
        }

        productoService.actualizarStock(id, nuevoStock);
    }
}
