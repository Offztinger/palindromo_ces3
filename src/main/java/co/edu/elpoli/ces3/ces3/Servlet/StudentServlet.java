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

    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
