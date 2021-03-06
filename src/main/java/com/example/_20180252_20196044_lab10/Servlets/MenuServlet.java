package com.example._20180252_20196044_lab10.Servlets;

import com.example._20180252_20196044_lab10.Beans.Compra;
import com.example._20180252_20196044_lab10.Beans.Seguro;
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

        HttpSession session = (HttpSession) request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            response.sendRedirect(request.getContextPath());
        }
        else {



            String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");
            MenuDao menuDao = new MenuDao();

            menuDao.actualizarGastoUsuario(usuarioLogueado.getGasto(), usuarioLogueado.getUsuarioId());

            switch (action) {
                case "listar" -> {
                    int idUsuario = usuarioLogueado.getUsuarioId();
                    //System.out.println(idUsuario);
                    ArrayList<Compra> listaMenu = menuDao.obtenerListaMenu(idUsuario);

                    request.setAttribute("listaMenu", listaMenu);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("menuInicio.jsp");
                    requestDispatcher.forward(request,response);
                }
                case "listarViajes" -> {
                    ArrayList<Viaje> listaViajesDisp = menuDao.obtenerListaViajesDisponibles();

                    //lista de seguros disponibles
                    ArrayList<Seguro> listaSeguros = menuDao.obtenerSeguros();

                    request.setAttribute("listaViajesDisp", listaViajesDisp);
                    request.setAttribute("listaSeguros", listaSeguros);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("listaViajes.jsp");
                    requestDispatcher.forward(request, response);
                }

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession();
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            response.sendRedirect(request.getContextPath());
        }
        else {

            String action = request.getParameter("a") == null ? "lista" : request.getParameter("a");
            MenuDao menuDao = new MenuDao();

            menuDao.actualizarGastoUsuario(usuarioLogueado.getGasto(), usuarioLogueado.getUsuarioId());

            switch (action) {
                case "actualizarViaje" -> {
                    Compra bcompra = leerParametrosRequest(request);
                    menuDao.actualizarcompra(bcompra);


                    int cantidad_tickets_nuevos = Integer.parseInt(request.getParameter("conseguirNumeroTickets"));
                    float costo_unitario = Float.parseFloat(request.getParameter("preciounitarioticket"));
                    int cantidad_tickets_anteriores = Integer.parseInt(request.getParameter("ticketsAnteriores"));
                    float gasto_total_anterior = costo_unitario * cantidad_tickets_anteriores;
                    float gasto_total_nuevo = costo_unitario * cantidad_tickets_nuevos;
                    float actualizacionEnGasto = gasto_total_nuevo - gasto_total_anterior;
                    int codigo = Integer.parseInt(request.getParameter("codigopuk"));


                    //menuDao.actualizarCostoGeneral(actualizacionEnGasto,codigo);
                    usuarioLogueado.setGasto(usuarioLogueado.getGasto() - gasto_total_anterior + gasto_total_nuevo);


                    response.sendRedirect(request.getContextPath() + "/MenuServlet");
                }

                case "eliminarViaje" -> {
                    int idcompra = Integer.parseInt(request.getParameter("conseguirIdcompraB"));

                    float costo_unitario = Float.parseFloat(request.getParameter("preciounitarioticketE"));
                    int cantidad_tickets_anteriores = Integer.parseInt(request.getParameter("ticketsAnterioresE"));
                    float gasto_total_anterior = costo_unitario * cantidad_tickets_anteriores;


                    menuDao.eliminarViaje(idcompra);

                    int codigo = Integer.parseInt(request.getParameter("codigopukE"));
                    //menuDao.actualizarCostoGeneralenEliminacion(gasto_total_anterior,codigo);

                    //actualizar el gasto del usuario logueado

                    usuarioLogueado.setGasto(usuarioLogueado.getGasto() - gasto_total_anterior);
                    response.sendRedirect(request.getContextPath() + "/MenuServlet");
                }


                case "crearCompra" -> {
                    int idViaje = Integer.parseInt(request.getParameter("idViaje"));
                    int num_tickets = Integer.parseInt(request.getParameter("num_tickets"));
                    int idSeguro = Integer.parseInt(request.getParameter("seguro"));
                    Float costounit = Float.valueOf(request.getParameter("costounit"));
                    Float gastoTotal = num_tickets * costounit;

                    //Verificar si el usuario ya tiene una compra de ese viaje
                    if (menuDao.obtenerCompraxViajexUsuario(usuarioLogueado.getUsuarioId(), idViaje) == 0) {

                        menuDao.crearCompra(usuarioLogueado.getUsuarioId(), idViaje, num_tickets, idSeguro, gastoTotal);
                        //Actualizar el gasto del usuario
                        //menuDao.actualizarGastoUsuario(gastoTotal, usuarioLogueado.getGasto(), usuarioLogueado.getUsuarioId());
                        Float cont = usuarioLogueado.getGasto() + gastoTotal;
                        usuarioLogueado.setGasto(cont);


                        request.getSession().setAttribute("success","Compra realizada, tambi??n se a??adir?? a Viajes Disponibles :)");
                        response.sendRedirect(request.getContextPath()+"/MenuServlet");
                    } else {
                        request.getSession().setAttribute("err","Error al comprar, usted ya ha comprado antes este viaje");
                        response.sendRedirect(request.getContextPath()+"/MenuServlet?a=listarViajes");
                    }




                }
                case "buscar" -> {
                    String textoBuscar = request.getParameter("textoBuscar");

                    request.setAttribute("textoBuscar", textoBuscar);
                    request.setAttribute("listaMenu", menuDao.buscarPorOrigenDestino(textoBuscar, usuarioLogueado.getUsuarioId()));

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("menuInicio.jsp");
                    requestDispatcher.forward(request, response);
                }


                //SECCION CREA TU PROPIO VIAJE


                case "crearCompraNueva" -> {


                    String nuevoOrigen=request.getParameter("viajeciudadOrigen");
                    String nuevoDestino=request.getParameter("viajeciudadDestino");
                    int nuevoViajeID=Integer.parseInt(request.getParameter("viajeID"));
                    int nuevoViajetickets = Integer.parseInt(request.getParameter("viajeTiquetes"));
                    int nuevoidSeguro = Integer.parseInt(request.getParameter("viajeSeguro"));
                    String nuevoFecha=request.getParameter("viajeFecha");
                    float nuevocostounit=Float.parseFloat(request.getParameter("viajeCostoBoleto"));
                    float nuevogastoTotal=nuevoViajetickets*nuevocostounit;

                    menuDao.nuevoViaje(nuevoViajeID,nuevoFecha,nuevocostounit,nuevoOrigen,nuevoDestino);

                    menuDao.crearCompra(usuarioLogueado.getUsuarioId(), nuevoViajeID, nuevoViajetickets, nuevoidSeguro, nuevogastoTotal);


                    //Actualizar el gasto del usuario

                    float cont = usuarioLogueado.getGasto() + nuevogastoTotal;
                    usuarioLogueado.setGasto(cont);

                    request.getSession().setAttribute("success","Compra realizada, tambi??n se a??adir?? a Viajes Disponibles :)");
                    response.sendRedirect(request.getContextPath()+"/MenuServlet");


                }






            }
        }

    }



    public Compra leerParametrosRequest(HttpServletRequest request) {
        int idcompra= Integer.parseInt(request.getParameter("conseguirIdcompra"));
        int cantidad_tickets=Integer.parseInt(request.getParameter("conseguirNumeroTickets"));
        float costo_unitario=Float.parseFloat(request.getParameter("preciounitarioticket"));
        float gasto_total= costo_unitario*cantidad_tickets;
        Compra bCompra= new Compra();

        bCompra.setIdCompra(idcompra);
        bCompra.setGastototal(gasto_total);
        bCompra.setNumtickets(cantidad_tickets);

        return bCompra;
    }





}
