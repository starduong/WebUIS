package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "NhanVienPBK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDepartment {

    @Id
    @Column(name = "IdNVPB", nullable = false)
    private Integer employeeDepartmentId;

    @Column(name = "MaNV", length = 10, nullable = false)
    private String employeeCode;

    @Column(name = "MaPBK", length = 10, nullable = false)
    private String departmentCode;

    @Column(name = "NgayQuyetDinh", nullable = false)
    private java.time.LocalDate decisionDate;

    @ManyToOne
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "MaPBK", referencedColumnName = "MaPBK", insertable = false, updatable = false)
    private Department department;
}

