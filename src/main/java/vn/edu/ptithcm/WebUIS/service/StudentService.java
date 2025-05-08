package vn.edu.ptithcm.WebUIS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.repository.SemesterRepository;
import vn.edu.ptithcm.WebUIS.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SemesterRepository semesterRepository;

    public StudentService(StudentRepository studentRepository, SemesterRepository semesterRepository) {
        this.studentRepository = studentRepository;
        this.semesterRepository = semesterRepository;
    }

    public Student getStudent(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public List<Semester> getAllSemester() {
        return semesterRepository.findAllSemester();
    }
}