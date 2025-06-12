package vn.edu.ptithcm.WebUIS.domain.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ThongBao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTB")
    private Integer id;

    @Column(name = "TieuDe", length = 255, nullable = false)
    private String title;

    @Column(name = "NoiDung", columnDefinition = "MEDIUMTEXT")
    private String content;

    @Column(name = "FileDinhKem")
    private String attachmentUrl;

    @Column(name = "NgayDang", nullable = false)
    private LocalDateTime sendDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "MaNV")
    private Employee employee;

    @PrePersist
    public void prePersist() {
        this.sendDate = LocalDateTime.now();
    }
}
