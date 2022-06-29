package com.example._20180252_20196044_lab10.Servlets;

import com.example._20180252_20196044_lab10.Beans.Compra;
import com.example._20180252_20196044_lab10.Beans.Usuario;
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

        HttpSession session = (HttpSession) request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        switch (action) {
            case "listar" -> {
                int idUsuario = usuarioLogueado.getUsuarioId();
                ArrayList<Compra> listaMenu = menuDao.obtenerListaMenu(idUsuario);

                request.setAttribute("listaMenu", listaMenu);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("menuInicio.jsp");
                requestDispatcher.forward(request,response);
            }
            case "listarViajes" -> {
                ArrayList<Viaje> listaViajesDisp = menuDao.obtenerListaViajesDisponibles();

                //lista de seguros disponibles
                ArrayList<String> listaSeguros = menuDao.obtenerSeguros();

                request.setAttribute("listaViajesDisp", listaViajesDisp);
                request.setAttribute("listaSeguros", listaSeguros);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("listaViajes.jsp");
                requestDispatcher.forward(request, response);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a") == null ? "lista" : request.getParameter("a");
        MenuDao menuDao = new MenuDao();

        switch (action) {
            case "actualizarViaje" -> {
                Compra bcompra = leerParametrosRequest(request);
                menuDao.actualizarcompra(bcompra);
                response.sendRedirect(request.getContextPath() + "/MenuServlet");
            }

            case "eliminarViaje" -> {
                int idcompra= Integer.parseInt(request.getParameter("conseguirIdcompraB"));
                menuDao.eliminarViaje(idcompra);
                response.sendRedirect(request.getContextPath() + "/MenuServlet");
            }


        }

    }




    public Compra leerParametrosRequest(HttpServletRequest request) {
        int idcompra= Integer.parseInt(request.getParameter("conseguirIdcompra"));
        int cantidad_tickets=Integer.parseInt(request.getParameter("conseguirNumeroTickets"));
        float gasto_total= Float.parseFloat(request.getParameter("conseguirgastoTotal"));
        Compra bCompra= new Compra();

        bCompra.setIdCompra(idcompra);
        bCompra.setGastototal(gasto_total);
        bCompra.setNumtickets(cantidad_tickets);

        return bCompra;
    }





}
