package vn.edu.ptithcm.WebUIS.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SinhVien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "MaSV", length = 10)
    @NotNull(message = "Mã sinh viên không được để trống")
    private String studentId;

    @Column(name = "Ho", length = 50, nullable = false)
    @NotNull(message = "Họ không được để trống")
    private String lastName;

    @Column(name = "Ten", length = 50, nullable = false)
    @NotNull(message = "Tên không được để trống")
    private String firstName;

    @Column(name = "NgaySinh", nullable = false)
    @NotNull(message = "Ngày sinh không được để trống")
    private LocalDate dateOfBirth;

    @Column(name = "GioiTinh", nullable = false)
    @NotNull(message = "Giới tính không được để trống")
    private Boolean gender;

    @Column(name = "SoDienThoai", length = 10, nullable = false)
    private String phoneNumber;

    @Column(name = "CCCD", length = 12, nullable = false)
    @NotNull(message = "Số CCCD không được để trống")
    private String citizenId;

    @Column(name = "EmailTruong", length = 100)
    @NotNull(message = "Email trường không được để trống")
    @Email(message = "Email trường không hợp lệ")
    private String universityEmail;

    @Column(name = "EmailCaNhan", length = 100)
    private String personalEmail;

    @Column(name = "HinhAnh", length = 255)
    private String imagePath;

    @Column(name = "QueQuan", length = 100)
    private String hometown;

    @Column(name = "DiaChi", length = 500)
    private String address;

    @Column(name = "HoKhau", length = 100)
    private String permanentAddress;

    @Column(name = "DanToc", length = 50)
    private String ethnicity;

    @Column(name = "TrangThai", nullable = false)
    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "IdTK")
    @NotNull(message = "Tài khoản không được để trống")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "MaCN")
    @NotNull(message = "Chương trình không được để trống")
    private Major major;

    @ManyToOne
    @JoinColumn(name = "MaLop")
    @NotNull(message = "Lớp không được để trống")
    private ClassEntity classEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<AcademicResult> academicResults;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<TrainingScore> trainingScores;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<ClassCommittee> classCommittees;
}