package com.example._20180252_20196044_lab10.Servlets;

import com.example._20180252_20196044_lab10.Beans.Compra;
import com.example._20180252_20196044_lab10.Beans.Viaje;
import com.example._20180252_20196044_lab10.Daos.MainDao;
import com.example._20180252_20196044_lab10.Daos.MenuDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MenuServlet", value = "/MenuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");
        MenuDao menuDao = new MenuDao();

        switch (action) {
            case "listar" -> {
                int idUsuario = 12345678;
                ArrayList<Compra> listaMenu = menuDao.obtenerListaMenu(idUsuario);

                request.setAttribute("listaMenu", listaMenu);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("menuInicio.jsp");
                requestDispatcher.forward(request,response);
            }
            case "listarViajes" -> {
                ArrayList<Viaje> listaViajesDisp = menuDao.obtenerListaViajesDisponibles();

                request.setAttribute("listaViajesDisp", listaViajesDisp);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("listaViajes.jsp");
                requestDispatcher.forward(request, response);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
