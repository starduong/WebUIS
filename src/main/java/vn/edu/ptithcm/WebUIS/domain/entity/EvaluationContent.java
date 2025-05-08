package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NoiDungDanhGia")
public class EvaluationContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdND")
    private Integer id;

    @Column(name = "NoiDung", length = 500)
    @NotNull(message = "Nội dung không được để trống")
    private String content;

    @Column(name = "DiemToiDa")
    @NotNull(message = "Điểm tối đa không được để trống")
    private Integer maxScore;

    @ManyToOne
    @JoinColumn(name = "IdTC")
    @NotNull(message = "Mã tiêu chí không được để trống")
    private Criterion criterion;
}