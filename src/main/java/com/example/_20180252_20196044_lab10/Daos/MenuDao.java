package com.example._20180252_20196044_lab10.Daos;

import com.example._20180252_20196044_lab10.Beans.Compra;
import com.example._20180252_20196044_lab10.Beans.Viaje;

import java.sql.*;
import java.util.ArrayList;

public class MenuDao extends DaoBase {

    public java.util.ArrayList<Viaje> obtenerListaViajesDisponibles() {
        ArrayList<Viaje> listaViajesdisp = new ArrayList<>();


        try (Connection connection = this.getConection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select * from viaje;");) {

            while (rs.next()) {
                Viaje viaje = new Viaje();
                viaje.setIdViaje(rs.getInt(1));
                viaje.setFechaViaje(rs.getDate(2));
                viaje.setCostounit(rs.getFloat(3));
                viaje.setOrigen(rs.getString(4));
                viaje.setDestino(rs.getString(5));
                listaViajesdisp.add(viaje);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaViajesdisp;
    }

    public java.util.ArrayList<Compra> obtenerListaMenu(int idUsuario) {
        ArrayList<Compra> listaMenu = new ArrayList<>();


        String sql = "select c.idcompra, c.gasto_total, c.num_tickets, c.fecha_reserva, s.nombre AS 'Seguro', v.fecha_viaje, v.origen, v.destino, v.idviaje, v.costo_unit\n" +
                "from compra c\n" +
                "inner join seguro s on c.seguro_idseguro = s.idseguro\n" +
                "inner join viaje v on c.viaje_idviaje = v.idviaje\n" +
                "where c.usuario_idusuario = ?;";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setInt(1, idUsuario);

            try (ResultSet rs = pstmt.executeQuery();) {

                while (rs.next()) {
                    Compra compra = new Compra();
                    compra.setIdCompra(rs.getInt(1));
                    compra.setGastototal(rs.getFloat(2));
                    compra.setNumtickets(rs.getInt(3));
                    compra.setFechaReserva(rs.getDate(4));
                    compra.setSeguro(rs.getString(5));
                    Viaje viaje = new Viaje();
                    viaje.setFechaViaje(rs.getDate(6));
                    viaje.setOrigen(rs.getString(7));
                    viaje.setDestino(rs.getString(8));
                    viaje.setIdViaje(rs.getInt(9));
                    viaje.setCostounit(rs.getFloat(10));
                    compra.setViaje(viaje);
                    listaMenu.add(compra);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaMenu;
    }

    public java.util.ArrayList<String> obtenerSeguros() {
        ArrayList<String> listaSeguros = new ArrayList<>();

        try (Connection connection = this.getConection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select nombre from seguro;");) {

            while (rs.next()) {
                String seguro = rs.getString(1);
                listaSeguros.add(seguro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaSeguros;
    }


    public void actualizarcompra (Compra compra){

        String sql = "UPDATE compra set num_tickets=?,gasto_total=? where idcompra=?;";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {


            pstmt.setInt(1, compra.getNumtickets());
            pstmt.setFloat(2, compra.getGastototal());
            pstmt.setInt(3, compra.getIdCompra());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void eliminarViaje (int idcompra){

        String sql = "delete from compra where idcompra=?;";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setInt(1, idcompra);


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }










}
