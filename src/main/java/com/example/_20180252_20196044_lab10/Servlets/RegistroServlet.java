package com.example._20180252_20196044_lab10.Servlets;

import com.example._20180252_20196044_lab10.Daos.MainDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RegistroServlet", value = "/RegistroServlet")
public class RegistroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view =request.getRequestDispatcher("registro.jsp");
        view.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");
        MainDao mainDao =new MainDao();

        String codigo = request.getParameter("codigo");
        String nombre= request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String especialidad = request.getParameter("especialidad");
        String correo = request.getParameter("correo");
        String edad = request.getParameter("edad");
        String contrasenha = request.getParameter("contrasenha");
        String contrasenha_confirmada = request.getParameter("confirm_contrasenha");

        mainDao.crearUsuario(codigo,nombre,apellido,correo,especialidad,edad,contrasenha);

        response.sendRedirect(request.getContextPath() + "/LoginServlet");

    }
}
