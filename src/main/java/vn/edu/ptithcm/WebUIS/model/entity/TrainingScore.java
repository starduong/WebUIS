package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DiemRenLuyen")
public class TrainingScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDRL", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdHK", nullable = false)
    @NotNull(message = "Học kỳ không được để trống")
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaSV", nullable = false)
    @NotNull(message = "Mã sinh viên không được để trống")
    private Student student;

    @Column(name = "ThoiGianBatDau")
    @NotNull(message = "Thời gian bắt đầu không được để trống")
    private LocalDate startDate;

    @Column(name = "ThoiGianKetThuc")
    @NotNull(message = "Thời gian kết thúc không được để trống")
    private LocalDate endDate;

    @Column(name = "NgaySVCham")
    private LocalDate studentAssessmentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdBCS")
    private ClassCommittee classCommittee;

    @Column(name = "NgayBCSCham")
    private LocalDate classCommitteeAssessmentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCVHT")
    private AcademicAdvisor academicAdvisor;

    @Column(name = "NgayCVHTCham")
    private LocalDate advisorAssessmentDate;

    @Column(name = "TongDiem")
    private Integer totalScore;

    @OneToMany(mappedBy = "trainingScore")
    private List<TrainingScoreDetail> trainingScoreDetails;
}