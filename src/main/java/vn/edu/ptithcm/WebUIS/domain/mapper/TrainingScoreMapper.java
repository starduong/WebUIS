package vn.edu.ptithcm.WebUIS.domain.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.Criterion;
import vn.edu.ptithcm.WebUIS.domain.entity.EvaluationContent;
import vn.edu.ptithcm.WebUIS.domain.entity.EvaluationContentDetail;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScoreDetail;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreClassify;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;
import vn.edu.ptithcm.WebUIS.domain.response.TrainingScoreStatisticsResponse;
import vn.edu.ptithcm.WebUIS.domain.response.classcommittee.TrainingScoreByClassResponse;
import vn.edu.ptithcm.WebUIS.domain.response.classcommittee.TrainingScoreTimeResponse;
import vn.edu.ptithcm.WebUIS.domain.response.department.TrainingScoreByFCSResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.CriterionResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.EvaluationContentDetailResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.EvaluationContentResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.FormTrainingScoreResponse;
import vn.edu.ptithcm.WebUIS.domain.response.student.TrainingScoreBySemesterResponse;
import vn.edu.ptithcm.WebUIS.repository.CriterionRepository;
import vn.edu.ptithcm.WebUIS.repository.EvaluationContentDetailRepository;
import vn.edu.ptithcm.WebUIS.repository.EvaluationContentRepository;

@Component
@RequiredArgsConstructor
public class TrainingScoreMapper {

    private final CriterionRepository criterionRepository;
    private final EvaluationContentRepository evaluationContentRepository;
    private final EvaluationContentDetailRepository evaluationContentDetailRepository;

    /**
     * Chuyển đổi TrainingScore thành TrainingScoreByFCSResponse
     * 
     * @param trainingScore
     * @return
     */
    public TrainingScoreByFCSResponse convertTrainingScoreToFCSDTO(TrainingScore trainingScore) {
        List<TrainingScoreByFCSResponse.DepartmentCriteriaResponse> departmentCriteriaResponses = new ArrayList<>();
        List<TrainingScoreDetail> trainingScoreDetails = trainingScore.getTrainingScoreDetails();
        if (trainingScoreDetails == null || trainingScoreDetails.isEmpty()) {
            // nếu không có chi tiết điểm rèn luyện thì trả về điểm rèn luyện với
            // hiển thị danh sách các tiêu chí mặc định với điểm là 0
            // tất cả điểm là 0
            List<Criterion> criteria = criterionRepository.findAll();
            for (Criterion criterion : criteria) {
                departmentCriteriaResponses.add(new TrainingScoreByFCSResponse.DepartmentCriteriaResponse(
                        criterion.getId(),
                        criterion.getCriterionName(),
                        0));
            }

            // Sắp xếp tiêu chí theo ID
            departmentCriteriaResponses.sort((c1, c2) -> c1.getId().compareTo(c2.getId()));

            return new TrainingScoreByFCSResponse(
                    trainingScore.getId(),
                    trainingScore.getStudent().getStudentId(),
                    trainingScore.getStudent().getFirstName(),
                    trainingScore.getStudent().getLastName(),
                    departmentCriteriaResponses,
                    0,
                    trainingScore.getStatus());
        }

        // Sử dụng Map để nhóm các điểm theo tiêu chí
        Map<Integer, Integer> criterionScores = new HashMap<>();
        Map<Integer, String> criterionNames = new HashMap<>();

        // Tính tổng điểm cho mỗi tiêu chí
        for (TrainingScoreDetail trainingScoreDetail : trainingScoreDetails) {
            Criterion criterion = trainingScoreDetail.getEvaluationContent().getCriterion();
            if (criterion == null) {
                continue;
            }
            Integer criterionId = criterion.getId();
            String criterionName = criterion.getCriterionName();
            Integer score = trainingScoreDetail.getAdvisorScore() != null ? trainingScoreDetail.getAdvisorScore() : 0;

            criterionNames.putIfAbsent(criterionId, criterionName);
            criterionScores.put(criterionId, criterionScores.getOrDefault(criterionId, 0) + score);
        }

        // Tạo danh sách các DepartmentCriteriaResponse từ Map
        for (Map.Entry<Integer, Integer> entry : criterionScores.entrySet()) {
            Integer criterionId = entry.getKey();
            Integer totalScore = entry.getValue();
            String criterionName = criterionNames.get(criterionId);

            departmentCriteriaResponses.add(new TrainingScoreByFCSResponse.DepartmentCriteriaResponse(
                    criterionId,
                    criterionName,
                    totalScore));
        }

        // Sắp xếp tiêu chí theo ID
        departmentCriteriaResponses.sort((c1, c2) -> c1.getId().compareTo(c2.getId()));

        return new TrainingScoreByFCSResponse(
                trainingScore.getId(),
                trainingScore.getStudent().getStudentId(),
                trainingScore.getStudent().getFirstName(),
                trainingScore.getStudent().getLastName(),
                departmentCriteriaResponses,
                trainingScore.getTotalScore(),
                trainingScore.getStatus());
    }

