package co.edu.elpoli.ces3.ces3.Servlet;

import co.edu.elpoli.ces3.ces3.Modem.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "studentServlet", value = "/student")

public class StudentServlet extends MyServlet {
    private String message;
    private GsonBuilder gsonBuilder;
    private Gson gson;
    private ArrayList<Student> students;
    public void init() {

        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        students = new ArrayList<>();
        Student student1 = new Student();
        student1.id = 10;
        student1.setNombre("Alejo");
        student1.setCedula("1013336354");

        Student student2 = new Student();
        student2.id = 11;
        student2.setNombre("Omar");
        student2.setCedula("98557333");

        students.add(student1);
        students.add(student2);

        for (int i = 0; i < students.size(); i = i+1){
            System.out.println(students.get(i));
        }

        message = "Hello student.";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        //getStudentByParam
        String studentId = request.getParameter("studentId");
        PrintWriter out = response.getWriter();
        //Si param viene vacio...
        if (studentId == null){
            out.println(gson.toJson(students));
            //Si param viene lleno...
        } else {
            Student studentSearch = null;
            //For mejorado realizado en clase
            for (Student s: students){
                if (s.getId() == Integer.parseInt(studentId)){
                    studentSearch = s;
                    break;
                }
            }
            out.println(gson.toJson(studentSearch));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServletException {
        resp.setContentType("application/json");
        ServletOutputStream out = resp.getOutputStream();
        JsonObject body = this.getParamsFromPost(req);

        Student std = new Student(
                body.get("id").getAsInt(),
                body.get("cedula").getAsString(),
                body.get("nombre").getAsString()
        );

        this.students.add(std);
        out.println(gson.toJson(std));
        out.println("\n POST SUCCESSFUL");
        out.flush();

    }
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(request);

        int studentId = body.get("id").getAsInt();

        Student studentToUpdate = null;
        for (Student student : students) {
            if (student.getId() == studentId) {
                studentToUpdate = student;
                break;
            }
        }

        if (studentToUpdate != null) {
            // Actualiza los campos del estudiante
            studentToUpdate.setNombre(body.get("nombre").getAsString());
            studentToUpdate.setCedula(body.get("cedula").getAsString());

            PrintWriter out = response.getWriter();
            out.println(gson.toJson(studentToUpdate));
            out.println("\n PUT SUCCESSFUL");
            out.flush();
            System.out.println("El estudiante con el id #" + studentId + " ha sido actualizado");

        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter out = response.getWriter();
            out.println("\n No se ha encontrado un estudiante con el id #"+studentId);
            out.flush();
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        String studentId = request.getParameter("studentId");
        if (studentId != null) {
            int idToDelete = Integer.parseInt(studentId);
            Student studentToDelete = null;

            for (Student student : students) {
                if (student.getId() == idToDelete) {
                    studentToDelete = student;
                    break;
                }
            }

            if (studentToDelete != null) {
                students.remove(studentToDelete);
                PrintWriter out = response.getWriter();
                out.println("\n El estudiante con el id #" + idToDelete + " ha sido eliminado.");
                out.flush();
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                PrintWriter out = response.getWriter();
                out.println("No se ha encontrado un estudiante con el id #"+idToDelete);
                out.flush();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            PrintWriter out = response.getWriter();
            out.println("\n Debe ingresar un id de estudiante por parametros.");
            out.flush();
        }
        System.out.println("El estudiante con el id #" + studentId + " ha sido eliminado");
    }
}
