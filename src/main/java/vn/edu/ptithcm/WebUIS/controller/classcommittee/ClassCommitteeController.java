package vn.edu.ptithcm.WebUIS.controller.classcommittee;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.mapper.ClassEntityMapper;
import vn.edu.ptithcm.WebUIS.domain.response.ClassEntityResponse;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.service.ClassCommitteeService;
import vn.edu.ptithcm.WebUIS.service.SemesterService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/class-committee")
public class ClassCommitteeController {
    private final ClassCommitteeService classCommitteeService;
    private final SemesterService semesterService;
    private final ClassEntityMapper classEntityMapper;

    /**
     * Lấy danh sách học kỳ của lớp
     * 
     * @return
     */
    @GetMapping("/semesters")
    @ApiMessage("Danh sách học kỳ của lớp")
    public ResponseEntity<List<Semester>> getSemesterByClass() {
        Student student = classCommitteeService.getCurrentStudentLogin();
        String classId = student.getClassEntity().getClassId();
        return ResponseEntity.ok(semesterService.getSemesterByClass(classId));
    }

    /**
     * Lấy ra tên lớp đang làm nhiệm vụ Ban cán sự
     * 
     * @return
     * @throws IdInValidException
     */
    @GetMapping("/class-name")
    @ApiMessage("Lấy ra tên lớp đang làm nhiệm vụ Ban cán sự")
    public ResponseEntity<ClassEntityResponse> getClassByClassCommittee() throws IdInValidException {
        Student student = classCommitteeService.getCurrentStudentLogin();
        return ResponseEntity.ok(classEntityMapper.convertToClassEntityResponse(student.getClassEntity()));
    }
}
