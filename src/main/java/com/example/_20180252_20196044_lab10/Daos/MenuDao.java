package com.example._20180252_20196044_lab10.Daos;

import com.example._20180252_20196044_lab10.Beans.Compra;
import com.example._20180252_20196044_lab10.Beans.Seguro;
import com.example._20180252_20196044_lab10.Beans.Viaje;

import java.sql.*;
import java.util.ArrayList;

public class MenuDao extends DaoBase {

    public java.util.ArrayList<Viaje> obtenerListaViajesDisponibles() {
        ArrayList<Viaje> listaViajesdisp = new ArrayList<>();


        try (Connection connection = this.getConection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select * from viaje where fecha_viaje > current_date;");) {

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

    public java.util.ArrayList<Seguro> obtenerSeguros() {
        ArrayList<Seguro> listaSeguros = new ArrayList<>();

        try (Connection connection = this.getConection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("select * from seguro;");) {

            while (rs.next()) {
                Seguro seguro = new Seguro();
                seguro.setIdSeguro(rs.getInt(1));
                seguro.setNombreSeguro(rs.getString(2));
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


    public void actualizarCostoGeneral (float costoGeneral,int codigo){

        String sql = "update usuario set gasto=gasto+? where idusuario=?;";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setFloat(1, costoGeneral);
            pstmt.setInt(2, codigo);


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void actualizarCostoGeneralenEliminacion (float costoGeneral,int codigo){

        String sql = "update usuario set gasto=gasto-? where idusuario=?;";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setFloat(1, costoGeneral);
            pstmt.setInt(2, codigo);


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





    public void crearCompra (int idUsuario, int idViaje, int num_tickets, int idSeguro, Float gastoTotal){

        String sql = "INSERT into compra (usuario_idusuario, viaje_idviaje, seguro_idseguro, gasto_total, num_tickets, fecha_reserva)\n" +
                "values(?,?,?,?,?, current_date);";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idViaje);
            pstmt.setInt(3, idSeguro);
            pstmt.setFloat(4, gastoTotal);
            pstmt.setInt(5, num_tickets);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Compra> buscarPorOrigenDestino(String textoBuscar, int idUsuario) {
        ArrayList<Compra> listaMenu = new ArrayList<>();


        String sql = "select c.idcompra, c.gasto_total, c.num_tickets, c.fecha_reserva, s.nombre AS 'Seguro', v.fecha_viaje, v.origen, v.destino, v.idviaje, v.costo_unit\n" +
                "from compra c\n" +
                "inner join seguro s on c.seguro_idseguro = s.idseguro\n" +
                "INNER JOIN viaje v on c.viaje_idviaje = v.idviaje\n" +
                "where lower(v.destino) like ? or lower(v.origen) like ?\n" +
                "AND c.usuario_idusuario= ?";

        try (Connection connection = this.getConection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, "%" + textoBuscar.toLowerCase() + "%");
            preparedStatement.setString(2, "%" + textoBuscar.toLowerCase() + "%");
            preparedStatement.setInt(3,idUsuario);

            try (ResultSet rs = preparedStatement.executeQuery();) {
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

    public void actualizarGastoUsuario (Float gastoCompra, Float gastoUsuario, int idUsuario){
        Float nuevoGasto = Float.sum(gastoCompra,gastoUsuario);

        String sql = "UPDATE usuario set gasto = ? where idusuario = ?;";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setFloat(1, nuevoGasto);
            pstmt.setInt(2, idUsuario);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int obtenerCompraxViajexUsuario (int idUsuario, int idViaje){
        int idCompra = 0;

        String sql = "select idcompra from compra\n" +
                "where usuario_idusuario = ? AND viaje_idviaje = ?";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {


            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idViaje);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    idCompra = rs.getInt(1) + idCompra;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idCompra;
    }
    /*
    public void actualizarGastoUsuarioTOTAL (int idUsuario){
        ArrayList<Float> listaGastosxCompras = new ArrayList<>();

        String sql = "select gasto_total from compra where usuario_idusuario = ?";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setInt(1, idUsuario);

            try (ResultSet rs = pstmt.executeQuery();) {

                while (rs.next()) {
                    listaGastosxCompras.add(rs.getFloat(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //suma de los arreglo
        listaGastosxCompras.get(1);

    }

     */


}
