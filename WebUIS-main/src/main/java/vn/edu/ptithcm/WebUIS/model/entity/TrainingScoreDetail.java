package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ChiTietDRL", schema = "HocTap")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingScoreDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCTDRL", nullable = false)
    private Integer trainingScoreDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDRL", nullable = false)
    private TrainingScore trainingScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaSV", nullable = false)
    private Student student;

    // @Column(name = "IdKQHT")
    // private Integer academicResultId;

    @Column(name = "NgaySVCham")
    private LocalDate studentAssessmentDate;

    @Column(name = "TongDiemSV")
    private Integer studentTotalScore;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "IdBCS")
    // private ClassCommittee classCommittee;

    @Column(name = "NgayBCSCham")
    private LocalDate classCommitteeAssessmentDate;

    @Column(name = "TongDiemBCSCham")
    private Integer classCommitteeTotalScore;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "IdCVHT")
    // private AcademicAdvisor academicAdvisor;

    @Column(name = "NgayCVHTCham")
    private LocalDate advisorAssessmentDate;

    @Column(name = "TongDiemCVHT")
    private Integer advisorTotalScore;

    @OneToMany(mappedBy = "trainingScoreDetail", fetch = FetchType.LAZY)
    private List<CriterionAssessment> criterionAssessments;
}