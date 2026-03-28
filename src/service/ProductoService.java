package service;

import Exceptions.IdInvalidoException;
import Exceptions.ProductoNullException;
import java.util.List;
import model.Producto;
import repository.ProductoRepositorySQLite;

public class ProductoService {
    
    private ProductoRepositorySQLite repo;

    public ProductoService(ProductoRepositorySQLite repo){
        this.repo = new ProductoRepositorySQLite();
    }

//-----------------------------------------------------------------------------------------------//
public Producto guardarProducto(Producto producto)throws ProductoNullException{

    if(producto == null){
        throw new ProductoNullException("Error: No se puedo realizar el guardado");
    }
    repo.guardarProducto(producto);
    return producto;
}
//-----------------------------------------------------------------------------------------------//
public List<Producto> listarProductos(){
    return repo.obtenerProductos();
}
//-----------------------------------------------------------------------------------------------//
public Producto buscarProducto(int id)throws IdInvalidoException, ProductoNullException{
    
    if(id <= 0){
        throw new IdInvalidoException("Error: El id no puede ser menor/igual a cero");
    }

    Producto producto = repo.buscarProducto(id);

    if(producto == null){
        throw new ProductoNullException("Error: No se encontro el producto con id: " + id);
    }

    return producto;
}
//-----------------------------------------------------------------------------------------------//
public void actualizarStock(int id, int nuevoStock) throws IdInvalidoException{

    if(id <= 0){
        throw new IdInvalidoException("Error: El id no puede ser menor/igual a cero");
    }

    if(nuevoStock < 0){
        throw new IllegalArgumentException("Error: El stock no puede ser menor a cero");
    }

    repo.updateStock(id, nuevoStock);
}
//-----------------------------------------------------------------------------------------------//
public void eliminarProducto(int id) throws IdInvalidoException{

    if(id <= 0){
        throw new IdInvalidoException("Error: El id no puede ser menor/igual a cero");
    }

    repo.deleteProducto(id);
}
}
