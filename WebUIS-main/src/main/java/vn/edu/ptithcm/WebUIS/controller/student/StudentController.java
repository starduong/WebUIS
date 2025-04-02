package vn.edu.ptithcm.WebUIS.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.ptithcm.WebUIS.model.entity.Student;
import vn.edu.ptithcm.WebUIS.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public String getStudentIndexPage() {
        return "student/index";
    }

    @GetMapping("/profile/{studentId}")
    public String getStudentProfilePage(ModelMap model, @PathVariable("studentId") String studentId) {
        Student student = studentService.getStudent(studentId);
        model.addAttribute("student", student);
        return "student/profile";
    }

}
