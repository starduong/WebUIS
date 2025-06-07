package vn.edu.ptithcm.WebUIS.controller.classcommittee;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.Semester;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.service.ClassCommitteeService;
import vn.edu.ptithcm.WebUIS.service.SemesterService;
import vn.edu.ptithcm.WebUIS.util.annotation.ApiMessage;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/class-committee")
public class ClassCommitteeController {
    private final ClassCommitteeService classCommitteeService;
    private final SemesterService semesterService;

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
}
