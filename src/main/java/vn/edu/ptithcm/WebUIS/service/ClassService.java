package vn.edu.ptithcm.WebUIS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.Department;
import vn.edu.ptithcm.WebUIS.domain.mapper.ClassEntityMapper;
import vn.edu.ptithcm.WebUIS.domain.response.ClassEntityResponse;
import vn.edu.ptithcm.WebUIS.repository.ClassRepository;

@Service
@RequiredArgsConstructor
public class ClassService {
    private final ClassRepository classRepository;
    private final ClassEntityMapper classEntityMapper;

    public List<ClassEntityResponse> getAllClassesOfFaculty(Department department) {
        List<ClassEntity> classes = new ArrayList<>();
        if (department.getType()) {
            classes = classRepository.findByDepartment(department);
        }
        return classes.stream()
                .map(classEntityMapper::convertToClassEntityResponse)
                .collect(Collectors.toList());
    }
}
