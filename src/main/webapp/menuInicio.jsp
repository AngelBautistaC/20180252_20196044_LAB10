<%@ page import="com.example._20180252_20196044_lab10.Beans.Compra" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Angel
  Date: 0028, 28 de junio del 2022
  Time: 12:11p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% int a=0;%>
<%
    ArrayList<Compra> listaMenu =  (ArrayList<Compra>) request.getAttribute("listaMenu");
%>
<html>
<head>
    <!-- Required meta 2 tags -->
    <meta charset="utf-8">
    <meta name='viewport' content='width=device-width, initial-scale=1'>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>




    <title>Menu de Inicio</title>

    <nav class="navbar navbar-light"

    <%/*COLOR DORADO(MIEMBRO GOLD)*/ if (a==1){%>
            style="background-color: #DAA520" <% }%>
            <%/*COLOR PLATEADO(MIEMBRO SILVER)*/ if (a==0){%>
         style="background-color: #C0C0C0" <% }%>

            <%/*COLOR AZUL (NORMAL)*/ if (a==3){%>
         style="background-color: #214b9f" <% }%>
            <%/*COLOR NEGRO (MIEMBRO PLATINUM)*/ if (a==4){%>
         style="background-color: #000000" <% }%>
    >

        <h1 class="glow" style="color: white; ">Menu del TeleViajero</h1>
        <p class="my-1 mx-1" STYLE="color: white;font-weight: bold">Bienvenido, Tunombre Tuapellido<br>Status: Platinum </p>
        <div class="nav2">
            <button type="button" class="btn btn-danger"><p class="my-1 mx-1" STYLE="color: white"> Cerrar sesión</p></button>
            <!--<a href="${pageContext.request.contextPath}/index.jsp" class="my-1 mx-1" style="color: white;font-weight: bold;font-size: 17px;font-family: Arial">⠀<i class="bi bi-house-fill"></i> </a> -->

        </div>
    </nav>

    <style>
        .titulo{
            color:white;
            font-weight: bold;
            font-size: 35px;
            margin-top:20px;
            -webkit-text-stroke: 1px black;
        }


    </style>

</head>
<body STYLE="background-image:url(fondo.jpg);-webkit-background-size: cover;-moz-background-size: cover;-o-background-size: cover; background-size: cover;backdrop-filter:blur(5px);background-repeat: no-repeat;background-attachment: fixed;">


<div class="container">


    <div class="d-flex flex-row bd-highlight mb-1">
        <p class="titulo">
            Lista de Viajes Reservados</p>

    </div>
    <a href="<%=request.getContextPath()%>/MenuServlet?a=listarViajes">
        <button type="button" class="btn btn-success" style="margin-top: -15px"><p class="my-1 mx-1" STYLE="color: white">Añadir Viaje</p></button>
    </a>
    <p><br></p>


    <table class="table table-hover table-dark">
        <thead>
        <tr>
            <th scope="col">Id del viaje</th>
            <th scope="col">Fecha de reserva</th>
            <th scope="col">Fecha del viaje</th>
            <th scope="col">Ciudad Origen</th>
            <th scope="col">Ciudad Destino</th>
            <th scope="col">Empresa de seguro</th>
            <th scope="col">Numero de boletos</th>
            <th scope="col">Costo por boleto</th>
            <th scope="col">Costo Total</th>
            <th scope="col">Editar Viaje</th>
            <th scope="col">Eliminar Viaje</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Compra compra:listaMenu) {
        %>
        <tr>

            <td><%= compra.getViaje().getIdViaje()%></td>
            <td><%= compra.getFechaReserva()%></td>
            <td><%= compra.getViaje().getFechaViaje()%></td>
            <td><%= compra.getViaje().getOrigen()%></td>
            <td><%= compra.getViaje().getDestino()%></td>
            <td><%= compra.getSeguro()%></td>
            <td><%= compra.getNumtickets()%></td>
            <td><%= compra.getViaje().getCostounit()%></td>
            <td><%= compra.getGastototal()%></td>
            <td><button class="btn btn-primary">Editar Viaje</button></td>
            <td><button class="btn btn-warning">Eliminar Viaje</button></td>
        </tr>
        <%
            }%>

        </tbody>
    </table>





</div>


</body>
</html>
