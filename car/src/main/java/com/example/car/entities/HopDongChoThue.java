package com.example.car.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "hop_dong_cho_thue")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HopDongChoThue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date ngayBatDau;

    @Column(nullable = false)
    private Date ngayKetThuc;

    @Column(length = 255, nullable = true)
    private String ghiChu;

    @Column(length = 15, nullable = false)
    private float giaThue;

    @ManyToOne
    @JoinColumn(name = "OtoId", nullable = false)
    private Oto oto;

    @ManyToOne
    @JoinColumn(name = "NhanVienId", nullable = false)
    private NhanVien nhanVien;
}