    /**
     * khởi tạo form đánh giá điểm rèn luyện - dành cho trường hợp mới tạo
     * TrainingScore
     * chưa có TrainingScoreDetail
     * 
     * @param trainingScoreId
     * @return
     */
    public FormTrainingScoreResponse initFormTrainingScoreResponse(Integer trainingScoreId) {
        List<CriterionResponse> criterionResponses = new ArrayList<>();

        // Lấy tất cả các tiêu chí
        List<Criterion> criteria = criterionRepository.findAll();

        // Xử lý từng tiêu chí
        for (Criterion criterion : criteria) {
            // Lấy tất cả nội dung đánh giá thuộc tiêu chí này
            List<EvaluationContent> criterionContents = evaluationContentRepository
                    .findByCriterionId(criterion.getId());

            // Sắp xếp nội dung đánh giá theo ID
            criterionContents.sort((c1, c2) -> c1.getId().compareTo(c2.getId()));

            List<EvaluationContentResponse> evaluationContentResponses = new ArrayList<>();

            // Tính tổng điểm tối đa cho tiêu chí
            Integer maxScore = 0;

            // Xử lý từng nội dung đánh giá
            for (EvaluationContent evaluationContent : criterionContents) {
                if (evaluationContent.getMaxScore() > 0) {
                    maxScore += evaluationContent.getMaxScore();
                }
                // Lấy chi tiết nội dung đánh giá
                List<EvaluationContentDetail> evaluationContentDetails = getEvaluationContentDetails(
                        evaluationContent.getId());

                List<EvaluationContentDetailResponse> evaluationContentDetailResponses = null;

                // Nếu có chi tiết nội dung đánh giá thì tạo danh sách
                if (!evaluationContentDetails.isEmpty()) {
                    evaluationContentDetailResponses = new ArrayList<>();

                    for (EvaluationContentDetail evaluationContentDetail : evaluationContentDetails) {
                        EvaluationContent detailContent = evaluationContentDetail.getEvaluationContentDetail();
                        evaluationContentDetailResponses.add(new EvaluationContentDetailResponse(
                                detailContent.getId(),
                                detailContent.getContent(),
                                detailContent.getMaxScore(), // This is the score field
                                null, // studentScore
                                null, // classCommitteeScore
                                null // academicAdvisorScore
                        ));
                    }

                    // Sắp xếp chi tiết nội dung đánh giá theo ID
                    evaluationContentDetailResponses.sort((d1, d2) -> d1.getId().compareTo(d2.getId()));
                }

                // Thêm nội dung đánh giá vào danh sách
                evaluationContentResponses.add(new EvaluationContentResponse(
                        evaluationContent.getId(),
                        evaluationContent.getContent(),
                        evaluationContent.getMaxScore(),
                        0, // totalStudentScore
                        0, // totalClassCommitteeScore
                        0, // totalAcademicAdvisorScore
                        evaluationContentDetailResponses));
            }

            // Thêm tiêu chí vào danh sách
            criterionResponses.add(new CriterionResponse(
                    criterion.getId(),
                    criterion.getCriterionName(),
                    maxScore,
                    0, // score (total score for this criterion)
                    evaluationContentResponses));
        }

        // Sắp xếp tiêu chí theo ID
        criterionResponses.sort((c1, c2) -> c1.getId().compareTo(c2.getId()));

        return new FormTrainingScoreResponse(
                trainingScoreId,
                criterionResponses,
                0,
                TrainingScoreStatus.WAIT_STUDENT);
    }

