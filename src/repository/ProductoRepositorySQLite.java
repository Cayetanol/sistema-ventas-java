package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.ConexionSQLite;
import model.Producto;

public class ProductoRepositorySQLite {

    public void guardarProducto(Producto producto) {

        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionSQLite.conectar(); PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getStock());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//-----------------------------------------------------------------------------------------------//

    public List<Producto> obtenerProductos() {

        String sql = "SELECT * FROM productos";

        List<Producto> listarProductos = new ArrayList<>();

        try (Connection conexion = ConexionSQLite.conectar(); PreparedStatement statement = conexion.prepareStatement(sql)) {

            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("Id");
                String nombre = resultado.getString("nombre");
                double precio = resultado.getDouble("precio");
                int stock = resultado.getInt("stock");

                Producto producto = new Producto(nombre, precio, stock);
                producto.setId(id);

                listarProductos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listarProductos;
    }
//-----------------------------------------------------------------------------------------------//

    public void deleteProducto(int id) {

        String sql = "DELETE FROM productos WHERE Id= ?";

        try (Connection conexion = ConexionSQLite.conectar(); PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//-----------------------------------------------------------------------------------------------//

    public void updateStock(int id, int nuevoStock) {

        String sql = "UPDATE productos SET stock= ? WHERE Id= ?";

        try (Connection conexion = ConexionSQLite.conectar(); PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, nuevoStock);
            statement.setInt(2, id);

            int filas = statement.executeUpdate();

            if (filas == 0) {
                System.out.println("No se encontro el producto con id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//-----------------------------------------------------------------------------------------------//

    public Producto buscarProducto(int id) {

        String sql = "SELECT * FROM productos WHERE Id = ?";

        try (Connection conexion = ConexionSQLite.conectar(); PreparedStatement statement = conexion.prepareStatement(sql)) {
            
            statement.setInt(1, id);

            ResultSet resultado = statement.executeQuery();

            if (resultado.next()) {
                int idDB = resultado.getInt("Id");
                String nombre = resultado.getString("nombre");
                double precio = resultado.getDouble("precio");
                int stock = resultado.getInt("stock");

                Producto producto = new Producto(nombre, precio, stock);
                producto.setId(idDB);

                return producto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
