package vn.edu.ptithcm.WebUIS.controller.lecturer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    @GetMapping("")
    public String getStudentIndexPage() {
        return "lecturer/index";
    }
}
