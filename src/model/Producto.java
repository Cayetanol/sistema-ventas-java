package model;

public class Producto {
    private int id;
    private double precio;
    private String nombre;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
    this.id = 0;
        this.precio = precio;
        this.nombre = nombre;
        this.stock = stock;
    }

    public double getPrecio() {
        return this.precio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getId(){
        return this.id;
    }

    public int getStock(){
        return this.stock;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    @Override
    public String toString(){
        return "Id: " + getId() + " | Producto: " + getNombre() + " | Precio: " + getPrecio() + " | Stock: " + getStock();
    }

    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Producto other = (Producto) obj;
    return this.id == other.id;
}

@Override
public int hashCode() {
    return Integer.hashCode(id);
}
}
