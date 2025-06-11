package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "KhieuNai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKhieuNai")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdDon")
    private FormTemplate formTemplate;

    @ManyToOne
    @JoinColumn(name = "MaSV")
    @NotNull(message = "Mã sinh viên không được để trống")
    private Student student;

    @Column(name = "NoiDung", columnDefinition = "MEDIUMTEXT")
    @NotNull(message = "Nội dung không được để trống")
    private String content;

    @Column(name = "NgayGui", nullable = false)
    @NotNull(message = "Ngày gửi không được để trống")
    private LocalDate sendDate;

    @Column(name = "TrangThai", length = 20, nullable = false)
    @NotNull(message = "Trạng thái không được để trống")
    private String status;

    @ManyToOne
    @JoinColumn(name = "IdNVPB")
    private Employee employee;

    @PrePersist
    public void prePersist() {
        this.sendDate = LocalDate.now();
    }
}