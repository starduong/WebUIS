package vn.edu.ptithcm.WebUIS.model.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NoiDungDanhGia", schema = "HocTap")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdND", nullable = false)
    private Integer evaluationContentId;

    @Column(name = "NoiDung", length = 500)
    private String content;

    @Column(name = "DiemQuyDinh")
    private Integer regulatedScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTC", nullable = false)
    private Criterion criterion;

    @OneToMany(mappedBy = "evaluationContent", fetch = FetchType.LAZY)
    private List<ContentAssessment> ContentAssessments;
}
