package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "GiangVienKhoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDepartment {

    @Id
    @ManyToOne
    @JoinColumn(name = "MaGV", referencedColumnName = "MaGV", nullable = false)
    private Lecturer lecturer;

    @Id
    @ManyToOne
    @JoinColumn(name = "MaKhoa", referencedColumnName = "MaKhoa", nullable = false)
    private Faculty department;

    @Column(name = "NgayQuyetDinh", nullable = false)
    private LocalDate decisionDate;
}
