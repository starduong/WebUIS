package vn.edu.ptithcm.WebUIS.controller.department;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.response.student.StudentResponse;
import vn.edu.ptithcm.WebUIS.service.StudentService;

@RestController
@RequestMapping("/api/v1/department/students")
@RequiredArgsConstructor
public class StudentMgrController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable String studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }
}
