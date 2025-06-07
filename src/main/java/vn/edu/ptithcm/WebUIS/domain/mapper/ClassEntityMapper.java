package vn.edu.ptithcm.WebUIS.domain.mapper;

import org.springframework.stereotype.Component;

import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.response.ClassEntityResponse;

@Component
public class ClassEntityMapper {

    public ClassEntityResponse convertToClassEntityResponse(ClassEntity classEntity) {
        return new ClassEntityResponse(
                classEntity.getClassId(),
                classEntity.getMajor(),
                classEntity.getEducationLevel(),
                classEntity.getAcademicYear(),
                classEntity.getStudents().size());
    }
}
