package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "NhanVien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "MaNV", length = 10, nullable = false)
    private String employeeCode;

    @Column(name = "Ho", length = 50, nullable = false)
    private String lastName;

    @Column(name = "Ten", length = 50, nullable = false)
    private String firstName;

    @Column(name = "GioiTinh", nullable = false)
    private Boolean gender;

    @Column(name = "SoDT", length = 15)
    private String phoneNumber;

    @Column(name = "CCCD", length = 12, nullable = false)
    private String nationalId;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "HinhAnh", length = 255)
    private String profileImage;

    @Column(name = "TrangThai", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "departmentEmployee", fetch = FetchType.LAZY)
    private List<Complaint> complaints;
}