    /**
     * chuyển đổi điểm rèn luyện thành dạng response
     * 
     * @param trainingScore điểm rèn luyện
     * @return dạng response
     */
    public FormTrainingScoreResponse convertTrainingScoreToFormTrainingScoreResponse(TrainingScore trainingScore) {
        // Group training score details by criterion
        Map<Criterion, List<TrainingScoreDetail>> detailsByCriterion = new HashMap<>();
        // Map to store all details for later use with child contents
        Map<Integer, TrainingScoreDetail> allDetailsByContentId = new HashMap<>();

        // Process all training score details
        for (TrainingScoreDetail detail : trainingScore.getTrainingScoreDetails()) {
            EvaluationContent content = detail.getEvaluationContent();
            // Store all details by content ID for easy lookup
            allDetailsByContentId.put(content.getId(), detail);

            Criterion criterion = content.getCriterion();
            if (criterion == null) {
                // Skip for now, but we've stored it in allDetailsByContentId for later use
                continue;
            }

            if (!detailsByCriterion.containsKey(criterion)) {
                detailsByCriterion.put(criterion, new ArrayList<>());
            }
            detailsByCriterion.get(criterion).add(detail);
        }

        // Create list of criterion responses
        List<CriterionResponse> criterionResponses = new ArrayList<>();

        // Process each criterion
        for (Map.Entry<Criterion, List<TrainingScoreDetail>> entry : detailsByCriterion.entrySet()) {
            Criterion criterion = entry.getKey();
            List<TrainingScoreDetail> details = entry.getValue();

            // Create evaluation contents for this criterion
            List<EvaluationContentResponse> evaluationContents = createEvaluationContentResponses(details,
                    allDetailsByContentId);

            // Calculate max score for criterion if getMaxScore > 0
            Integer maxScore = evaluationContents.stream()
                    .map(EvaluationContentResponse::getMaxScore)
                    .filter(score -> score > 0)
                    .mapToInt(Integer::intValue)
                    .sum();

            // Calculate total score for criterion
            Integer totalScore = evaluationContents.stream()
                    .mapToInt(EvaluationContentResponse::getTotalAcademicAdvisorScore)
                    .sum();

            // Create criterion response
            CriterionResponse criterionResponse = new CriterionResponse(
                    criterion.getId(),
                    criterion.getCriterionName(),
                    maxScore,
                    totalScore,
                    evaluationContents);

            criterionResponses.add(criterionResponse);
        }

        // Sort criterion responses by ID to maintain consistent order
        criterionResponses.sort((cr1, cr2) -> cr1.getId().compareTo(cr2.getId()));

        // Create and return the response
        return new FormTrainingScoreResponse(
                trainingScore.getId(),
                criterionResponses,
                trainingScore.getTotalScore(),
                trainingScore.getStatus());
    }

