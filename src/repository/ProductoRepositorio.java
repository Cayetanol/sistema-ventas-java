package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Producto;

public class ProductoRepositorio {
    
    private HashMap<Integer, Producto> productos = new HashMap<>();

    private int siguienteId = 1;

//-----------------------------------------------------------------------------------------------//
    public void agregarProducto(Producto producto){
        
        if(producto == null){
            throw new IllegalArgumentException("");
        }

        int id = siguienteId;
        siguienteId++;
        producto.setId(id);
        this.productos.put(id, producto);
    }
//-----------------------------------------------------------------------------------------------//
    public Producto buscarProducto(int id){

        Producto producto = productos.get(id);

        if(producto == null){
            throw new IllegalArgumentException("");
        }

        return producto;
    }
//-----------------------------------------------------------------------------------------------//
    public void eliminarProducto(int id){

        if(!productos.containsKey(id)){
            throw new IllegalArgumentException("");
        }

        this.productos.remove(id);
    }
//-----------------------------------------------------------------------------------------------//
    public List<Producto> listarProductos(){

        if(productos.isEmpty()){
            throw new IllegalArgumentException("");
        }

        return new ArrayList<>(productos.values());
    }
//-----------------------------------------------------------------------------------------------//
    public void actualizarStock(int id,int nuevoStock){

        if(nuevoStock < 0){
            throw new IllegalArgumentException("");
        }

        Producto producto = productos.get(id);

        if(producto == null){
            throw new IllegalArgumentException("");
        }

        producto.setStock(nuevoStock);   
    }
}
