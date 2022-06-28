<%--
  Created by IntelliJ IDEA.
  User: Angel
  Date: 0027, 27 de junio del 2022
  Time: 07:58p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <meta name='viewport' content='width=device-width, initial-scale=1'>
  <link href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' rel='stylesheet'>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
  <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css' rel='stylesheet'>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Registro de Usuario</title>
</head>
<body STYLE="background-image:url(fondo.jpg);-webkit-background-size: cover;-moz-background-size: cover;-o-background-size: cover; background-size: cover;backdrop-filter:blur(5px);background-repeat: no-repeat;background-attachment: fixed;">
<section class="vh-100" >
  <div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-xl-9">

        <h1 class="text-white mb-4"><p STYLE="color: black">Registro de usuario</p></h1>

        <div class="card" style="border-radius: 15px;">
          <div class="card-body">

            <form class="form-login" style="width: 80%; margin-left: 10%; margin-top: 3%;" method="POST" action="<%=request.getContextPath()%>/RegistroServlet">

            <div class="row align-items-center pt-4 pb-3">
              <div class="col-md-3 ps-5">
                <h6 class="mb-0">Nombres: (*)</h6>

              </div>
              <div class="col-md-9 pe-5">
                <label for="nombre" class="form-label"></label>
                <input type="text" name="nombre" id="nombre" class="form-control form-control-lg" placeholder="Nombre" required="required" />

              </div>
            </div>

            <hr class="mx-n3">

            <div class="row align-items-center py-3">
              <div class="col-md-3 ps-5">

                <h6 class="mb-0">Apellidos: (*)</h6>

              </div>
              <div class="col-md-9 pe-5">
                <label for="apellido" class="form-label"></label>
                <input type="text" name="apellido" id="apellido" class="form-control form-control-lg" placeholder="Apellido" required="required"/>

              </div>
            </div>

            <hr class="mx-n3">

            <div class="row align-items-center py-3">
              <div class="col-md-3 ps-5">

                <h6 class="mb-0">Edad: (*)</h6>

              </div>
              <div class="col-md-9 pe-5">
                <label for="edad" class="form-label"></label>
                <input type="number" name="edad" id="edad" class="form-control form-control-lg" placeholder="edad" required="required"/>

              </div>
            </div>

            <hr class="mx-n3">

            <div class="row align-items-center py-3">
              <div class="col-md-3 ps-5">

                <h6 class="mb-0">Código PUCP: (*)</h6>

              </div>
              <div class="col-md-9 pe-5">
                <label for="codigo" class="form-label" ></label>
                <input type="number" name="codigo" id="codigo" class="form-control form-control-lg" maxlength="8" minlength="8" pattern="[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" placeholder="Código" required="required"/>

              </div>
            </div>

            <hr class="mx-n3">

            <div class="row align-items-center py-3">
              <div class="col-md-3 ps-5">

                <h6 class="mb-0">Correo PUCP: (*)</h6>

              </div>
              <div class="col-md-9 pe-5">
                <label for="correo" class="form-label" ></label>
                <input type="email" name="correo" id="correo" class="form-control form-control-lg" placeholder="mi.correo@pucp.edu.pe" required="required"/>

              </div>
            </div>

            <hr class="mx-n3">

            <div class="row align-items-center py-3">
              <div class="col-md-3 ps-5">

                <h6 class="mb-0">Especialidad: (*)</h6>

              </div>
              <div class="col-md-9 pe-5">
                <label for="especialidad" class="form-label" ></label>
                <input type="text" name="especialidad" id="especialidad" class="form-control form-control-lg" placeholder="Especialidad" required="required"/>

              </div>
            </div>


            <hr class="mx-n3">

            <div class="row align-items-center py-3">
              <div class="col-md-3 ps-5">

                <h6 class="mb-0">Contraseña: (*)</h6>

              </div>
              <div class="col-md-9 pe-5">
                <label for="contrasenha" class="form-label" ></label>
                <input type="password" name="contrasenha" id="contrasenha" class="form-control form-control-lg" placeholder="Contraseña" required="required"/>

              </div>
            </div>


            <hr class="mx-n3">

            <div class="row align-items-center py-3">
              <div class="col-md-3 ps-5">

                <h6 class="mb-0">Confirme la contraseña: (*)</h6>

              </div>
              <div class="col-md-9 pe-5">
                <label for="confirm_contrasenha" class="form-label" ></label>
                <input type="password" name="correo" id="confirm_contrasenha" class="form-control form-control-lg" placeholder="Confirme la Contraseña" required="required"/>

              </div>
            </div>



            <hr class="mx-n3">

            <div class="px-5 py-4">
              <button type="submit" class="btn btn-primary btn-lg">Registrarse</button>
            </div>
            </form>
          </div>
        </div>

      </div>
    </div>
  </div>
</section>

</body>
</html>
