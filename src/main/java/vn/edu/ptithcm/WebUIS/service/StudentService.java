package vn.edu.ptithcm.WebUIS.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.AcademicResult;
import vn.edu.ptithcm.WebUIS.domain.entity.Account;
import vn.edu.ptithcm.WebUIS.domain.entity.EvaluationContent;
import vn.edu.ptithcm.WebUIS.domain.entity.Student;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetail;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetailPK;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;
import vn.edu.ptithcm.WebUIS.domain.mapper.StudentMapper;
import vn.edu.ptithcm.WebUIS.domain.mapper.TrainingScoreMapper;
import vn.edu.ptithcm.WebUIS.domain.request.SubmitTrainingScoreRequest;
import vn.edu.ptithcm.WebUIS.domain.request.UpdateStudentRequest;
import vn.edu.ptithcm.WebUIS.domain.response.student.AcademicResultResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.StudentResponse;
import vn.edu.ptithcm.WebUIS.exception.BadRequestException;
import vn.edu.ptithcm.WebUIS.exception.IdInValidException;
import vn.edu.ptithcm.WebUIS.repository.AcademicResultRepository;
import vn.edu.ptithcm.WebUIS.repository.AccountRepository;
import vn.edu.ptithcm.WebUIS.repository.EvaluationContentRepository;
import vn.edu.ptithcm.WebUIS.repository.StudentRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreDetailRepository;
import vn.edu.ptithcm.WebUIS.repository.TrainingScoreRepository;
import vn.edu.ptithcm.WebUIS.util.S3UploadFileUtil;
import vn.edu.ptithcm.WebUIS.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final AccountRepository accountRepository;
    private final AcademicResultRepository academicResultRepository;
    private final TrainingScoreRepository trainingScoreRepository;
    private final S3UploadFileUtil s3UploadFileUtil;
    private final StudentMapper studentMapper;
    private final TrainingScoreMapper trainingScoreMapper;
    private final EvaluationContentRepository evaluationContentRepository;
    private final TrainingScoreDetailRepository trainingScoreDetailRepository;

    /**
     * Lấy thông tin sinh viên đang đăng nhập
     * 
     * @return student
     */
    public Student getCurrentStudentLogin() {
        String username = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get()
                : null;
        return studentRepository.findByAccount(accountRepository.findByUsername(username).orElse(null));
    }

    public List<StudentResponse> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::convertStudentToDTO)
                .collect(Collectors.toList());
    }

    public StudentResponse getStudentById(String studentId) {
        return studentMapper.convertStudentToDTO(studentRepository.findByStudentId(studentId));
    }

    public Student getStudentByAccount(Account account) {
        return studentRepository.findByAccount(account);
    }

    public List<AcademicResult> getAcademicResultByStudentId(String studentId) {
        return academicResultRepository.findByIdStudentId(studentId);
    }

    public List<AcademicResultResponse> getAcademicResultDTOByStudentId(String studentId) {
        List<AcademicResult> results = academicResultRepository.findByIdStudentId(studentId);
        return results.stream()
                .map(studentMapper::convertAcademicResultToDTO)
                .collect(Collectors.toList());
    }

    public Double getAverageGpaByStudentId(String studentId) {
        return Math.round(academicResultRepository.findAverageGpaByStudentId(studentId) * 100.0) / 100.0;
    }

    // update student
    public StudentResponse updateStudent(String studentId, UpdateStudentRequest studentRequest, MultipartFile avatar)
            throws IdInValidException, IOException {
        Student student = studentRepository.findByStudentId(studentId);
        if (student == null) {
            throw new IdInValidException("Sinh viên không tồn tại");
        }
        student.setLastName(studentRequest.getLastName());
        student.setFirstName(studentRequest.getFirstName());
        student.setDateOfBirth(studentRequest.getDateOfBirth());
        student.setGender(studentRequest.getGender());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        if (studentRequest.getCitizenId() != null) {
            if (studentRepository.existsByCitizenId(studentRequest.getCitizenId())) {
                throw new IdInValidException("Số CCCD đã tồn tại");
            }
            student.setCitizenId(studentRequest.getCitizenId());
        }
        student.setPersonalEmail(studentRequest.getPersonalEmail());
        if (avatar != null) {
            student.setImagePath(s3UploadFileUtil.uploadFile(avatar, "temps"));
        }
        student.setHometown(studentRequest.getHometown());
        student.setAddress(studentRequest.getAddress());
        student.setPermanentAddress(studentRequest.getPermanentAddress());
        student.setEthnicity(studentRequest.getEthnicity());
        return studentMapper.convertStudentToDTO(studentRepository.save(student));
    }

    /**
     * Kiểm tra xem username có phải là chủ sở hữu của studentId không
     * 
     * @param username  Tên đăng nhập
     * @param studentId Mã sinh viên
     * @return true nếu username là chủ sở hữu của studentId, ngược lại false
     */
    public boolean isStudentOwner(String username, String studentId) {
        Account account = accountRepository.findByUsername(username).orElse(null);
        if (account == null) {
            return false;
        }

        Student student = studentRepository.findByAccount(account);
        if (student == null) {
            return false;
        }

        return student.getStudentId().equals(studentId);
    }

    /**
     * kiểm tra xem điểm rèn luyện sinh viên có được phép xem không
     * 
     * @param trainingScoreId id điểm rèn luyện
     * @return true nếu sinh viên có được phép xem, ngược lại false
     */
    public boolean isStudentCanViewTrainingScore(Integer trainingScoreId) {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId).orElse(null);
        if (trainingScore == null) {
            return false;
        }

        Student student = getCurrentStudentLogin();
        if (student == null) {
            return false;
        }

        return trainingScore.getStudent().getStudentId().equals(student.getStudentId());
    }

    /**
     * Kiểm tra xem sinh viên có được phép đánh giá điểm rèn luyện không
     * 
     * @param trainingScoreId id điểm rèn luyện
     * @return true nếu sinh viên có được phép đánh giá, ngược lại false
     */
    public boolean isStudentCanEvaluateTrainingScore(Integer trainingScoreId) {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId).orElse(null);
        if (trainingScore == null) {
            return false;
        }
        return trainingScore.getStatus() == TrainingScoreStatus.WAIT_STUDENT &&
                trainingScore.getStudent().getStudentId().equals(getCurrentStudentLogin().getStudentId());
    }

    /**
     * Sinh viên đánh giá điểm rèn luyện
     * 
     * @param trainingScoreId            id điểm rèn luyện
     * @param submitTrainingScoreRequest request đánh giá điểm rèn luyện
     * @return
     * @throws IdInValidException
     */
    @Transactional(rollbackOn = { IdInValidException.class, BadRequestException.class })
    public FormTrainingScoreResponse submitTrainingScore(Integer trainingScoreId,
            SubmitTrainingScoreRequest submitTrainingScoreRequest)
            throws IdInValidException {
        TrainingScore trainingScore = trainingScoreRepository.findById(trainingScoreId).orElse(null);
        if (trainingScore == null) {
            throw new IdInValidException("Điểm rèn luyện không tồn tại");
        }

        if (!isStudentCanEvaluateTrainingScore(trainingScoreId)) {
            throw new BadRequestException("Bạn không có quyền đánh giá điểm rèn luyện này");
        }

        // tạo trainingScoreDetail
        List<SubmitTrainingScoreRequest.TrainingScoreDetailRequest> trainingScoreDetailRequests = submitTrainingScoreRequest
                .getTrainingScoreDetails();
        for (SubmitTrainingScoreRequest.TrainingScoreDetailRequest evaluationContentDetailRequest : trainingScoreDetailRequests) {
            EvaluationContent evaluationContent = evaluationContentRepository
                    .findById(evaluationContentDetailRequest.getEvaluationContentId()).orElse(null);
            TrainingScoreDetail trainingScoreDetail = new TrainingScoreDetail();
            trainingScoreDetail.setId(new TrainingScoreDetailPK(trainingScoreId,
                    evaluationContentDetailRequest.getEvaluationContentId()));
            trainingScoreDetail.setTrainingScore(trainingScore);
            trainingScoreDetail.setEvaluationContent(evaluationContent);
            trainingScoreDetail.setStudentScore(evaluationContentDetailRequest.getScore());
            trainingScoreDetailRepository.save(trainingScoreDetail);
        }
        trainingScore.setStatus(TrainingScoreStatus.WAIT_CLASS_COMMITTEE);
        trainingScore.setStudentAssessmentDate(LocalDate.now());
        trainingScoreRepository.save(trainingScore);
        return trainingScoreMapper.convertTrainingScoreToFormTrainingScoreResponse(trainingScore);
    }
}