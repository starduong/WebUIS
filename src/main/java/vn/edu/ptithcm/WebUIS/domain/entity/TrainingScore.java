package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import vn.edu.ptithcm.WebUIS.domain.enumeration.TrainingScoreStatus;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "DiemRenLuyen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDRL", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdHK", nullable = false)
    @NotNull(message = "Học kỳ không được để trống")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Semester semester;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaSV", nullable = false)
    @NotNull(message = "Mã sinh viên không được để trống")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "academicResults", "trainingScores",
            "classCommittees" })
    private Student student;

    @Column(name = "ThoiGianBatDau")
    @NotNull(message = "Thời gian bắt đầu không được để trống")
    private LocalDate startDate;

    @Column(name = "ThoiGianKetThuc")
    @NotNull(message = "Thời gian kết thúc không được để trống")
    private LocalDate endDate;

    @Column(name = "NgaySVCham")
    private LocalDate studentAssessmentDate;

    @Column(name = "NgayBCSCham")
    private LocalDate classCommitteeAssessmentDate;

    @Column(name = "NgayCVHTCham")
    private LocalDate advisorAssessmentDate;

    @Column(name = "TrangThai")
    @Enumerated(EnumType.STRING)
    private TrainingScoreStatus status;

    @Column(name = "TongDiem")
    private Integer totalScore;

    @JsonIgnore
    @OneToMany(mappedBy = "trainingScore")
    private List<TrainingScoreDetail> trainingScoreDetails;

    @PrePersist
    public void prePersist() {
        this.status = TrainingScoreStatus.WAIT_STUDENT;
        this.totalScore = 0;
    }
}