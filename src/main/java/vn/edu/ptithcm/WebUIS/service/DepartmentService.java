package vn.edu.ptithcm.WebUIS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.Department;
import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;
import vn.edu.ptithcm.WebUIS.domain.mapper.TrainingScoreMapper;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.request.department.CreateTrainingScoreByClassAndSemesterRequest;
import vn.edu.ptithcm.WebUIS.domain.response.department.TrainingScoreByFCSResponse;
import vn.edu.ptithcm.WebUIS.exception.BadRequestException;
import vn.edu.ptithcm.WebUIS.repository.ClassRepository;
import vn.edu.ptithcm.WebUIS.repository.DepartmentRepository;
import vn.edu.ptithcm.WebUIS.repository.SemesterRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreRepository;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ClassRepository classRepository;
    private final SemesterRepository semesterRepository;
    private final TrainingScoreRepository trainingScoreRepository;
    private final TrainingScoreMapper trainingScoreMapper;

    public Department getDepartmentById(String id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public List<Department> getAllFaculties() {
        return departmentRepository.findByType(true);
    }

    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }

    /**
     * Tạo điểm rèn luyện mới cho tất cả sinh viên trong lớp và học kỳ
     * =========================================================================================================
     * 
     * @param request
     * @return danh sách điểm rèn luyện của tất cả sinh viên trong lớp và học kỳ
     */
    @Transactional
    public List<TrainingScoreByFCSResponse> createNewTrainingScoreForAllStudentInClassAndSemester(
            CreateTrainingScoreByClassAndSemesterRequest request) {
        ClassEntity classEntity = classRepository.findById(request.getClassId())
                .orElseThrow(() -> new EntityNotFoundException("Class not found"));
        Semester semester = semesterRepository.findById(request.getSemesterId())
                .orElseThrow(() -> new EntityNotFoundException("Semester not found"));
        List<Student> students = classEntity.getStudents();
        List<TrainingScore> trainingScores = new ArrayList<>();
        for (Student student : students) {
            if (student.getStatus() != null && student.getStatus()) {
                // check if training score exists
                if (checkTrainingScoreExists(student.getStudentId(), semester.getId())) {
                    continue;
                }
                TrainingScore trainingScore = new TrainingScore();
                trainingScore.setSemester(semester);
                trainingScore.setStudent(student);
                trainingScore.setStartDate(request.getStartDate());
                trainingScore.setEndDate(request.getEndDate());
                trainingScores.add(trainingScoreRepository.save(trainingScore));
            }
        }
        List<TrainingScoreByFCSResponse> trainingScoreByFCSResponses = new ArrayList<>();
        for (TrainingScore trainingScore : trainingScores) {
            trainingScoreByFCSResponses.add(trainingScoreMapper.convertTrainingScoreToFCSDTO(trainingScore));
        }
        if (trainingScoreByFCSResponses.isEmpty()) {
            throw new BadRequestException("No training score created");
        }
        return trainingScoreByFCSResponses;
    }

    private boolean checkTrainingScoreExists(String studentId, Integer semesterId) {
        return trainingScoreRepository.existsByStudentIdAndSemesterId(studentId, semesterId);
    }
}
