package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JoinColumn(name = "MaSV")
    @NotNull(message = "Mã sinh viên không được để trống")
    private Student student;

    @Column(name = "TieuDe", length = 255, nullable = false)
    @NotNull(message = "Tiêu đề không được để trống")
    private String title;

    @Column(name = "NoiDung", columnDefinition = "MEDIUMTEXT")
    @NotNull(message = "Nội dung không được để trống")
    private String content;

    @Column(name = "FileDinhKem")
    private String attachmentUrl;

    @Column(name = "NgayGui", nullable = false)
    @NotNull(message = "Ngày gửi không được để trống")
    private LocalDateTime sendDate;

    @Column(name = "TrangThai", length = 20, nullable = false)
    @NotNull(message = "Trạng thái không được để trống")
    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "IdNVPB")
    private Employee employee;

    @Column(name = "PhanHoi", columnDefinition = "MEDIUMTEXT")
    private String response;

    @PrePersist
    public void prePersist() {
        this.sendDate = LocalDateTime.now();
    }
}