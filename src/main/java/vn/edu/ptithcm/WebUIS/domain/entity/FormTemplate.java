package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "MauDon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDon")
    private Integer id;

    @Column(name = "Loai", length = 50, nullable = false)
    @NotNull(message = "Loại không được để trống")
    private String type;

    @Column(name = "HinhAnh", length = 255, nullable = false)
    @NotNull(message = "Hình ảnh không được để trống")
    private String imagePath;

    @Column(name = "MauFile", length = 255, nullable = false)
    @NotNull(message = "Mẫu file không được để trống")
    private String filePath;
}