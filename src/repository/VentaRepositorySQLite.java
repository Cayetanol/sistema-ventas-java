package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.ConexionSQLite;
import model.ItemVenta;
import model.Producto;
import model.Venta;

public class VentaRepositorySQLite extends VentaRepositorio {

    private List<ItemVenta> items;

    public void guardarVenta(Venta venta) {

        String sqlVenta = "INSERT INTO ventas (fecha, total) VALUES (?, ?)";
        String sqlItem = "INSERT INTO item_venta (venta_Id, producto_Id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        Connection conexion = null;

        try {
            conexion = ConexionSQLite.conectar();
            conexion.setAutoCommit(false);

            PreparedStatement statement = conexion.prepareStatement(sqlVenta, java.sql.Statement.RETURN_GENERATED_KEYS);

            statement.setDate(1, java.sql.Date.valueOf(venta.getFecha()));
            statement.setDouble(2, venta.getTotal());

            statement.executeUpdate();

            ResultSet resultado = statement.getGeneratedKeys();
            if (resultado.next()) {
                int idGenerado = resultado.getInt(1);
                venta.setId(idGenerado);
            }

            PreparedStatement statement2 = conexion.prepareStatement(sqlItem);

            for (ItemVenta item : venta.getItem()) {

                statement2.setInt(1, venta.getId());
                statement2.setInt(2, item.getProducto().getId());
                statement2.setInt(3, item.getCantidad());
                statement2.setDouble(4, item.getPrecioUnitario());

                statement2.executeUpdate();
            }

            conexion.commit();

        } catch (Exception e) {

            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
//-----------------------------------------------------------------------------------------------//

//-----------------------------------------------------------------------------------------------//
    public List<Venta> listarVentas() {

        String sql = "SELECT v.Id AS venta_Id, v.fecha, v.total, "
                + "i.producto_Id, i.cantidad, i.precio_unitario, "
                + "p.nombre, p.precio, p.stock "
                + "FROM ventas v "
                + "JOIN item_venta i ON v.Id = i.venta_Id "
                + "JOIN productos p ON i.producto_Id = p.Id";

        Map<Integer, Venta> ventasMap = new HashMap<>();

        try (Connection conexion = ConexionSQLite.conectar(); PreparedStatement statement = conexion.prepareStatement(sql); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {

                int idVenta = resultado.getInt("venta_Id");

                Venta venta;

                if (ventasMap.containsKey(idVenta)) {
                    venta = ventasMap.get(idVenta);
                } else {
                    venta = new Venta();
                    venta.setId(idVenta);
                    long timestamp = resultado.getLong("fecha");
                    venta.setFecha(
                            java.time.Instant.ofEpochMilli(timestamp)
                                    .atZone(java.time.ZoneId.systemDefault())
                                    .toLocalDate()
                    );
                    venta.setTotal(resultado.getDouble("total"));
                    ventasMap.put(idVenta, venta);
                }

                Producto producto = new Producto(
                        resultado.getString("nombre"),
                        resultado.getDouble("precio"),
                        resultado.getInt("stock")
                );
                producto.setId(resultado.getInt("producto_Id"));

                ItemVenta item = new ItemVenta(producto, resultado.getInt("cantidad"));
                item.setPrecioUnitario(resultado.getDouble("precio_unitario"));

                venta.agregarItem(item);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar ventas: " + e.getMessage());
        }

        return new ArrayList<>(ventasMap.values());
    }
}
