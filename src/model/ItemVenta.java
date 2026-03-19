package model;

public class ItemVenta {
    
    private Producto producto;
    private int cantidad;
    private float subtotal;


    public ItemVenta(Producto producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
    }


    //Gett
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

//-----------------------------------------------------------------------------------------------//
//Aumentar cantidad item
public void aumentarCantidad(int cantidad){

    if(cantidad <= 0){
        throw new IllegalArgumentException("");
    }

    this.cantidad += cantidad;
}

}
