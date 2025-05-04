package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List; // Added for relationship

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChuyenNganh")
public class Major {
    @Id
    @Column(name = "MaCN", length = 10)
    @NotNull(message = "Mã chuyên ngành không được để trống")
    private String majorCode;

    @Column(name = "TenCN", nullable = false, length = 100)
    @NotNull(message = "Tên chuyên ngành không được để trống")
    private String majorName;

    // Relationship added: One Major can have many Students
    @OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
    private List<Student> students;
}
