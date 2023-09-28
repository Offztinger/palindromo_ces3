package co.edu.elpoli.ces3.ces3;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    // Algoritmo para verificar si una palabra es palíndromo
    private boolean esPalindromo(String palabra) {
        // Convertir la palabra a minúsculas
        palabra = palabra.toLowerCase();

        // Eliminar los espacios en blanco
        palabra = palabra.replaceAll(" ", "");

        // Invertir la palabra
        String palabraInvertida = new StringBuilder(palabra).reverse().toString();

        // Comparar la palabra original con la palabra invertida
        return palabra.equals(palabraInvertida);
    }

    public void destroy() {
    }
}