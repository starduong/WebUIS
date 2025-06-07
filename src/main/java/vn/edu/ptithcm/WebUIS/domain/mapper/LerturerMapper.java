package vn.edu.ptithcm.WebUIS.domain.mapper;

import org.springframework.stereotype.Component;

import vn.edu.ptithcm.WebUIS.domain.entity.Lecturer;
import vn.edu.ptithcm.WebUIS.domain.response.lecturer.LecturerResponse;

@Component
public class LerturerMapper {

    public LecturerResponse convertLecturerToDTO(Lecturer lecturer) {
        return new LecturerResponse(
                lecturer.getLecturerId(),
                lecturer.getLastName(),
                lecturer.getFirstName(),
                lecturer.getGender(),
                lecturer.getPhoneNumber(),
                lecturer.getCitizenId(),
                lecturer.getEmail(),
                lecturer.getAcademicRank(),
                lecturer.getAcademicTitle(),
                lecturer.getSpecialization(),
                lecturer.getImageUrl(),
                lecturer.getStatus(),
                new LecturerResponse.LecturerAccount(lecturer.getAccount().getId(),
                        lecturer.getAccount().getUsername()),
                new LecturerResponse.LecturerDepartment(lecturer.getDepartment().getDepartmentId(),
                        lecturer.getDepartment().getDepartmentName()));
    }

}
