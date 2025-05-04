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
@Table(name = "CoVanHocTap")
public class AcademicAdvisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCVHT")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaGV", nullable = false)
    @NotNull(message = "Mã giảng viên không được để trống")
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLop", nullable = false)
    @NotNull(message = "Mã lớp không được để trống")
    private ClassEntity classEntity;

    @Column(name = "NgayQuyetDinh", nullable = false)
    @NotNull(message = "Ngày quyết định không được để trống")
    private LocalDate decisionDate;

    // Relationship added: One Advisor can assess many TrainingScores
    @OneToMany(mappedBy = "academicAdvisor", fetch = FetchType.LAZY)
    private List<TrainingScore> trainingScores;
}