    /**
     * tạo danh sách nội dung đánh giá
     * 
     * @param details               danh sách chi tiết điểm rèn luyện
     * @param allDetailsByContentId map chứa tất cả chi tiết điểm rèn luyện theo id
     *                              nội dung đánh giá
     * @return danh sách nội dung đánh giá
     */
    private List<EvaluationContentResponse> createEvaluationContentResponses(
            List<TrainingScoreDetail> details,
            Map<Integer, TrainingScoreDetail> allDetailsByContentId) {
        // Group details by evaluation content
        Map<EvaluationContent, List<TrainingScoreDetail>> detailsByContent = new HashMap<>();

        for (TrainingScoreDetail detail : details) {
            EvaluationContent content = detail.getEvaluationContent();

            if (!detailsByContent.containsKey(content)) {
                detailsByContent.put(content, new ArrayList<>());
            }
            detailsByContent.get(content).add(detail);
        }

        // Convert each group to EvaluationContentResponse
        List<EvaluationContentResponse> responses = new ArrayList<>();

        for (Map.Entry<EvaluationContent, List<TrainingScoreDetail>> entry : detailsByContent.entrySet()) {
            EvaluationContent content = entry.getKey();
            List<TrainingScoreDetail> contentDetails = entry.getValue();

            // Initialize total scores
            Integer totalStudentScore = 0;
            Integer totalClassCommitteeScore = 0;
            Integer totalAcademicAdvisorScore = 0;

            // Calculate scores for this content
            for (TrainingScoreDetail detail : contentDetails) {
                totalStudentScore += detail.getStudentScore() != null ? detail.getStudentScore() : 0;
                totalClassCommitteeScore += detail.getClassCommitteeScore() != null
                        ? detail.getClassCommitteeScore()
                        : 0;
                totalAcademicAdvisorScore += detail.getAdvisorScore() != null ? detail.getAdvisorScore() : 0;
            }

            // Process child content details
            List<EvaluationContentDetailResponse> childContentResponses = null;

            // Get all child contents for this parent content
            List<EvaluationContentDetail> childRelations = getEvaluationContentDetails(content.getId());

            if (!childRelations.isEmpty()) {
                childContentResponses = new ArrayList<>();

                // Process each child content
                for (EvaluationContentDetail relation : childRelations) {
                    EvaluationContent childContent = relation.getEvaluationContentDetail();
                    if (childContent == null)
                        continue;

                    // Find the training score detail for this child content if it exists
                    TrainingScoreDetail matchingDetail = allDetailsByContentId.get(childContent.getId());

                    // Get scores from the matching detail or set to null
                    Integer studentScore = null;
                    Integer classCommitteeScore = null;
                    Integer advisorScore = null;

                    if (matchingDetail != null) {
                        studentScore = matchingDetail.getStudentScore();
                        classCommitteeScore = matchingDetail.getClassCommitteeScore();
                        advisorScore = matchingDetail.getAdvisorScore();
                    }

                    // Create child content response
                    EvaluationContentDetailResponse childResponse = new EvaluationContentDetailResponse(
                            childContent.getId(),
                            childContent.getContent(),
                            childContent.getMaxScore(),
                            studentScore,
                            classCommitteeScore,
                            advisorScore);

                    childContentResponses.add(childResponse);
                }

                // Sort child content responses by ID to maintain consistent order
                childContentResponses.sort((cr1, cr2) -> cr1.getId().compareTo(cr2.getId()));
            }

            // Create and add EvaluationContentResponse
            EvaluationContentResponse response = new EvaluationContentResponse(
                    content.getId(),
                    content.getContent(),
                    content.getMaxScore(),
                    totalStudentScore,
                    totalClassCommitteeScore,
                    totalAcademicAdvisorScore,
                    childContentResponses);

            responses.add(response);
        }

        // Sort responses by ID to maintain consistent order
        responses.sort((r1, r2) -> r1.getId().compareTo(r2.getId()));

        return responses;
    }

    /**
     * Lấy danh sách chi tiết nội dung đánh giá theo id nội dung đánh giá
     * 
     * @param evaluationContentId id nội dung đánh giá
     * @return danh sách chi tiết nội dung đánh giá
     */
    private List<EvaluationContentDetail> getEvaluationContentDetails(Integer evaluationContentId) {
        return evaluationContentDetailRepository.findByIdEvaluationContent(evaluationContentId);
    }

