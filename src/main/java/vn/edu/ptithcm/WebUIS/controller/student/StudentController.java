package vn.edu.ptithcm.WebUIS.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("")
    public String getStudentIndexPage() {
        return "student/index";
    }

    @GetMapping("/profile")
    public String getStudentProfilePage() {
        return "student/profile";
    }

}
