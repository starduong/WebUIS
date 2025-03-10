package vn.edu.ptithcm.WebUIS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

}
