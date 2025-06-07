package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicResultPK implements Serializable {

    @Column(name = "MaSV", nullable = false)
    @NotNull(message = "Mã sinh viên không được để trống")
    private String studentId;

    @Column(name = "IdHK", nullable = false)
    @NotNull(message = "Mã học kỳ không được để trống")
    private Integer semesterId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AcademicResultPK that = (AcademicResultPK) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(semesterId, that.semesterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, semesterId);
    }
}