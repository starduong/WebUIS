package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MauDon")
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