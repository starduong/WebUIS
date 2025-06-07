package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "BanCanSu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassCommittee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdBCS")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "MaSV", nullable = false)
    @NotNull(message = "Mã sinh viên không được để trống")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "MaLop", nullable = false)
    @NotNull(message = "Mã lớp không được để trống")
    private ClassEntity classEntity;

    @Column(name = "NgayQuyetDinh", nullable = false)
    @NotNull(message = "Ngày quyết định không được để trống")
    private LocalDate decisionDate;

    @Column(name = "ChucDanh", nullable = false)
    @NotNull(message = "Chức danh không được để trống")
    private String position;
}
