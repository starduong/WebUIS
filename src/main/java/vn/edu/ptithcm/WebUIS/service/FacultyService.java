package vn.edu.ptithcm.WebUIS.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Department;
import vn.edu.ptithcm.WebUIS.repository.DepartmentRepository;

@Service
@RequiredArgsConstructor
public class FacultyService {
    private final DepartmentRepository departmentRepository;

    public Department getDepartmentById(String id) {
        return departmentRepository.findById(id).orElse(null);
    }

    // kiểm tra lớp có thuộc khoa không
    public boolean isClassOfFaculty(String classId, String facultyId) {
        Department faculty = getDepartmentById(facultyId);
        return faculty.getClasses().stream()
                .anyMatch(classEntity -> classEntity.getClassId().equals(classId));
    }

}
