package com.example._20180252_20196044_lab10.Daos;

import com.example._20180252_20196044_lab10.Beans.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainDao extends DaoBase{

    public Usuario validarUsuarioPassword(String username, String password) {
        Usuario usuario = null;

        String sql = "select idusuario, nombre, apellido, edad, correo, especialidad, gasto" +
                " from usuario where correo = ? and password = ?;";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setUsuarioId(rs.getInt(1));
                    usuario.setFirstName(rs.getString(2));
                    usuario.setLastName(rs.getString(3));
                    usuario.setEdad(rs.getInt(4));
                    usuario.setEmail(rs.getString(5));
                    usuario.setEspecialidad(rs.getString(6));
                    usuario.setGasto(rs.getFloat(7));


                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }
}
