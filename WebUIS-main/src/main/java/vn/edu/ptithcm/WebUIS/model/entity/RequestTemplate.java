package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "MauDon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDon", nullable = false)
    private Integer requestId;

    @Column(name = "Loai", length = 50, nullable = false)
    private String type;

    @Column(name = "HinhAnh", length = 255, nullable = false)
    private String image;

    @Column(name = "MauFile", length = 255, nullable = false)
    private String templateFile;

    // Assuming you need a relationship with Complaint (IdDon as foreign key)
    @OneToMany(mappedBy = "requestTemplate", fetch = FetchType.LAZY)
    private List<Complaint> complaints;
}
