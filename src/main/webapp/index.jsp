<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Inicio Televiajero</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="sesioninicio.css" media="screen" title="no title" charset="utf-8">
    <link rel="icon" href=
            logo.png
          type="image/x-icon">



</head>
<body>
    <div class="container">
        <img class="logo" src="logo.png" width="300" height="300" >
        <p class="bienvenida"><b>Bienvenido TeleViajero</b></p>
        <main class="form-signin">
            <form method="post" action="<%=request.getContextPath()%>/LoginServlet">
                <div class="mb-3">
                    <label for="exampleInputEmail1" style="color: white">Email</label>
                    <input type="email"  class="form-control" id="exampleInputEmail1" name="username" aria-describedby="emailHelp" placeholder="name@example.com">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" style="color: white">Contraseña</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Contraseña">
                </div>
                <% if (request.getParameter("error") != null) { %>
                <div class="text-danger mb-2">Error en usuario o contraseña <br> ...O ¿tal vez tu especialdad no es "ingenieria de telecomunicaciones"? </div>
                <%}%>
                <div class="boton"><button type="submit" class="btn btn-outline-primary">Ingresar</button></div>

            </form>


        <!-- Button trigger modal -->
        <div class="d-flex justify-content-center" STYLE="margin-top: 15px"><button type="button" class="btn btn-link" STYLE="background-color: white">
            <a href="<%=request.getContextPath()%>/RegistroServlet">Soy nuevo y quiero registrarme</a>
        </button></div>


    </div>
</body>
</html>