package model;

public class ItemVenta {
    
    private int id;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private float subtotal;


    public ItemVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio(); // Asignar el precio unitario desde el producto
    }
//-----------------------------------------------------------------------------------------------//

public Producto getProducto(){
    return producto;
}

public int getCantidad(){
    return cantidad;
}

public float getSubtotal(){
    return subtotal;
}

public double getPrecioUnitario(){
    return precioUnitario;
}

public void setPrecioUnitario(double precio_unitario){
    this.precioUnitario = precio_unitario;
}
//-----------------------------------------------------------------------------------------------//
//Aumentar cantidad item
public void aumentarCantidad(int cantidad){

    if(cantidad <= 0){
        throw new IllegalArgumentException("");
    }

    this.cantidad += cantidad;
}

}
