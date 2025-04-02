package vn.edu.ptithcm.WebUIS.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "DiemRenLuyen", schema = "HocTap")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDRL", nullable = false)
    private Integer trainingScoreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLop")
    private ClassEntity classEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdHK")
    private Semester semester;

    @Column(name = "ThoiGianBatDau")
    private LocalDate startDate;

    @Column(name = "ThoiGianKetThuc")
    private LocalDate endDate;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "IdNVPB")
    // private StaffDepartment staffDepartment;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "IdNVK")
    // private StaffFaculty staffFaculty;

    @OneToMany(mappedBy = "trainingScore", fetch = FetchType.LAZY)
    private List<TrainingScoreDetail> trainingScoreDetails;

    // Constraint CHECK: ThoiGianBatDau < ThoiGianKetThuc
    @PrePersist
    @PreUpdate
    public void validateDates() {
        if (startDate != null && endDate != null && !startDate.isBefore(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }
}