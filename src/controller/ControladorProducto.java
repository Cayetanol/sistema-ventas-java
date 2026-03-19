package controller;

import java.util.List;
import model.Producto;
import repository.ProductoRepositorio;

public class ControladorProducto {
    
    private ProductoRepositorio repo;

    public ControladorProducto(ProductoRepositorio repo){
        this.repo = repo;
    }
//-----------------------------------------------------------------------------------------------//
    public Producto crearProducto(String nombre, float precio, int stock){
        if(precio <= 0 || stock < 0){
            throw new IllegalArgumentException("");
        }

        if(nombre == null){
            throw new IllegalArgumentException("");
        }

        if(nombre.isBlank()){
            throw new IllegalArgumentException("");
        }

        Producto producto = new Producto(nombre, precio, stock);
        repo.agregarProducto(producto);

        return producto;
    }
//-----------------------------------------------------------------------------------------------//
    public Producto buscarProducto(int id){
        if(id <= 0){
            throw new IllegalArgumentException("");
        }

        return repo.buscarProducto(id);
    }
//-----------------------------------------------------------------------------------------------//
    public List<Producto> listarProductos(){
        return repo.listarProductos();
    }
//-----------------------------------------------------------------------------------------------//
    public void eliminarProducto(int id){
        if(id <= 0){
            throw new IllegalArgumentException("");
        }

        repo.eliminarProducto(id);
    }
//-----------------------------------------------------------------------------------------------//
    public void actualizarStock(int id, int nuevoStock){

        if(id <= 0 || nuevoStock < 0){
            throw new IllegalArgumentException("");
        }

        repo.actualizarStock(id, nuevoStock);
    }
}
