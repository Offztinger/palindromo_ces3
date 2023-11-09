package co.edu.elpoli.ces3.ces3.Servlet;

import co.edu.elpoli.ces3.ces3.Modem.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "studentServlet", value = "/student")

public class StudentServlet extends MyServlet {
    private String message;
    private ArrayList<Student> students;

    public void init() {

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
            out.println(students);
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
            out.println(studentSearch);
        }
    }

}
