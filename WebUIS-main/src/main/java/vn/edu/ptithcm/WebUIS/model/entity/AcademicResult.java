package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "KetQuaHocTap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicResult {

    @Id
    @Column(name = "IdKQHT", nullable = false)
    private Integer resultId;

    @Column(name = "MaSV", length = 10, nullable = false)
    private String studentCode;

    @Column(name = "IdHK", nullable = false)
    private Integer semesterId;

    @Column(name = "GPA")
    private Float gpa;

    @ManyToOne
    @JoinColumn(name = "MaSV", referencedColumnName = "MaSV", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "IdHK", referencedColumnName = "IdHK", insertable = false, updatable = false)
    private Semester semester;
}
