package repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Venta;

public class VentaRepositorio {

    private int siguienteId = 1;
    List<Venta> ventas = new ArrayList<>();

    public void guardarVenta(Venta venta) throws SQLException{
        venta.setId(siguienteId);
        siguienteId++;
        ventas.add(venta);
    }

    public List<Venta> listarVentas(){
        return ventas;
    }

    public Venta buscarVenta(int id){

        for (Venta venta : ventas) {
            if(venta.getId() == id){
                return venta;
            }
        }
        return null;
    }
}