    public TrainingScoreBySemesterResponse convertTrainingScoreBySemesterToDTO(TrainingScore trainingScore) {
        return new TrainingScoreBySemesterResponse(
                trainingScore.getId(),
                new TrainingScoreBySemesterResponse.SemesterResponse(
                        trainingScore.getSemester().getId(),
                        trainingScore.getSemester().getOrder(),
                        trainingScore.getSemester().getAcademicYear()),
                trainingScore.getStartDate(),
                trainingScore.getEndDate(),
                trainingScore.getTotalScore(),
                TrainingScoreClassify.fromScore(trainingScore.getTotalScore()),
                trainingScore.getStatus());
    }

    /**
     * chuyển đổi TrainingScore thành TrainingScoreByClassResponse
     * 
     * @param trainingScore
     * @return TrainingScoreByClassResponse
     */
    public TrainingScoreByClassResponse convertTrainingScoreToClassDTO(TrainingScore trainingScore) {
        return new TrainingScoreByClassResponse(
                trainingScore.getId(),
                trainingScore.getStudent().getStudentId(),
                trainingScore.getStudent().getFirstName(),
                trainingScore.getStudent().getLastName(),
                trainingScore.getTotalScore() != null ? trainingScore.getTotalScore() : 0,
                TrainingScoreClassify.fromScore(trainingScore.getTotalScore()),
                trainingScore.getStatus());
    }

    /**
     * chuyển đổi danh sách TrainingScore thành TrainingScoreStatisticsResponse
     * 
     * @param trainingScores
     * @return
     */
    public TrainingScoreStatisticsResponse convertTrainingScoreToStatisticsResponse(
            List<TrainingScore> trainingScores) {
        TrainingScoreStatisticsResponse response = new TrainingScoreStatisticsResponse();

        ClassEntity classEntity = trainingScores.get(0).getStudent().getClassEntity();
        response.setClassId(classEntity.getClassId());
        response.setSemesterOrder(trainingScores.get(0).getSemester().getOrder().toString());
        response.setSemesterYear(trainingScores.get(0).getSemester().getAcademicYear().toString());
        response.setTotalStudent(trainingScores.size());

        // Đếm số lượng từng loại phân loại
        Map<TrainingScoreClassify, Long> classifyCountMap = trainingScores.stream()
                .map(score -> TrainingScoreClassify.fromScore(score.getTotalScore()))
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        response.setTotalExcellent(classifyCountMap.getOrDefault(TrainingScoreClassify.EXCELLENT, 0L).intValue());
        response.setTotalGood(classifyCountMap.getOrDefault(TrainingScoreClassify.GOOD, 0L).intValue());
        response.setTotalFair(classifyCountMap.getOrDefault(TrainingScoreClassify.FAIR, 0L).intValue());
        response.setTotalAverage(classifyCountMap.getOrDefault(TrainingScoreClassify.AVERAGE, 0L).intValue());
        response.setTotalWeak(classifyCountMap.getOrDefault(TrainingScoreClassify.WEAK, 0L).intValue());
        response.setTotalPoor(classifyCountMap.getOrDefault(TrainingScoreClassify.POOR, 0L).intValue());

        return response;
    }

    /**
     * chuyển đổi TrainingScore thành TrainingScoreTimeResponse
     * 
     * @param trainingScore
     * @return
     */
    public TrainingScoreTimeResponse convertTrainingScoreToTimeResponse(TrainingScore trainingScore) {
        return new TrainingScoreTimeResponse(
                trainingScore.getSemester().getId(),
                trainingScore.getSemester().getOrder(),
                trainingScore.getSemester().getAcademicYear(),
                trainingScore.getStartDate(),
                trainingScore.getEndDate());
    }

}
