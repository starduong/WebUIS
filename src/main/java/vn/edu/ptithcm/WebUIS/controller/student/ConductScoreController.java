package vn.edu.ptithcm.WebUIS.controller.student;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.service.StudentService;

@Controller
public class ConductScoreController {

    private final StudentService studentService;

    public ConductScoreController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/conduct-score")
    public String getStudentConductScorePage(Model model) {
        return "student/conduct-score";
    }

    // TODO: Get list of all semesters for dropdown menu in the conduct score page
    @GetMapping("/student/conduct-score-detail")
    public String getStudentConductScoreDetailDetailPage(Model model) {
        List<Semester> semesters = this.studentService.getAllSemester();
        model.addAttribute("semesters", semesters);
        return "student/conduct-score-detail";
    }

}
