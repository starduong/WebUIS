package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "ThongBao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTB", nullable = false)
    private Integer notificationId;

    @Column(name = "TieuDe", length = 200, nullable = false)
    private String title;

    @Column(name = "NoiDung", length = 1000, nullable = false)
    private String content;

    @Column(name = "FileDinhKem", length = 50)
    private String attachment;

    @Column(name = "NgayGui", nullable = false)
    private LocalDate sentDate;

    @Column(name = "TrangThai", length = 20, nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "IdNVPB", referencedColumnName = "IdNVPB", nullable = false)
    private EmployeeDepartment sender;
}
