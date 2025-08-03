package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "phu_phi_duoc_chon")
@Data
public class PhuPhiDuocChon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 15, nullable = false)
    private float gia;

    @ManyToOne
    @JoinColumn(name = "PhuPhiId", nullable = false)
    private PhuPhi phuPhi;

    @ManyToOne
    @JoinColumn(name = "HoaDonId", nullable = false)
    private HoaDon hoaDon;
}
