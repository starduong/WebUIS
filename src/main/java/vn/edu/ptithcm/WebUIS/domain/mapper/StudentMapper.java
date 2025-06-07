package vn.edu.ptithcm.WebUIS.domain.mapper;

import org.springframework.stereotype.Component;

import vn.edu.ptithcm.WebUIS.domain.entity.AcademicResult;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.response.student.AcademicResultResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.StudentResponse;

@Component
public class StudentMapper {

        public StudentResponse convertStudentToDTO(Student student) {
                return new StudentResponse(
                                student.getStudentId(),
                                student.getLastName(),
                                student.getFirstName(),
                                student.getDateOfBirth(),
                                student.getGender(),
                                student.getPhoneNumber(),
                                student.getCitizenId(),
                                student.getUniversityEmail(),
                                student.getPersonalEmail(),
                                student.getImagePath(),
                                student.getHometown(),
                                student.getAddress(),
                                student.getPermanentAddress(),
                                student.getEthnicity(),
                                student.getStatus(),
                                new StudentResponse.StudentAccount(student.getAccount().getId(),
                                                student.getAccount().getUsername()),
                                new StudentResponse.StudentMajor(student.getMajor().getMajorCode(),
                                                student.getMajor().getMajorName()),
                                new StudentResponse.StudentClass(student.getClassEntity().getClassId(),
                                                student.getClassEntity().getEducationLevel(),
                                                student.getClassEntity().getAcademicYear()),
                                new StudentResponse.StudentFaculty(
                                                student.getClassEntity().getDepartment().getDepartmentId(),
                                                student.getClassEntity().getDepartment().getDepartmentName()));
        }

        public AcademicResultResponse convertAcademicResultToDTO(AcademicResult academicResult) {
                return new AcademicResultResponse(
                                academicResult.getStudent().getStudentId(),
                                academicResult.getSemester().getOrder(),
                                academicResult.getSemester().getAcademicYear(),
                                academicResult.getGpa());
        }
}
