package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "CoVanHocTap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicAdvisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCVHT", nullable = false)
    private Integer advisorId;

    @Column(name = "MaGV", length = 10, nullable = false)
    private String lecturerCode;

    @Column(name = "MaLop", length = 10, nullable = false)
    private String classCode;

    @Column(name = "NgayQuyetDinh", nullable = false)
    private java.time.LocalDate decisionDate;

    @ManyToOne
    @JoinColumn(name = "MaGV", referencedColumnName = "MaGV", insertable = false, updatable = false)
    private Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "MaLop", referencedColumnName = "MaLop", insertable = false, updatable = false)
    private ClassEntity classEntity;
}
