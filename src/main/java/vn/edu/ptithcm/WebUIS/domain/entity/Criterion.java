package vn.edu.ptithcm.WebUIS.domain.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity lưu trữ tiêu chí chấm điểm rèn luyện
 */
@Entity
@Table(name = "TieuChi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTC")
    private Integer id;

    @Column(name = "TenTC")
    private String criterionName;

    @JsonIgnore
    @OneToMany(mappedBy = "criterion")
    private List<EvaluationContent> evaluationContents;
}