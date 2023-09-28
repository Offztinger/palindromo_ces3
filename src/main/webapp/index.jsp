<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>

<head>
    <!-- Basic -->
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <!-- Site Metas -->
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>Spering</title>

    <!-- bootstrap core css -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>

    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/customStyles.css" rel="stylesheet"/>
    <!-- responsive style -->
    <link href="css/responsive.css" rel="stylesheet"/>
</head>

<body>
<div class="hero_area">
    <!-- header section strats -->
    <header class="header_section">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg custom_nav-container">
                <a class="navbar-brand" href="index.html">
                    <img src="images/logo.png" alt=""/>
                    <span>
              Spering
            </span>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav  ">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"> About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Work </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"> Category </a>
                        </li>
                    </ul>
                    <div class="user_option">
                        <a href="">
                <span>
                  Login
                </span>
                        </a>
                        <form class="form-inline my-2 my-lg-0 ml-0 ml-lg-4 mb-3 mb-lg-0">
                            <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit"></button>
                        </form>
                    </div>
                </div>
                <div>
                    <div class="custom_menu-btn ">
                        <button>
                <span class=" s-1">

                </span>
                            <span class="s-2">

                </span>
                            <span class="s-3">

                </span>
                        </button>
                    </div>
                </div>

            </nav>
        </div>
    </header>
    <!-- end header section -->
    <!-- slider section -->
    <section class="slider_section">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active">01</li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1">02</li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2">03</li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-5 offset-md-1">
                                <div class="detail-box">
                                    <h1>
                                        Ejercicio <br>
                                        Palindromo
                                    </h1>
                                    <p>
                                        Debe digitar una palabra en el campo de tipo texto.
                                    </p>

                                    <div class="input-container">
                                        <form class="d-flex justify-content-center">
                                            <input name="palabra" placeholder="Ingrese una palabra" value=""
                                                   class="input-group"/>
                                            <button style="height: 100%" class="btn-outline-primary" type="submit">
                                                Validar
                                            </button>
                                        </form>
                                    </div>


                                    <%!

                                        String palabra = "";
                                        String[] palabras = new String[50];
                                        static String error = "";
                                        static String[] palindromos = new String[50];
                                        static int count = 0;

                                        public static String[] agregarPalabra(String[] palabras, String palabra) {
                                            if(!palabra.isEmpty()){
                                                boolean esPalindromo = esPalindromo(palabra);

                                                for (int i = 0; i < palabras.length; i++) {
                                                    if (palabras[i] != null && palabras[i].equals(palabra)) {
                                                        error = "Has ingresado una palabra existente";
                                                        return palabras;
                                                    } else {
                                                        error = "";
                                                    }
                                                }

                                                palabras[count] = palabra.toLowerCase().replaceAll(" ", "");
                                                if (esPalindromo) {
                                                    palindromos[count] = "Sí";
                                                } else {
                                                    palindromos[count] = "No";
                                                }
                                                count++;
                                            }

                                            return palabras;
                                        }

                                        public static boolean esPalindromo(String palabra) {
                                            if (palabra != null) {
                                                // Convertir la palabra a minúsculas
                                                palabra = palabra.toLowerCase();

                                                // Eliminar los espacios en blanco
                                                palabra = palabra.replaceAll(" ", "");

                                                // Invertir la palabra
                                                String palabraInvertida = new StringBuilder(palabra).reverse().toString();

                                                // Comparar la palabra original con la palabra invertida
                                                return palabra.equals(palabraInvertida);
                                            }
                                            return false;
                                        }
                                    %>
                                    <%
                                        palabra = request.getParameter("palabra");
                                        if (palabra != null) {
                                            palabras = agregarPalabra(palabras, palabra);
                                        }
                                    %>
                                    <p style="color: red; font-weight: bold"><%= error%></p>
                                    <div class="mt-5">
                                        <h1 style="font-size: 18px">Listado de palabras ingresadas</h1>
                                            <table style="background: #ffffff">
                                                <thead>
                                                <tr>
                                                    <th>Palabra</th>
                                                    <th>¿Es palindromo?</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <%
                                                    for (int i = 0; i <= 49; i += 1) {
                                                        if (palabras[i] != null && palindromos[i] != null) {
                                                %>
                                                <tr>
                                                    <td><%= palabras[i] %>
                                                    </td>
                                                    <td><%=palindromos[i]%>
                                                    </td>
                                                </tr>
                                                <%
                                                        }
                                                    }
                                                %>
                                                </tbody>

                                            </table>
                                    </div>
                                </div>
                            </div>
                            <div class="offset-md-1 col-md-4 img-container">
                                <div class="img-box">
                                    <img src="images/slider-img.png" alt="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/custom.js"></script>


</body>
</body>

</html>