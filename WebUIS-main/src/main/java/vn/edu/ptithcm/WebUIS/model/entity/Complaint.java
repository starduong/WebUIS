package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "KhieuNai")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKN", nullable = false)
    private Integer complaintId;

    @Column(name = "NoiDung", length = 500, nullable = false)
    private String content;

    @Column(name = "NgayGui", nullable = false)
    private java.time.LocalDate submissionDate;

    @Column(name = "TrangThai", length = 20, nullable = false)
    private String status;

    @Column(name = "IdDon", nullable = false)
    private Integer requestId;

    @Column(name = "MaSV", length = 10, nullable = false)
    private String studentCode;

    @ManyToOne
    @JoinColumn(name = "MaSV", referencedColumnName = "MaSV", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "IdNVPB", referencedColumnName = "IdNVPB", insertable = false, updatable = false)
    private Employee departmentEmployee;
}

