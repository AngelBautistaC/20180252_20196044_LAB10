package com.example._20180252_20196044_lab10.Daos;

import com.example._20180252_20196044_lab10.Beans.Viaje;

import java.sql.*;
import java.util.ArrayList;

public class MenuDao extends DaoBase{

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

        return listaViajesdisp;}
}
