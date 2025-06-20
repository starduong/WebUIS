package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "CoVanHocTap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}