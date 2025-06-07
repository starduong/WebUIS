package vn.edu.ptithcm.WebUIS.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "KetQuaHocTap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicResult {

    @EmbeddedId
    private AcademicResultPK id;

    @JsonIgnore
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "MaSV")
    @NotNull(message = "Mã sinh viên không được để trống")
    private Student student;

    @JsonIgnore
    @ManyToOne
    @MapsId("semesterId")
    @JoinColumn(name = "IdHK")
    @NotNull(message = "Học kỳ không được để trống")
    private Semester semester;

    @Column(name = "GPA")
    @NotNull(message = "GPA không được để trống")
    @DecimalMin(value = "0.0", message = "GPA phải lớn hơn hoặc bằng 0")
    @DecimalMax(value = "4.0", message = "GPA phải nhỏ hơn hoặc bằng 4")
    private Double gpa;
}