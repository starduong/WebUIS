package vn.edu.ptithcm.WebUIS.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.repository.ClassRepository;
import vn.edu.ptithcm.WebUIS.repository.SemesterRepository;

@Service
@RequiredArgsConstructor
public class SemesterService {
    private final SemesterRepository semesterRepository;
    private final ClassRepository classRepository;

    public List<Semester> getSemesterByClass(String classId) {
        ClassEntity classEntity = classRepository.findById(classId).orElse(null);
        List<Semester> semesters = semesterRepository.findAll(Sort.by(Sort.Direction.DESC, "academicYear", "order"));
        List<Semester> result = new ArrayList<>();

        if (classEntity != null) {
            // Extract class academic year (e.g., "2022-2027" -> 2022)
            int classStartYear = extractStartYear(classEntity.getAcademicYear());
            // Extract class academic year (e.g., "2022-2027" -> 2027)
            int classFinishYear = extractFinishYear(classEntity.getAcademicYear());
            for (Semester semester : semesters) {
                // Extract semester academic year (e.g., "2022-2023" -> 2022)
                int semesterStartYear = extractStartYear(semester.getAcademicYear());
                // Extract semester academic year (e.g., "2022-2023" -> 2023)
                int semesterFinishYear = extractFinishYear(semester.getAcademicYear());
                if (semesterStartYear >= classStartYear && semesterFinishYear <= classFinishYear) {
                    result.add(semester);
                }
            }
        }
        return result;
    }

    private int extractStartYear(String academicYear) {
        if (academicYear == null || academicYear.isEmpty() || !academicYear.contains("-")) {
            return 0;
        }

        try {
            return Integer.parseInt(academicYear.split("-")[0]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private int extractFinishYear(String academicYear) {
        if (academicYear == null || academicYear.isEmpty() || !academicYear.contains("-")) {
            return 0;
        }

        try {
            return Integer.parseInt(academicYear.split("-")[1]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
