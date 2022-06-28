package com.example._20180252_20196044_lab10.Servlets;

import com.example._20180252_20196044_lab10.Beans.Usuario;
import com.example._20180252_20196044_lab10.Daos.MainDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") != null ? request.getParameter("action") : "login";

        HttpSession session = request.getSession();

        switch (action){
            case "login":
                Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

                if(usuario != null && usuario.getUsuarioId() != 0){
                    response.sendRedirect(request.getContextPath());
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
                }
                break;
            case "logout":
                session.invalidate();
                response.sendRedirect(request.getContextPath());
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        MainDao mainDao = new MainDao();

        Usuario usuario =mainDao.validarUsuarioPassword(username, password);

        if (usuario != null && Objects.equals(usuario.getEspecialidad(), "ingenieria de telecomunicaciones")) { //existe usuario y password
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);
            session.setMaxInactiveInterval(60 * 10);

            response.sendRedirect(request.getContextPath() + "/MenuServlet");

        } else {
            response.sendRedirect(request.getContextPath() + "/LoginServlet?error");
        }

    }
}
