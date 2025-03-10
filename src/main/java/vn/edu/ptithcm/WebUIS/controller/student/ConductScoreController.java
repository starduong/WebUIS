package vn.edu.ptithcm.WebUIS.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConductScoreController {

    @GetMapping("/student/conduct-score")
    public String getStudentConductScorePage() {
        return "student/conduct-score";
    }

}
