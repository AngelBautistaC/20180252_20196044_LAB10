package com.example._20180252_20196044_lab10.Daos;

import com.example._20180252_20196044_lab10.Beans.Usuario;

import java.sql.*;

public class MainDao extends DaoBase{

    //Para el Login servlet

    public Usuario validarUsuarioPassword(String username, String password) {
        Usuario usuario = null;

        String sql = "select idusuario, nombre, apellido, edad, correo, especialidad, gasto,password" +
                " from usuario where correo = ? and password = sha2(?,256);";

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
                    usuario.setPassword(rs.getString(8));


                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuario;
    }

    //Para el registro servlet

    public void crearUsuario(String codigo, String nombre, String apellido, String correo,
                             String especialidad, String edad, String contrasenha) {


        String sql = "insert into usuario(idusuario, nombre, apellido, edad, correo, especialidad, password, gasto) values (?,?,?,?,?,?,sha2(?,256),0);";

        try (Connection connection = this.getConection();
             PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setInt(1, Integer.parseInt(codigo));
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setInt(4, Integer.parseInt(edad));
            pstmt.setString(5, correo);
            pstmt.setString(6, especialidad);
            pstmt.setString(7, contrasenha);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
